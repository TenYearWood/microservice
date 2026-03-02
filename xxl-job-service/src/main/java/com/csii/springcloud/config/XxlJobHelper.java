package com.csii.springcloud.config;

import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.log.XxlJobFileAppender;
import com.xxl.job.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @description 自定义的XxlJobHelper，作用就是xxlJob输出在管理台的日志也能在本地console打印一份
 * @author: chengyu
 * @date: 2026-03-02 19:06
 */
public class XxlJobHelper extends com.xxl.job.core.context.XxlJobHelper {

    private static Logger logger = LoggerFactory.getLogger("xxl-job logger");

    public static boolean log(String appendLogPattern, Object... appendLogArguments) {
        FormattingTuple ft = MessageFormatter.arrayFormat(appendLogPattern, appendLogArguments);
        String appendLog = ft.getMessage();
        StackTraceElement callInfo = (new Throwable()).getStackTrace()[1];
        return logDetail(callInfo, appendLog);
    }

    public static boolean log(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String appendLog = stringWriter.toString();
        StackTraceElement callInfo = (new Throwable()).getStackTrace()[1];
        return logDetail(callInfo, appendLog);
    }

    private static boolean logDetail(StackTraceElement callInfo, String appendLog) {
        logger.info(appendLog);
        XxlJobContext xxlJobContext = XxlJobContext.getXxlJobContext();
        if (xxlJobContext == null) {
            return false;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(DateUtil.formatDateTime(new Date())).append(" ").append("[" + callInfo.getClassName() + "#" + callInfo.getMethodName() + "]").append("-").append("[" + callInfo.getLineNumber() + "]").append("-").append("[" + Thread.currentThread().getName() + "]").append(" ").append(appendLog != null ? appendLog : "");
            String formatAppendLog = stringBuffer.toString();
            String logFileName = xxlJobContext.getJobLogFileName();
            if (logFileName != null && logFileName.trim().length() > 0) {
                XxlJobFileAppender.appendLog(logFileName, formatAppendLog);
                return true;
            } else {
                logger.info(">>>>>>>>>>> {}", formatAppendLog);
                return false;
            }
        }
    }
}
