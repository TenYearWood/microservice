package com.csii.springcloud.job;

import com.csii.springcloud.config.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description com.csii.springcloud.job
 * @author: chengyu
 * @date: 2026-01-04 20:33
 */
@Component
public class TestXxlJob {

    private static final Logger log = LoggerFactory.getLogger(TestXxlJob.class);

    @XxlJob("testHandler")
    public void testHandler() {
        XxlJobHelper.log("开始执行测试任务...入参:{}", XxlJobHelper.getJobParam());
        XxlJobHelper.log("start.... ");
        XxlJobHelper.handleSuccess("本次测试任务调度成功");
    }
}
