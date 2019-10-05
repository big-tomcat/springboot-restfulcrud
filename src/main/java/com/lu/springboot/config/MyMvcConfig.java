package com.lu.springboot.config;


import com.lu.springboot.component.LoginHandlerInterceptor;
import com.lu.springboot.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Locale;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");





    }
   //注册拦截器
   @Override
    public  void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }
}
