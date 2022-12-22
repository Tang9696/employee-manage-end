package com.example.employeemanageend.mapper;

import com.example.employeemanageend.pojo.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    public Department getdept(@Param("id")Integer id);

    public List<Department> getdeptall();

    List<Department> selectPage(Integer pageNum, Integer pageSize, String departmentName);

    Integer selectTotal(String departmentName);

    void save(Department department);

    void deleteDept(Integer id);

    void updateDept(Department department);
}
