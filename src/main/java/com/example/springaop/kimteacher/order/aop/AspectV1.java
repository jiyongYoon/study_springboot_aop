package com.example.springaop.kimteacher.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Spring AOP는 실제 AspectJ를 직접 사용하는 것이 아니라, AspectJ의 문법을 차용해서 사용하는 것이다.
 * spring AOP는 프록시 방식의 AOP를 사용한다.
 */
@Slf4j
@Aspect
public class AspectV1 {

    @Around("execution(* com.example.springaop.kimteacher.order..*(..))") // 포인트컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // joinpoint 시그니쳐
        return joinPoint.proceed();
    }

}
