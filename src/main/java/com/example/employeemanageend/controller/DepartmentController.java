package com.example.employeemanageend.controller;

import com.example.employeemanageend.pojo.Department;
import com.example.employeemanageend.pojo.Role;
import com.example.employeemanageend.service.DeptService;
import com.example.employeemanageend.service.RoleMenuService;
import com.example.employeemanageend.service.RoleService;
import com.example.employeemanageend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    DeptService deptService;


    //查询所有部门
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String departmentName){
        pageNum = (pageNum-1) * pageSize;
        List<Department> departments = deptService.selectPage(pageNum,pageSize,departmentName);
        Integer total = deptService.selectTotal(departmentName);
        Map<String,Object> req = new HashMap<>();
        req.put("depts",departments);
        req.put("total",total);
        return Result.success(req);
    }

    //查询所有部门
    @GetMapping("/getdepts")
    public Result getroles(){
        return Result.success(deptService.getDepartments());
    }


    //add 部门
    @PostMapping("/adddept")
    public Result realadd(@RequestBody Department department){
        //添加dept表
        System.out.println(department.getDepartmentName());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createtime = formatter.format(date);
        department.setCreatetime(createtime);
        deptService.save(department);
        return Result.success();
    }

    //修改部门信息
    @PostMapping("/updatedept")
    public Result realupdate(@RequestBody Department department){
        System.out.println(department);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatetime = formatter.format(date);
        department.setUpdatetime(updatetime);
        deptService.updateDept(department);
        return Result.success();
    }

    //删除部门
    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id){
        deptService.deleteDept(id);
        return Result.success();
    }

}
