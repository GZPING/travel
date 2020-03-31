package com.gd.travel.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author : GD
 * @date :2020/3/31 : 10:45
 */
@Data
public class User {
    private String name;
    private Integer age;

    private String email;
    private String password;

    List<User> users ;

    Set<User> userSet;
}
