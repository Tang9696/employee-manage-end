package com.example.employeemanageend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {

    /**
     * 解决跨域问题
     */
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:8080")
                    .allowCredentials(true)
                    .maxAge(3600)
//                    .allowedHeaders("Accept", "Content-Type", "Origin", "Authorization", "X-Auth-Token")
//                    .exposedHeaders("X-Auth-Token", "Authorization")
                    .allowedHeaders("*")
                    .exposedHeaders("*")
                    .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS");
        }
    }

