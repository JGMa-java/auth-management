package com.jgma.code.redis;

import com.jgma.code.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By majg on 2020-08-19
 */
@RestController
@RequestMapping("/redis")
public class RedisCtrl {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/send")
    public Object produce() {
        MessageProducer messageProducer = new MessageProducer();
        Thread t1 = new Thread(messageProducer, "thread1");
        Thread t2 = new Thread(messageProducer, "thread2");
        Thread t3 = new Thread(messageProducer, "thread3");
        Thread t4 = new Thread(messageProducer, "thread4");
        Thread t5 = new Thread(messageProducer, "thread5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        return "ok";
    }

    @GetMapping("/Rewrite/{value}")
    public Object Rewrite(@PathVariable(name = "value") String value){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("testRewrite",value);
        return "ok";
    }

}
