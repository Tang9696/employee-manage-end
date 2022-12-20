package com.example.employeemanageend.util;

import com.example.employeemanageend.pojo.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class SendLeaveEmailUtill {

    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String sender;
    @Autowired
    TemplateEngine templateEngine;

    public void sendLeaveEmali(Leave leave) throws MessagingException {
        Context context = new Context();
        context.setVariable("username", leave.getName());
        context.setVariable("dept", leave.getDept());
        context.setVariable("type", leave.getType());
        context.setVariable("starttime", leave.getStarttime());
        context.setVariable("endtime", leave.getEndtime());
        String emailContent = templateEngine.process("emailleave", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(sender);
        helper.setSubject("Leave of Absence Request");
        helper.setText(emailContent, true);

        mailSender.send(message);
    }
}
