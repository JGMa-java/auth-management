package com.jgma.code;

import com.alibaba.fastjson.JSON;
import com.jgma.code.entity.RedisUserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class AuthSecurityApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

//    @Test
    void contextLoads() {
        RedisUserTest userTest = new RedisUserTest();
        userTest.setAddress("北京");
        userTest.setAge(18);
        userTest.setName("李小龙");
        ValueOperations operations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();

        operations.set("user",userTest,30, TimeUnit.MINUTES);
        Boolean exists = redisTemplate.hasKey("user");
        System.out.println("不存在"+exists);

        Object user = operations.get("user");
        System.out.println(JSON.toJSONString(user));

        ListOperations listOperations = redisTemplate.opsForList();
    }

    public static void main(String[] args) {
//        for (int q = 0; q < 50; q++) {
//            printX();
//        }
        printX(3);
    }

    public static void printX(int height){
        for (int i = 10; i > 0; i--) {
            System.out.println();
            // 打印一行 第一列
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            System.out.print("X");
            // 打印一行 最后一列
            for (int k = 0; k <20; k++) {
                if (i==10 || i==1){
                    System.out.print("--");
                }else {
                    System.out.print("  ");
                }
            }
            System.out.print("X");
        }
        for (int h = 0; h < height; h++) {
            System.out.println();
        }
        for (int i = 10; i > 0; i--) {
            System.out.println();
            // 打印一行 第一列
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            System.out.print("X");
            // 打印一行 最后一列
            for (int k = 0; k <20; k++) {
                if (i==10 || i==1){
                    System.out.print("--");
                }else {
                    System.out.print("  ");
                }
            }
            System.out.print("X");
        }
    }

}
