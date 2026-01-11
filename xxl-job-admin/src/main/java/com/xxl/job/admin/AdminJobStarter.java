package com.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description PACKAGE_NAME
 * @author: chengyu
 * @date: 2026-01-04 21:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AdminJobStarter {

    public static void main(String[] args) {
        SpringApplication.run(AdminJobStarter.class, args);
    }
}
