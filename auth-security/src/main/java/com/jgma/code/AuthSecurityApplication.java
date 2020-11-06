package com.jgma.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.jgma.code.dao")
@EnableCaching
public class AuthSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthSecurityApplication.class, args);
    }

}
