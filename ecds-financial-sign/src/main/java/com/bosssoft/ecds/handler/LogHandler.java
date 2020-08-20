package com.bosssoft.ecds.handler;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/13
 * @Content: 统一日志处理类
 */
@Aspect
@Component
@Slf4j
public class LogHandler {

    @Pointcut("execution(* com.bosssoft.ecds.controller.*.*(..))")
    private void pt1(){}

    @Pointcut("execution(* com.bosssoft.ecds.service.*.*(..))")
    private void pt2(){}

    /**
     * 日志处理类
     *      pjp.proceed(args) 异常要抛出，因为此类优先级高于全局异常处理类
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pt1() || pt2()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        // 1.获取访问时间
        String visitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(new Date());
        // 2.包名。类名
        // executionClass切入点类别  executionMethod切入点方法
        Class executionClass = pjp.getTarget().getClass();
        String className = executionClass.getName();
        Method executionMethod =null;
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        String argsStr = "void";
        // 获取切入点方法参数
        if (args != null || args.length != 0) {
            JSON argsJson = JSONUtil.parse(args);
            argsStr = argsJson.toString();
        }
        Long executionTime = 0L;
        Object returnObject = null;
        //4.获取执行时长与应答内容
        Long startTime = System.currentTimeMillis();
        returnObject = pjp.proceed(args);
        executionTime = System.currentTimeMillis()-startTime;
        log.info("["+visitTime +"]\tpackage: " +className+"\tmethod: "+methodName+"\targs: "+argsStr+"\texecutionTime: "+executionTime+"\treturnObject"+returnObject);
        return returnObject;
    }
}
