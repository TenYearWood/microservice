package com.csii.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description com.csii.springcloud.config
 * @author: chengyu
 * @date: 2025-12-23 23:05
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced   //RestTemplate 的负载均衡能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
