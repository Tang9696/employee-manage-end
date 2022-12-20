package com.example.employeemanageend.config;

import com.example.employeemanageend.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("signin");
        registry.addViewController("/signin.html").setViewName("signin");
        registry.addViewController("/index.html").setViewName("index");


    }

//    //自定义的国际化组件就生效了
//    @Bean
//    public LocaleResolver localeResolver(){
//        return new MyLocaleResolver();
//        //return new AcceptHeaderLocaleResolver();
//    }





}
