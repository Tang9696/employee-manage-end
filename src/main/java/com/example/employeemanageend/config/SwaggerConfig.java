package com.example.employeemanageend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2//开启swagger2
public class SwaggerConfig {

    //配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){
        //获取项目的环境,设置要显示的swagger环境
        System.out.println("使用swagger");
        Profiles profiles = Profiles.of("dev","test");
        //通过environment.acceptsProfiles()判断是否处在自己设定的环境
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(apiInfo())
               .groupName("employee")
               .enable(flag)
               .select()
               //RequestHandlerSelectors,配置要扫描接口的方式
               //basePackage：指定要扫描的包
               //any():扫描全部
               //none():不扫描
               //withClassAnnotation():扫描类上的注解，参数是一个注解的反射对象
               //withMethodAnnotation():扫描方法上的注解
               .apis(RequestHandlerSelectors.basePackage("com.example.springstaffmanagement.controller"))
               //paths():过滤路径
               //.paths(PathSelectors.ant(""))
               .build();
     }

     //配置Swagger信息=apiInfo
     private ApiInfo apiInfo(){
         return new ApiInfo("Employee Management System",
                 "WangYuZhe Employee Management System",
                 "1.0",
                 "urn:tos",
                 DEFAULT_CONTACT,
                 "Apache 2.0",
                 "http://www.apache.org/licenses/LICENSE-2.0",
                 new ArrayList());
     }



}
