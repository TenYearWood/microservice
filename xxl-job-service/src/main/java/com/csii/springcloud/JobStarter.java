package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2026-01-11 14:38
 */
@SpringBootApplication
@EnableDiscoveryClient
public class JobStarter {

    public static void main(String[] args) {
        SpringApplication.run(JobStarter.class, args);
    }
}
