package com.csii.springcloud.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description com.csii.springcloud.job
 * @author: chengyu
 * @date: 2026-01-04 20:33
 */
@Component
@Slf4j
public class TestXxlJob {

    @XxlJob("testHandler")
    public void testHandler() {
        log.info("开始执行测试任务...入参:{}", XxlJobHelper.getJobParam());
        XxlJobHelper.log("start.... ");
        XxlJobHelper.handleSuccess("本次测试任务调度成功");
    }
}
