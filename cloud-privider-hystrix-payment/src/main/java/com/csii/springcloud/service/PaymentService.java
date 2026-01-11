package com.csii.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description com.csii.springcloud.service
 * @author: chengyu
 * @date: 2026-01-11 11:27
 */
@Service
public class PaymentService {

    /**
     * 正常访问
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 超时访问
     * 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     * fallbackMethod属性是写降级的方法名，意思就是该方法出现问题后， 这个方法paymentInfo_TimeOutHandler进行兜底。
     * commandProperties属性是数组形式的，可以设置多个属性，添加该注解@HystrixProperty，然后配置里边name和value的属性。
     * name属性就是该线程timeoutInMilliseconds，value就是设置峰值时间。具体配置如下
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //异常错误的演示代码
        //int age = 10/0;

        long startTime = System.currentTimeMillis();
        try {
            // 暂停2秒钟
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "\t" +
                "O(∩_∩)O哈哈~  耗时(毫秒)" + (endTime - startTime);
    }

    /**
     * 超时访问到这里兜底
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler,id:" + id + "\t" +
                "系统繁忙，请稍后再试****o(╥﹏╥)o";
    }

    /**
     * HystrixCommand: Hystrix会监控微服务调用的状况，当失败的调用到一定的阈值，缺省是5秒内20次调用失败，就会启动熔断机制。
     * 熔断机制的注解是@HystrixCommand
     * <p>
     * circuitBreaker.enabled   是否开启断路器
     * requestVolumeThreshold   请求次数，断路器的窗口期内触发断路的请求阈值，默认为20。换句话说，假如某个窗口期内的请求总数都不到该配置值，那么断路器连发生的资格都没有。断路器在该窗口期内将不会被打开。
     * sleepWindowInMilliseconds    时间窗口期，可以理解为一个触发断路器的周期时间值，默认为10秒（10000）。
     * errorThresholdPercentage     失败率达到多少后跳闸，断路器的窗口期内能够容忍的错误百分比阈值，默认为50（也就是说默认容忍50%的错误率）。打个比方，假如一个窗口期内，发生了100次服务请求，其中50次出现了错误。在这样的情况下，断路器将会被打开。在该窗口期结束之前，即使第51次请求没有发生异常，也将被执行fallback逻辑。
     * 这里是写在@HystrixProperties注解中，当然实际项目中可以全局配置在yml或properties中
     *
     * 综上所述，在以上三个参数缺省的情况下，Hystrix断路器触发的默认策略为：
     * 在10秒内，发生20次以上的请求时，假如错误率达到50%以上，则断路器将被打开。（当一个窗口期过去的时候，断路器将变成半开（HALF-OPEN）状态，如果这时候发生的请求正常，则关闭，否则又打开）
     *
     * 熔断三大类型：
     * 熔断打开：请求不再调用当前服务，内部设置一般为MTTR（平均故障处理时间），当打开时长达到所设时钟则进入半熔断状态。
     * 熔断关闭：熔断关闭不会对服务进行熔断。
     * 熔断半开：部分请求根据规则调用当前服务，如果请求成功且符合规则认为当前服务恢复正常，关闭熔断。
     *
     * 官网的的流程图描述了Hystrix的工作流程：
     * 1、每次调用都会创建一个HystrixCommand
     * 2、执行execute或queue做同步\异步调用
     * 3、判断熔断器是否打开,如果打开跳到步骤8，否则进入步骤4
     * 4、判断线程池/信号量是否跑满，如果跑满进入步骤8,否则进入步骤5
     * 5、调用HystrixCommand的run方法，如果调用超时进入步骤8
     * 6、判断是否调用成功，返回成功调用结果，如果失败进入步骤8
     * 7、计算熔断器状态,所有的运行状态(成功, 失败, 拒绝,超时)上报给熔断器，用于统计从而判断熔断器状态
     * 8、降级处理逻辑，根据上方的步骤可以得出以下四种情况会进入降级处理：熔断器打开、线程池/信号量跑满、调用超时、调用失、
     * 9、返回执行成功结果
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    /**
     * 兜底降级的方法
     */
    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}
