package com.example.employeemanageend.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.io.unit.DataUnit;
import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.service.EmployeeService;
import com.example.employeemanageend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/example")
    public Result get(){
        Map<String,Object> map = new HashMap<>();
        map.put("x",CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }

    @GetMapping("/memeber")
    public Result member() throws ParseException {
        List<Employee> employees = employeeService.getAll();
        //定义四个季度
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for(Employee employee : employees){
            String createTime = employee.getCreatetime();
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date edate = sdf.parse(createTime);
            //给出一个java.util.Date对象，找到它所在的季度
            Quarter quarter = DateUtil.quarterEnum(edate);
            switch (quarter){
                case Q1: q1 += 1;break;
                case Q2: q2 += 1;break;
                case Q3: q3 += 1;break;
                case Q4: q4 += 1;break;
                default:break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1,q2,q3,q4));

    }

    @GetMapping("/gender")
    public Result gender()  {
        List<Employee> employees = employeeService.getAll();
        //男女数量
        int woman = 0;
        int man = 0;
        for(Employee employee : employees){
            if(employee.getGender() == 0){
                woman +=1;
            }else {
                man +=1;
            }
        }
        return Result.success(CollUtil.newArrayList(woman,man));
    }

}
