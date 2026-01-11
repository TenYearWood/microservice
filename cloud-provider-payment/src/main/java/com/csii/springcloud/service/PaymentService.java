package com.csii.springcloud.service;

import com.csii.pojo.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @description com.csii.springcloud.service
 * @author: chengyu
 * @date: 2025-12-23 22:46
 */
public interface PaymentService {

    int create(Payment payment);

    Payment queryById(@Param("id")long id);
}
