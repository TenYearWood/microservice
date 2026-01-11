package com.csii.springcloud.dao;

import com.csii.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description com.csii.springcloud.dao
 * @author: chengyu
 * @date: 2025-12-23 22:45
 */
@Mapper
@Repository
public interface PaymentDao {

    int create(Payment payment);

    Payment queryById(@Param("id")long id);
}
