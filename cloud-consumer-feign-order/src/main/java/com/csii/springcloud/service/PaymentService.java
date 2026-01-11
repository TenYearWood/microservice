package com.csii.springcloud.service;

import com.csii.pojo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description com.csii.springcloud.service
 * @author: chengyu
 * @date: 2025-12-23 22:46
 */
@Component
@FeignClient(value ="microservice-payment")//使用Feign，使用哪个微服务
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    CommonResult queryById(@PathVariable("id") Long id);

    /**
     * 调用生产者微服务名称为microservice-payment下边的模拟超时的接口
     * Openfeign默认等待1秒钟，超过后会报错
     * 但是我们的生产者服务确实需要处理复杂的业务，处理时间会超过1秒，就需要修改Openfeign默认等待的时间。
     * 需要在消费者的服务的yml文件进行设置。因为Feign集成了Ribbon，所以需要设置Ribbon的相关。
     */
    @GetMapping("/payment/feign/timeout")
    String PaymentFeignTimeOut() throws InterruptedException;
}
