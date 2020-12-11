package com.kubrick.annotation.entity;


import lombok.Data;
import lombok.ToString;

/**
 * @author k
 */
@ToString
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer age;
}
