package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2026-01-11 14:15
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayStarter {

    public static void main(String[] args) {
        SpringApplication.run(GatewayStarter.class,args);
    }
}
