package com.example.employeemanageend.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    //1.声明注册fanout模式的交换机(请假消息交换机)
    @Bean
    public FanoutExchange fanoutExchange(){
        System.out.println("创建leave_fanout交换机");
        return new FanoutExchange("leave_fanout",true,false);
    }
    //2.声明队列
    @Bean
    public Queue emailQueue(){
        System.out.println("声明队列emailqueue");
        return new Queue("emailqueue",true);
    }
    @Bean
    public Queue addLeaveQueue(){
        System.out.println("声明队列addleavequeue");
        return new Queue("addleavequeue",true);};
    //3.完成绑定队列
    @Bean
    public Binding emailBind(){
        System.out.println("绑定交换机1");
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding addLeaveBind(){
        System.out.println("绑定交换机2");
        return BindingBuilder.bind(addLeaveQueue()).to(fanoutExchange());
    }



}
