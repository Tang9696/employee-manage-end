package com.example.employeemanageend.controller;

import com.example.employeemanageend.pojo.Menu;
import com.example.employeemanageend.pojo.Role;
import com.example.employeemanageend.service.MenuService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    RoleMenuService roleMenuService;

    //查询所有菜单
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name){
        pageNum = (pageNum-1) * pageSize;
        List<Menu> menus = menuService.selectPage(pageNum,pageSize,name);
        Integer total = menuService.selectTotal(name);
        Map<String,Object> req = new HashMap<>();
        req.put("menus",menus);
        req.put("total",total);
        return Result.success(req);
    }

    @GetMapping("/getmenus")
    public Result findall(@RequestParam(defaultValue = "") String name){
        return Result.success(menuService.getmenus(name));
    }


    //添加菜单
    @PostMapping("/addmenu")
    public Result realadd(@RequestBody Menu menu){
        menuService.save(menu);
        return Result.success();
    }

    //修改菜单信息
    @PostMapping("/updatemenu")
    public Result realupdate(@RequestBody Menu menu){
        System.out.println(menu);
        menuService.updateMenu(menu);

        return Result.success();
    }

    //删除菜单
    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id){
        //再删主表的信息
        menuService.deleteMenu(id);
        //删中间表信息
        roleMenuService.delbymid(id);
        return Result.success();
    }
}
