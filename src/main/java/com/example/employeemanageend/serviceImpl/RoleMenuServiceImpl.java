package com.example.employeemanageend.serviceImpl;

import com.example.employeemanageend.mapper.RoleMenuMapper;
import com.example.employeemanageend.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Repository
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public List<Integer> getRoleMenu(Integer rid) {
        return roleMenuMapper.getRoleMenu(rid);
    }

    @Override
    public void delbymid(Integer mid) {
         roleMenuMapper.delbymid(mid);
    }
}
