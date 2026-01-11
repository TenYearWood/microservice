package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2026-01-11 22:28
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerStarter.class,args);
    }
}
