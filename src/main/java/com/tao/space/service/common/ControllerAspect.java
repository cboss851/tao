package com.tao.space.service.common;

import com.tao.commons.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Controller层的日志接口
 *
 * @Author：cboss
 * @Date：2023/3/26
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {
    @Around("execution(* com.tao.space.controller..*.*(..))")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = proceedingJoinPoint.proceed();
        if (res instanceof ResponseResult) {
            ResponseResult responseResult = (ResponseResult) res;
            responseResult.setRequestId(RequestIdService.get());
            responseResult.setExecutionTime(System.currentTimeMillis() - start);
        }
        return res;
    }
}
