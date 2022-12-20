package com.example.employeemanageend.service;

import com.example.employeemanageend.pojo.Leave;

import java.util.List;

public interface LeaveService {

    //请假
    public void save(Leave leave);

    //查询个人假条信息
    List<Leave> getPersonLeaves(Integer id);

    //查询所有请假信息
    List<Leave> getAllLeaves();

    //更改假条状态
    void leaveUpdate(int id,int status,String updatetime);

    //删除
    void leaveDel(int id);

    List<Leave> selectPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);

    void batchDeleteLeave(List<Integer> ids);

    List<Leave> selectPagePersonal(Integer pageNum, Integer pageSize, String name, Integer uid);

    Integer selectTotalPersonal(String name, Integer uid);
}
