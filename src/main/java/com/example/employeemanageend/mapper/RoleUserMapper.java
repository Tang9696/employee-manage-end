package com.example.employeemanageend.mapper;

import com.example.employeemanageend.pojo.RoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleUserMapper {

    //查询所有人的角色id信息--中间表
    public List<RoleUser> getroleid(@Param("uid") Integer id);

}
