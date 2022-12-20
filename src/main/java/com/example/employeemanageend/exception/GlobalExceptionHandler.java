package com.example.employeemanageend.exception;

import com.example.employeemanageend.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public Result handle(ServerException se){
        return Result.error(se.getCode(),se.getMessage());
    }
}
