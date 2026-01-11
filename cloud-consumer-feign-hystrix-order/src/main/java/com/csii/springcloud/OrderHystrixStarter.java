package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2026-01-11 12:16
 *
 * EnableCircuitBreaker： 表示激活业务类上加的@HystrixCommand注解
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class OrderHystrixStarter {

    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixStarter.class,args);
    }
}
