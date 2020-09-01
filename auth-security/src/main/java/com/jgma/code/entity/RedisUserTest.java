package com.jgma.code.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created By majg on 2020-08-19
 */
@Data
public class RedisUserTest implements Serializable {
    private String name;
    private int age;
    private String address;

}
