package com.example.employeemanageend.service;

import com.example.employeemanageend.pojo.Department;
import com.example.employeemanageend.pojo.Role;

import java.util.List;


public interface DeptService {

    //根据员工的部门id 查询所有部门信息
    Department getDepartments(Integer id);

    //查询所有部门信息
    List<Department> getDepartments();

    List<Department> selectPage(Integer pageNum, Integer pageSize, String departmentName);

    Integer selectTotal(String departmentName);

    void save(Department department);

    void updateDept(Department department);

    void deleteDept(Integer id);
}
