package com.example.employeemanageend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.employeemanageend.mapper")
public class EmployeeManageEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManageEndApplication.class, args);
    }

}
