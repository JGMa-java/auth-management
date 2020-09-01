package com.jgma.code.redis;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created By majg on 2020-08-19
 */
@Component
public class MessageProducer extends Thread {

    public static final String MESSAGE_KEY = "message:queue";
    private volatile int count;

    private RedisTemplate redisTemplate;

    public void putMessage(String message) {
        ListOperations listOperations = redisTemplate.opsForList();

        Long size = listOperations.leftPush(MESSAGE_KEY, message);
        redisTemplate.expire(MESSAGE_KEY,2, TimeUnit.MINUTES);
        System.out.println(Thread.currentThread().getName() + " put message,size=" + size + ",count=" + count);
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            putMessage("message" + count);
        }
    }
}
