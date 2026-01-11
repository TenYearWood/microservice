package com.csii.springcloud.controller;

import com.csii.pojo.CommonResult;
import com.csii.pojo.Payment;
import com.csii.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description 提供restful服务  供其他服务调用
 * @author: chengyu
 * @date: 2025-12-23 22:48
 */
@RestController
@Slf4j
public class PaymentControler {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    //获取服务信息
    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            log.info("********注册到eureka中的服务中有:" + s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("MICROSERVICE-PAYMENT");
        for (ServiceInstance s : instances) {
            log.info("当前服务的实例有" + s.getServiceId() + "\t" + s.getInstanceId() + "\t" + s.getHost() + "\t" + s.getPort() + "\t" + s.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment dept) {
        int i = paymentService.create(dept);
        log.info("***************插入成功*******" + i);
        if (i > 0) {
            return new CommonResult(200, "插入数据库成功" + serverPort, i);
        } else {
            return new CommonResult(444, "插入数据库失败" + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult queryById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);
        log.info("***************查询成功*********" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败" + serverPort, null);
        }
    }

    @GetMapping("/payment/lb/{id}")
    public CommonResult listById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);
        log.info("***************查询成功*********" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败" + serverPort, null);
        }
    }

    //模拟业务接口延时3秒
    @GetMapping("/payment/feign/timeout")
    public String PaymentFeignTimeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return serverPort;
    }
}
