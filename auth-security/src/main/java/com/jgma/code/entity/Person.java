package com.jgma.code.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: admin
 */
@Data
public class Person implements Serializable {
    private final long serialVersionUID = 1L;

    private String id;

    private String userName;

    private String memberName;

    private String password;

    private String email;

    private String status;

    private String pwdSalt;
}
