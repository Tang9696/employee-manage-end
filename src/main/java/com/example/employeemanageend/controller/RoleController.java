package com.example.employeemanageend.controller;

import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.pojo.Role;
import com.example.employeemanageend.service.RoleMenuService;
import com.example.employeemanageend.service.RoleService;
import com.example.employeemanageend.util.Result;
import com.example.employeemanageend.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMenuService roleMenuService;

    //查询所有角色
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String rolename){
        pageNum = (pageNum-1) * pageSize;
        List<Role> roles = roleService.selectPage(pageNum,pageSize,rolename);
        Integer total = roleService.selectTotal(rolename);
        Map<String,Object> req = new HashMap<>();
        req.put("roles",roles);
        req.put("total",total);
        return Result.success(req);
    }

    //查询所有角色
    @GetMapping("/getroles")
    public Result getroles(){
        return Result.success(roleService.getroles());
    }


    //add role
    @PostMapping("/addrole")
    public Result realadd(@RequestBody Role role){
        //添加role表
        System.out.println(role.getName());
        roleService.save(role);
        return Result.success();
    }

    //修改角色信息
    @PostMapping("/updaterole")
    public Result realupdate(@RequestBody Role role){
        System.out.println(role);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatetime = formatter.format(date);
        role.setUpdatetime(updatetime);
        roleService.updateRole(role);
        return Result.success();
    }

    //删除角色
    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id){
        roleService.deleteRole(id);
        return Result.success();
    }

    //给角色赋予菜单
    @PostMapping("/roleMenu/{rid}")
        public Result roleMenu(@PathVariable Integer rid,@RequestBody List<Integer> mids){
        roleService.setRoleMenu(rid,mids);
        return Result.success();
        }
    //拿取角色赋予菜单
    @GetMapping("/getroleMenu/{rid}")
    public Result getroleMenu(@PathVariable Integer rid){
        System.out.println(roleMenuService.getRoleMenu(rid));
        return Result.success(roleMenuService.getRoleMenu(rid));
    }

}
