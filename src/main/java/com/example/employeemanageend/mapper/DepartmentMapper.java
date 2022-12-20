package com.example.employeemanageend.mapper;

import com.example.employeemanageend.pojo.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    public Department getdept(@Param("id")Integer id);

    public List<Department> getdeptall();
}
