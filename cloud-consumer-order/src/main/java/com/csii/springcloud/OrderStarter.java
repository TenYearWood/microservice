package com.csii.springcloud;

import com.csii.springcloud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @description com.csii.springcloud
 * @author: chengyu
 * @date: 2025-12-23 22:58
 *
 * RibbonClient: name为生产者服务的服务名称  configuration为配置类的类名
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "microservice-payment",configuration = MySelfRule.class)
public class OrderStarter {

    public static void main(String[] args) {
        SpringApplication.run(OrderStarter.class,args);
    }
}
