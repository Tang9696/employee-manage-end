package com.example.employeemanageend.serviceImpl;

import com.example.employeemanageend.mapper.EmployeeMapper;
import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    //查询所有员工
    @Override
    public List<Employee> getAll() {
        return employeeMapper.getall();
    }

    //查询管理员密码
//    @Override
//    public String getAdminPassword(String username) {
//        return employeeMapper.getAdminPassword(username);
//    }

    //添加员工
    @Override
    public Integer save(Employee employee) {
        employeeMapper.save(employee);
        return null;
    }

    //根据id查找员工
    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    //删除员工
    @Override
    public void deleteEmployee(Integer id) {
       employeeMapper.deleteEmployee(id);
    }


    //分页查询员工
    @Override
    public List<Employee> selectPage(Integer pageNum, Integer pageSize, String lastname) {
        return employeeMapper.selectPage(pageNum,pageSize,lastname);
    }

    //查询人员数量
    @Override
    public Integer selectTotal(String lastname) {
        return employeeMapper.selectTotal(lastname);
    }

    //批量删除员工
    @Override
    public void batchDeleteEmployee(List<Integer> ids) { employeeMapper.batchDeleteEmployee(ids);}

    //批量插入
    @Override
    public void saveall(List<Employee> employees) {
        employeeMapper.saveall(employees);
    }

    //登录验证
    @Override
    public Employee login(Employee user) {
       return employeeMapper.login(user);
    }

    //修改员工
    @Override
    public void updateEmployee(Employee employee){
       employeeMapper.updateEmployee(employee);
    }
}
