package com.bosssoft.ecds.aspect;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 请求切面
 * @author: lin.wanning
 * @date: Created in 2020/8/20 16:18
 * @modified By:
 */
@Aspect
@Component
@Slf4j(topic = "kafka_logger")
public class RequestAspect {

    // 定义切点
    @Pointcut("execution(* com.bosssoft.ecds.controller..*.*(..))")
    public void point() {
    }


    @Before("point()")
    @Trace(operationName = "requestLog")
    public void log(JoinPoint joinPoint) {
        Map<String, Object> param = this.getNameAndValue(joinPoint);
        String traceId = TraceContext.traceId();
        String paramStr = JSONUtil.toJsonStr(param);
        ActiveSpan.tag("请求参数", paramStr);
        param.put("tid", traceId);
        String jsonStr = JSONUtil.toJsonStr(param);
        log.info(jsonStr);
    }

    private Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }

}
