package com.example.employeemanageend.service;


import com.example.employeemanageend.pojo.Menu;
import com.example.employeemanageend.pojo.Role;

import java.util.List;

public interface MenuService {

    List<Menu> selectPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);

    void save(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenu(Integer id);

    List<Menu> findall(String name);


    List<Menu> getmenus(String name);

    List<Menu> getmenubymid(List<Integer> mids);

    Menu getByid(Integer menuid);
}
