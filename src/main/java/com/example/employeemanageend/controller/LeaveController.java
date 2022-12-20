package com.example.employeemanageend.controller;


import com.example.employeemanageend.pojo.Department;
import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.pojo.Leave;
import com.example.employeemanageend.service.*;
import com.example.employeemanageend.util.Result;
import com.example.employeemanageend.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {

    @Autowired
    LeaveService leaveService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DeptService deptService;


    //通过id查询员工信息
    @GetMapping("/leaveadd/{id}")
    public String leavePage(@PathVariable("id")Integer id, Model modle){
        Employee employee = employeeService.getEmployeeById(id);
        System.out.println(employee);
        modle.addAttribute("emp",employee);
        Department departments = deptService.getDepartments(employee.getDepId());
        modle.addAttribute("dept",departments);

       return "leaveadd.html";
    }

    //添加假条
//    @PostMapping("/addleave")
//    public String leaveadd(Leave leave){
//        //接收前台参数,无需挨个拿，直接封装在了
//        System.out.println(leave);
//        //mqProducerService.addleavemq(leave);
//        //leaveService.save(leave);
//        return "redirect:/leavetoperson/"+ leave.getUid();
//    }

    //查询个人假条
    @GetMapping("/leavetoperson/{uid}")
    public Map<String,Object> list(@PathVariable("uid") Integer uid,
                       @RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam(defaultValue = "") String name){
        pageNum = (pageNum-1) * pageSize;
        System.out.println(uid);
        Integer uidnew = Integer.valueOf(uid);
        List<Leave> leaves = leaveService.selectPagePersonal(pageNum,pageSize,name,uidnew);
        System.out.println("hahahahaha:");
        System.out.println(leaves);
        Integer total = leaveService.selectTotalPersonal(name,uidnew);
        Map<String,Object> req = new HashMap<>();
        req.put("data",leaves);
        req.put("total",total);
        return req;
    }

    //查询所有请假信息
    @GetMapping("/getallleaves")
    public Result getAllLeaves(Model model){
        List<Leave> leaves = leaveService.getAllLeaves();
        return Result.success(leaves);
    }
    @GetMapping("/leavepage")
    public Map<String,Object> leavePage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String name){
        pageNum = (pageNum-1) * pageSize;
        List<Leave> leaves = leaveService.selectPage(pageNum,pageSize,name);
        Integer total = leaveService.selectTotal(name);
        Map<String,Object> req = new HashMap<>();
        req.put("data",leaves);
        req.put("total",total);
        return req;
    }

    //更改假条状态（同意）
    @PostMapping("/leavesAgree/{id}")
    public Result leaveAgree(@PathVariable("id")Integer id){
        int status = 2;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatetime = formatter.format(date);
        leaveService.leaveUpdate(id,status,updatetime);
        return Result.success();
    }

    //更改假条状态（拒绝）
    @PostMapping("/leavesRefuse/{id}")
    public Result leavesRefuse(@PathVariable("id")Integer id){
        int status = 3;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatetime = formatter.format(date);
        leaveService.leaveUpdate(id,status,updatetime);
        return Result.success();
    }

    //删除
    @DeleteMapping("/leavesDel/{id}")
    public Result leaveDel(@PathVariable("id")Integer id){
        leaveService.leaveDel(id);
        return Result.success();
    }

    //批量删除假条
    @DeleteMapping ("/leavebacth")
    public Result batchdel(@RequestBody List<Integer> ids){
        System.out.println(ids);
        leaveService.batchDeleteLeave(ids);
        return Result.success();
    }

}
