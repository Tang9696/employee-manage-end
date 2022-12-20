package com.example.employeemanageend.mapper;

import com.example.employeemanageend.pojo.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {


    //删除以rid为条件的菜单信息
    void delete(Integer rid);

    //添加角色菜单信息
    void insert(RoleMenu roleMenu);

    List<Integer> getRoleMenu(Integer rid);

    void delbymid(Integer mid);
}
