package com.example.employeemanageend.mapper;

import com.example.employeemanageend.pojo.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> selectPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);

    void save(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenu(Integer id);

    List<Menu> findall(String name);

    List<Menu> getmenubymid(List<Integer> mids);

    Menu getByid(Integer menuid);
}
