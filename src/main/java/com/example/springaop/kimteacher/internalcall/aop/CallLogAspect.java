package com.example.springaop.kimteacher.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class CallLogAspect {

    @Before("execution(* com.example.springaop.kimteacher.internalcall..*.*(..))")
    public void deLog(JoinPoint joinPoint) {
        log.info("aop={}", joinPoint.getSignature());
    }
}
