package com.csii.springcloud.controller;

import com.csii.pojo.CommonResult;
import com.csii.pojo.Payment;
import com.csii.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description com.csii.springcloud.controller
 * @author: chengyu
 * @date: 2025-12-28 22:35
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult result = paymentService.queryById(id);
        return result;
    }

    @GetMapping("/consumer/feign/timeout")
    public String PaymentFeignTimeOut() throws InterruptedException{
        return paymentService.PaymentFeignTimeOut();
    }
}
