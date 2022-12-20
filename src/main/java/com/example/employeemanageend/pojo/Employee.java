package com.example.employeemanageend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//员工
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private Integer id;
    private Integer depId;
    private String lastname;
    private String password;
    private String email;
    private Integer gender; //0:女，1：男
    //多对一关系，要在多的一方创建一的实体
    private Department department;
    private String birth;
    private String role;
    Date date = new Date();
    @JsonIgnore
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String createtime = formatter.format(date);
    String updatetime = formatter.format(date);

    //增加token属性
    private String token;

    //private RoleUser roleUsers;
    private List<Leave> leaves;
    private List<Menu> menus;

}
