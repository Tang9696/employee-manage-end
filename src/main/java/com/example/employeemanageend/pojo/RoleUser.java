package com.example.employeemanageend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUser implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer rid;


//    private List<Employee> employee;
//    private List<Role> role;
}
