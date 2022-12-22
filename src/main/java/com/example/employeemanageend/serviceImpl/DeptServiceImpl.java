package com.example.employeemanageend.serviceImpl;

import com.example.employeemanageend.mapper.DepartmentMapper;
import com.example.employeemanageend.pojo.Department;
import com.example.employeemanageend.pojo.Role;
import com.example.employeemanageend.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Repository
public class DeptServiceImpl implements DeptService {
    @Autowired(required = false)
    DepartmentMapper departmentMapper;

    @Override
    public Department getDepartments(Integer id) {
        return departmentMapper.getdept(id);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getdeptall();
    }

    @Override
    public List<Department> selectPage(Integer pageNum, Integer pageSize, String departmentName) {
        return departmentMapper.selectPage(pageNum, pageSize, departmentName);
    }

    @Override
    public Integer selectTotal(String departmentName) {
        return departmentMapper.selectTotal(departmentName);
    }

    @Override
    public void save(Department department) {
        departmentMapper.save(department);
    }

    @Override
    public void updateDept(Department department) {
        departmentMapper.updateDept(department);
    }

    @Override
    public void deleteDept(Integer id) {
        departmentMapper.deleteDept(id);
    }

}
