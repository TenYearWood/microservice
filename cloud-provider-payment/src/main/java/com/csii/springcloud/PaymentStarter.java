package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2025-12-23 22:41
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentStarter {

    public static void main(String[] args) {
        SpringApplication.run(PaymentStarter.class, args);
    }
}
