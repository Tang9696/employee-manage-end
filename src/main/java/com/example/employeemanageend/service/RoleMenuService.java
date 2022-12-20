package com.example.employeemanageend.service;

import java.util.List;

public interface RoleMenuService {

    List<Integer> getRoleMenu(Integer rid);

    //删除中间表信息
    void delbymid(Integer mid);
}
