package com.example.employeemanageend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

//部门
@Data //自动生成set/get方法，toString方法，equals方法，hashCode方法，不带参数的构造方法
@NoArgsConstructor //无参构造方法
@AllArgsConstructor //有参构造方法
public class Department implements Serializable {

     private Integer id;
     private String departmentName;
     private String createtime;
     private String updatetime;
     private List<Employee> employees;

}
