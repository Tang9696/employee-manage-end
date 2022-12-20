package com.example.employeemanageend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//请假表
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave implements Serializable {

    private Integer id;
    private Integer uid;
    private String name;
    private String dept;
    private String type;
    Date date = new Date();
    @JsonIgnore
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String starttime = formatter.format(date);
    String endtime = formatter.format(date);
    private int status;
    String updatetime = formatter.format(date);

    //创建对于用户的一对一实体
    private Employee employee;
}
