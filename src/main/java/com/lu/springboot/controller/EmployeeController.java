package com.lu.springboot.controller;

import com.lu.springboot.dao.DepartmentDao;
import com.lu.springboot.dao.EmployeeDao;
import com.lu.springboot.entities.Department;
import com.lu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面

    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);
        //thymeleaf默认会拼串，classpath:/templates/xxxx.html
        return "emp/list";

    }

    //员工添加页面
    @RequestMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面,查出所有部门在页面展示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";

    }
    //员工添加功能
    //springMvc自动将请求参数和入参对象的属性进行一一绑定
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //员工列表页面
        System.out.println("保存的员工信息" + employee);
        //保存员工
        employeeDao.save(employee);
        //redirect: 表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/emps";

    }

    //修改页面,查出当前员工，在页面回显数据
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //查出部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面
        return "emp/add";

    }
    //修改页面功能
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除功能
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";

    }
}
