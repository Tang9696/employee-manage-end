package com.example.employeemanageend.service;

import com.example.employeemanageend.pojo.Role;

import java.util.List;

public interface RoleService {


    //查询所有角色
    List<Role> selectPage(Integer pageNum, Integer pageSize, String rolename);

    //查询所有总数
    Integer selectTotal(String rolename);

    //添加角色
    void save(Role role);

    void deleteRole(Integer id);

    void updateRole(Role role);

    void setRoleMenu(Integer rid, List<Integer> mids);

    List<Role> getroles();

    Integer selectId(String role);
}
