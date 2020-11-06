package com.jgma.code.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgma.code.redis.MessageReceive1;
import com.jgma.code.redis.MessageReceive2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @Author: admin
 * created on 2020/10/12
 */
@Configuration
public class RedisConfig {

    /**
     * 配置自定义redisTemplate
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());

        // 使用Jackson2JsonRedisSerialize 替换默认的jdkSerializeable序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param redisConnectionFactory
     * @param listenerAdapter1
     * @return
     */
    //序列化对象（特别注意：发布的时候需要设置序列化；订阅方也需要设置序列化）
    @Bean
    public RedisMessageListenerContainer container1(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter listenerAdapter1, MessageListenerAdapter listenerAdapter2) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        // 订阅多个频道
        redisMessageListenerContainer.addMessageListener(listenerAdapter1, new PatternTopic("test1"));
        redisMessageListenerContainer.addMessageListener(listenerAdapter1, new PatternTopic("test2"));
        //不同的订阅者
        redisMessageListenerContainer.addMessageListener(listenerAdapter2, new PatternTopic("test2"));

        //序列化对象（特别注意：发布的时候需要设置序列化；订阅方也需要设置序列化）
        Jackson2JsonRedisSerializer seria = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        seria.setObjectMapper(objectMapper);
        redisMessageListenerContainer.setTopicSerializer(seria);
        return redisMessageListenerContainer;
    }

    //表示监听一个频道
    @Bean
    public MessageListenerAdapter listenerAdapter1(MessageReceive1 messageReceive1) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceive1 ”
        return new MessageListenerAdapter(messageReceive1, "getMessage");
    }

    //表示监听一个频道
    @Bean
    public MessageListenerAdapter listenerAdapter2(MessageReceive2 messageReceive2) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceive2 ”
        return new MessageListenerAdapter(messageReceive2, "getMessage");
    }


}
