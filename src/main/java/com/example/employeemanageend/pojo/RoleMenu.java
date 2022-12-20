package com.example.employeemanageend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu implements Serializable {

    private Integer id;
    private Integer rid;
    private Integer mid;
}
