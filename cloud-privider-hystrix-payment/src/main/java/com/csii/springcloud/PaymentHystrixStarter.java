package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2026-01-11 11:26
 *
 * EnableCircuitBreaker: 表示激活业务类上加的@HystrixCommand注解
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixStarter {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixStarter.class, args);
    }
}
