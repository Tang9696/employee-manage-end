package com.example.employeemanageend.service;
import com.example.employeemanageend.pojo.Employee;

import java.util.List;


public interface EmployeeService {


    //查询所有员工
    List<Employee> getAll();

    //查询管理员密码
    //public String getAdminPassword(String username);

    //添加员工
    public Integer save(Employee employee);

    //根据id查找员工
    public Employee getEmployeeById(Integer id);

    //修改员工
    void updateEmployee(Employee employee);

    //删除员工
    void deleteEmployee(Integer id);

    //shiro认证，根据用户名查找用户信息
   // public Employee getEmpByName(String username);

    //分页查询人员数据
    List<Employee> selectPage(Integer pageNum, Integer pageSize, String lastname);

    //查询人员数量
    Integer selectTotal(String lastname);

    //批量删除员工
    void batchDeleteEmployee(List<Integer> ids);

    //批量插入
    void saveall(List<Employee> employees);

    //登录验证
    Employee login(Employee user);
}
