package com.example.employeemanageend.controller;

import com.example.employeemanageend.pojo.Leave;
import com.example.employeemanageend.service.LeaveService;
import com.example.employeemanageend.util.SendLeaveEmailUtill;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


@RestController
public class MqConsumerController {


    @Autowired
    LeaveService leaveService;
    @Autowired
    SendLeaveEmailUtill sendLeaveEmailUtill;

    //请假服务
    @RabbitHandler
    @RabbitListener(queues = "addleavequeue")
    public void geteavemq(Leave message) {
        //调用请假服务消费消息
        leaveService.save(message);
        System.out.println("消费完毕");
    }

    //请假给领导发邮件
    @RabbitHandler
    @RabbitListener(queues = "emailqueue")
    public void sendleaveemail(Leave leave) throws MessagingException {
        sendLeaveEmailUtill.sendLeaveEmali(leave);
        System.out.println("消费完毕-email");
    }


}
