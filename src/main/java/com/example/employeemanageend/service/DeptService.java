package com.example.employeemanageend.service;

import com.example.employeemanageend.pojo.Department;

import java.util.List;


public interface DeptService {

    //根据员工的部门id 查询所有部门信息
    Department getDepartments(Integer id);

    //查询所有员工信息
    List<Department> getDepartments();


}
