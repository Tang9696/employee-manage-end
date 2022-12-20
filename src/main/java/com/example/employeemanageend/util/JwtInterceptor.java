package com.example.employeemanageend.util;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.employeemanageend.exception.ServerException;
import com.example.employeemanageend.pojo.Employee;
import com.example.employeemanageend.service.EmployeeService;
import com.example.employeemanageend.util.Constants;
import com.example.employeemanageend.util.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    EmployeeService employeeService;

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        System.out.println(token);
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServerException(Constants.CODE_401,"No token, please log in again");
        }
        //获取token中的userid
        String userId = null;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j){
            throw new ServerException(Constants.CODE_401, "Verification failure");
        }

        //根据token中的userid查询数据库
        Employee employee = employeeService.getEmployeeById(Integer.valueOf(userId));
        if(employee == null){
            throw new ServerException(Constants.CODE_401, "User does not exist");
        }

        //用户密码加签验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(employee.getPassword())).build();
        try{
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServerException(Constants.CODE_401, "Verification failure");
        }
         return true;
    }
}
