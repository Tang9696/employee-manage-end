package com.example.employeemanageend.mapper;

import com.example.employeemanageend.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    //根据中间表传来的rid信息查询所有role信息
    public List<Role> getallrole(@Param("rid") Integer id);

    //查询所有role信息
    List<Role> getall();


    List<Role> selectPage(Integer pageNum, Integer pageSize, String rolename);

    Integer selectTotal(String rolename);

    void save(Role role);

    void saveRP(Integer rid, Integer pid);

    void deleteRoleRP(Integer id);

    void deleteRole(Integer id);

    void updateRole(Role role);

    void updateRoleRP(Integer pid, Integer id);

    List<Role> getroles();

    Integer selectId(String role);

}
