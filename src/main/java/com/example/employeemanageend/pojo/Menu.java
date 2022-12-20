package com.example.employeemanageend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {

    private Integer id;
    private String name;
    private String path;
    private String icon;
    private String descrip;
    private Integer pid;
    private String pagepath;
    private List<Menu> children;
}
