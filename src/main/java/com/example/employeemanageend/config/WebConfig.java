package com.example.employeemanageend.config;

import com.example.employeemanageend.util.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求，通过判断token是否合法来决定是否需要登录
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login","/export","/import");
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

}
