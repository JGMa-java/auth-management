package com.jgma.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Author: admin
 * created on 2020/10/9
 */
@Configuration
public class CustomRedisCacheManager extends CachingConfigurerSupport {

    @Autowired
    private LettuceConnectionFactory redisConnectionFactory;

    @Override
    public CacheManager cacheManager() {
        // 重新配置缓存
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        //设置缓存的默认超时时间：30分钟
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L))
                .disableCachingNullValues()
                .disableKeyPrefix()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((new GenericJackson2JsonRedisSerializer())));

        return RedisCacheManager.builder(RedisCacheWriter
                .nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

}
