package com.example.employeemanageend.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.employeemanageend.pojo.Department;
import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.pojo.Role;
import com.example.employeemanageend.service.DeptService;
import com.example.employeemanageend.service.EmployeeService;
import com.example.employeemanageend.util.Result;
import com.example.employeemanageend.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DeptService deptService;


    //查询所有员工并分页
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String lastname){
        pageNum = (pageNum-1) * pageSize;
        List<Employee> employees = employeeService.selectPage(pageNum,pageSize,lastname);
        Integer total = employeeService.selectTotal(lastname);
        Map<String,Object> req = new HashMap<>();
        req.put("data",employees);
        req.put("total",total);

        //获取当前用户信息
        Employee employee = TokenUtils.getCurrentUser();
        System.out.println("hahahahahaahh"+employee);

        return req;
    }

    //查出所有部门信息，用于增加员工页面
    //Find out all department information for employees adding page
    @GetMapping("/emp")
    public Map<String,Object> addpage(){
        //查出所有部门信息
        List<Department> departments = deptService.getDepartments();
        Map<String,Object> req = new HashMap<>();
        req.put("data",departments);
        return req;
    }

    //添加员工
    //add staff
    @PostMapping("/addemp/{rid}")
    public Result realadd(@PathVariable Integer rid,@RequestBody Employee employee){
            //保存员工信息
            employeeService.save(employee);
        return Result.success();
    }

//    //通过id查询员工信息
//    @GetMapping("/empid")
//    public Map<String,Object> updatePage(@RequestParam Integer id){
//        System.out.println(id);
//        Employee employee = employeeService.getEmployeeById(id);
//        List<Department> departments = deptService.getDepartments();
//        Map<String,Object> req = new HashMap<>();
//        req.put("data",departments);
//        req.put("emp",employee);
//        return req;
//    }


    //修改员工信息
    @PostMapping("/updateemp")
    public Integer realupdate(@RequestBody Employee emp){
        System.out.println(emp);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updatetime = formatter.format(date);
        emp.setUpdatetime(updatetime);
        employeeService.updateEmployee(emp);
        return 1;
    }

    //删除员工
    @DeleteMapping("/{id}")
    public Integer del(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return 1;
    }

    //批量删除员工
    @DeleteMapping ("/bacth")
    public Integer batchdel(@RequestBody List<Integer> ids){
        System.out.println(ids);
        employeeService.batchDeleteEmployee(ids);
        return 1;
    }

    //导出功能
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeService.getAll();

        //自定义列名
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("id","id");
        writer.addHeaderAlias("depId","depId");
        writer.addHeaderAlias("lastname","lastname");
        writer.addHeaderAlias("email","email");
        writer.addHeaderAlias("gender","gender");
        writer.addHeaderAlias("department","department");
        writer.addHeaderAlias("birth","birth");
        writer.addHeaderAlias("createtime","createtime");
        writer.addHeaderAlias("updatetime","updatetime");

        //输出到excel文件中
        writer.write(employees,true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename = "Employee Information";
        response.setHeader("Content-Disposition","attachment;filename=" + filename + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();

    }


    //导入文件
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Employee> employees = reader.readAll(Employee.class);
        System.out.println(employees);
        employeeService.saveall(employees);
        return true;
    }



}
