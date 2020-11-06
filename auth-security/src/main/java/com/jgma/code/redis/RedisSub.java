package com.jgma.code.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: admin
 */
@RestController
public class RedisSub {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 发布一个消息
     *
     * @param channel
     * @param message
     */
    public void publishMsg(String channel, String message) {
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.leftPush(channel,message);

//            jedis.publish(channel, message);
        } catch (Exception e) {
        }
    }
}
