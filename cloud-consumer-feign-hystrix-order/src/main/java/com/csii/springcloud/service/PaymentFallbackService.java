package com.csii.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 关于服务的降级，是消费者服务去调用生产者服务，如果生产者服务出现宕机或者关闭，那我们就要对消费者服务进行服务降级处理，与生产者服务没有关系
 * 只需要为Feign客户端定义的接口添加一个服务降级处理的实现类即可实现解耦。
 * 为了防止与业务逻辑与代码混在一块，看起来特别混乱，所以要新建一个类PaymentFallbackService，
 * 实现该业务类PaymentHystrixService接口，统一为接口里面的方法进行异常处理
 * 这样就解决了业务逻辑与代码混合在一起的问题
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall  paymentInfo_OK 服务器出现故障,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService fall  paymentInfo_TimeOut 服务器出现故障,o(╥﹏╥)o";
    }
}
