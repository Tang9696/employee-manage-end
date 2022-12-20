package com.example.employeemanageend.serviceImpl;

import com.example.employeemanageend.mapper.MenuMapper;
import com.example.employeemanageend.pojo.Menu;
import com.example.employeemanageend.pojo.Role;
import com.example.employeemanageend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Repository
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> selectPage(Integer pageNum, Integer pageSize, String name) {
        return menuMapper.selectPage(pageNum,pageSize,name);
    }

    @Override
    public Integer selectTotal(String name) {
        return menuMapper.selectTotal(name);
    }

    @Override
    public void save(Menu menu) {
       menuMapper.save(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
       menuMapper.updateMenu(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
       menuMapper.deleteMenu(id);
    }

    @Override
    public List<Menu> findall(String name) {
        return menuMapper.findall(name);
    }

    @Override
    public List<Menu> getmenus(String name) {
        List<Menu> menus = findall(name);
        System.out.println(menus);
        //找出pid为null的一级菜单
        List<Menu> parentNode = menus.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //找出子菜单
        for(Menu menu : parentNode){
            menu.setChildren(menus.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentNode;
    }

    @Override
    public List<Menu> getmenubymid(List<Integer> mids) {
        return menuMapper.getmenubymid(mids);
    }

    @Override
    public Menu getByid(Integer menuid) {
        return menuMapper.getByid(menuid);
    }
}
