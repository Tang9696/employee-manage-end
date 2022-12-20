package com.example.employeemanageend.serviceImpl;

import com.example.employeemanageend.mapper.LeaveMapper;
import com.example.employeemanageend.pojo.Leave;
import com.example.employeemanageend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class LeaveServiceImpl implements LeaveService {

    @Autowired(required = false)
    LeaveMapper leaveMapper;

    @Override
    public void save(Leave leave) {
        leaveMapper.save(leave);
    }

    @Override
    public List<Leave> getPersonLeaves(Integer id) {
        return leaveMapper.getPersonLeaves(id);
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveMapper.getAllLeaves();
    }

    @Override
    public void leaveUpdate(int id,int status,String updatetime) {
        leaveMapper.leaveUpdate(id,status,updatetime);
    }

    @Override
    public void leaveDel(int id) {
        leaveMapper.leaveDel(id);
    }

    @Override
    public List<Leave> selectPage(Integer pageNum, Integer pageSize, String name) {
        return leaveMapper.selectPage(pageNum,pageSize,name);
    }

    @Override
    public Integer selectTotal(String name) {
        return leaveMapper.selectTotal(name);
    }

    @Override
    public void batchDeleteLeave(List<Integer> ids) {
        leaveMapper.batchDeleteLeave(ids);
    }

    @Override
    public List<Leave> selectPagePersonal(Integer pageNum, Integer pageSize, String name, Integer uid) {
        return leaveMapper.selectPagePersonal(pageNum,pageSize,name,uid);
    }

    @Override
    public Integer selectTotalPersonal(String name, Integer uid) {
        return leaveMapper.selectTotalPersonal(name,uid);
    }

}
