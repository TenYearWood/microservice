package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2025-12-23 23:31
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaStarter {

    public static void main(String[] args) {
        SpringApplication.run(EurekaStarter.class,args);
    }
}
