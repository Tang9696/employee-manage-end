package com.example.employeemanageend.serviceImpl;

import com.example.employeemanageend.mapper.RoleMapper;
import com.example.employeemanageend.mapper.RoleMenuMapper;
import com.example.employeemanageend.pojo.Menu;
import com.example.employeemanageend.pojo.Role;
import com.example.employeemanageend.pojo.RoleMenu;
import com.example.employeemanageend.service.MenuService;
import com.example.employeemanageend.service.RoleService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;


    @Override
    public List<Role> selectPage(Integer pageNum, Integer pageSize, String rolename) {
        return roleMapper.selectPage(pageNum,pageSize,rolename);
    }

    @Override
    public Integer selectTotal(String rolename) {
        return roleMapper.selectTotal(rolename);
    }

    @Override
    public void save(Role role) {
        roleMapper.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
    }

    @Override
    public void updateRole(Role role) {
       roleMapper.updateRole(role);
    }

    //给角色赋予菜单
    public void setRoleMenu(Integer rid,List<Integer> mids){

        //先删当前角色id所有绑定关系，
        roleMenuMapper.delete(rid);

        //在进行添加
        for(Integer menuid : mids){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRid(rid);
            roleMenu.setMid(menuid);
            roleMenuMapper.insert(roleMenu);
        }
    }


    @Override
    public List<Role> getroles() {
        return roleMapper.getroles();
    }

    @Override
    public Integer selectId(String role) {
        return roleMapper.selectId(role);
    }
}
