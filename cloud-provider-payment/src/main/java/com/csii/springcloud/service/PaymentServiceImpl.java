package com.csii.springcloud.service;

import com.csii.pojo.Payment;
import com.csii.springcloud.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description com.csii.springcloud.service
 * @author: chengyu
 * @date: 2025-12-23 22:47
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment queryById(long id) {
        return paymentDao.queryById(id);
    }
}
