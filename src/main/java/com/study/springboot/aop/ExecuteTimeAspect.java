package com.study.springboot.aop;

import cn.hutool.core.util.StrUtil;
import com.study.springboot.annotation.ExecuteTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecuteTimeAspect {

    @Around("@annotation(executeTime)")
    public Object logExecuteTime(ProceedingJoinPoint joinPoint, ExecuteTime executeTime) throws Throwable {

        String desc = executeTime.desc();
        if (StrUtil.isBlank(desc)){
            desc = joinPoint.getSignature().getDeclaringType().getSimpleName() + " | " + joinPoint.getSignature().getName();
        }

        log.info("-------------------------------------------------------");
        long begin = System.currentTimeMillis();
        Object res = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("{} | cost time | {} ms",desc,(end - begin));
        log.info("-------------------------------------------------------");

        return res;
    }

}
