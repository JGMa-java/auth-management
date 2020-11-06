package com.jgma.code.service.impl;

import com.jgma.code.service.RedisService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: admin
 * created on 2020/10/9
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static Map<String,String> result = new HashMap();

    static {
        result.put("1","字符串1");
        result.put("2","字符串2");
    }

    @Cacheable(key = "#key",value = "testRewrite")
    @Override
    public Object get(String key) {
        System.out.println("我是测试cache，service返回的。。。");
        return result.get(key);
    }
}
