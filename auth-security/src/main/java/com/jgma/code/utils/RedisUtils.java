package com.jgma.code.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created By majg on 2020-08-19
 */
@Configuration
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

}
