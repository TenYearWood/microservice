package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2025-12-28 21:57
 */
@SpringBootApplication
@EnableFeignClients//激活Feign的注解  需要加到主启动类上
public class OrderFeignStarter {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignStarter.class,args);
    }
}
