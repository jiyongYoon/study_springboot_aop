package com.example.springaop.kimteacher.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    @Pointcut("execution(* com.example.springaop.kimteacher.order..*(..))") // 포인트컷
    private void allOrder(){} // pointcut signature

    @Around("allOrder()") // pointcut signature 사용
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // joinpoint 시그니쳐
        return joinPoint.proceed();
    }

}
