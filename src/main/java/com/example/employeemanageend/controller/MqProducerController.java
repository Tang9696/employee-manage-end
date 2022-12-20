package com.example.employeemanageend.controller;

import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.pojo.Leave;
import com.example.employeemanageend.service.*;
import com.example.employeemanageend.util.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqProducerController {


    @Autowired(required = false)
    private RabbitTemplate rabbitTemplate;

    //发送请假消息至MQ
    @PostMapping("/mqaddleave")
    public Result addleavequeue(@RequestBody Leave leave,
                                @RequestParam String luid,
                                @RequestParam String lname,
                                @RequestParam String ldept){
        String exchangename = "leave_fanout";
        System.out.println(luid+"----"+lname+"-----"+ldept);
        leave.setDept(ldept);
        leave.setName(lname);
        Integer uidnew = Integer.valueOf(luid);
        leave.setUid(uidnew);
        leave.setStatus(1);
        rabbitTemplate.convertAndSend(exchangename,"",leave);
        return Result.success();
    }

}
