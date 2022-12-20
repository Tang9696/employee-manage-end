package com.example.employeemanageend.controller;

import cn.hutool.core.util.StrUtil;
import com.example.employeemanageend.mapper.RoleMenuMapper;
import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.pojo.Menu;
import com.example.employeemanageend.service.MenuService;
import com.example.employeemanageend.service.RoleMenuService;
import com.example.employeemanageend.service.RoleService;
import com.example.employeemanageend.util.Constants;
import com.example.employeemanageend.service.EmployeeService;
import com.example.employeemanageend.util.Result;
import com.example.employeemanageend.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MenuService menuService;



    //登录
    @PostMapping("/user/login")
    //@ResponseBody
    public Result login(@RequestBody Employee user){
        String username = user.getLastname();
        String password = user.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"Parameter error");
        }else{
            Employee employee = employeeService.login(user);
            if(employee != null) {
                System.out.println(employee);
                String token = TokenUtils.genToken(employee.getId().toString(),employee.getPassword().toString());
                employee.setToken(token);
                //得到该用户的角色名称
                String role = employee.getRole();
                //因为和menu表没有做关联，所以我们需要得到该角色的rid，从而使用role_menu中间表得到最后的菜单信息
                Integer roleid = roleService.selectId(role);
                //获取role-menu中间表的所有mid
                List<Integer> mids = roleMenuService.getRoleMenu(roleid);

                //要区分父菜单和子菜单,我们先把所有菜单都查出来，然后进行筛选
                List<Menu> menus = menuService.getmenus("");
                //new一个筛选完成的菜单列表
                List<Menu> roleMenus = new ArrayList<>();
                //然后再筛选菜单
                for(Menu menu : menus){
                    if(mids.contains(menu.getId())){
                        roleMenus.add(menu);
                    }
                    //子菜单,这一部分我们之前再MenuServiceImpl中set了所有子菜单
                    List<Menu> children = menu.getChildren();
                    //移除掉子菜单中不在mids中的项
                    //children.removeIf(childs -> !mids.contains(childs.getId()));
                    boolean isSuccess = children.removeIf(child -> !mids.contains(child.getId()));
                    //如果输出为false的话，说明没有可以排除的子菜单
                    System.out.println(isSuccess);
                    //boolean默认传false
                    if (children.size() != 0 && isSuccess) {
                        //我们只保存isSuccess为flase的选项
                        roleMenus.add(menu);
                    }
              }
                employee.setMenus(roleMenus);

                return Result.success(employee);
            }else {return Result.error(Constants.CODE_400,"Username or Password Error");}
        }

    }

//    @GetMapping("/index")
//    public String index(){
//        return "/index";
//    }
//    @GetMapping ("/login")
//    public String loginhtml(){
//        return "/signin";
//    }

//    @RequestMapping ("/logout")
//    public String logout(HttpSession session, HttpServletResponse response){
////        Subject subject = SecurityUtils.getSubject();
////        session.removeAttribute("loginUser");
////        if (subject.isAuthenticated()) {
////            subject.logout();
////            Cookie cookie = new Cookie("rememberMe", null);
////            cookie.setMaxAge(0);
////            response.addCookie(cookie);
////        }
//        return "redirect:/user/login";
//    }

//    @GetMapping("/unauthorized")
//    @ResponseBody
//    public String unauthorized(){
//        return "page of unauthorized!";
//    }


}
