package com.example.springaop.kimteacher.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    @Pointcut("execution(* com.example.springaop.kimteacher.order..*(..))") // 포인트컷
    private void allOrder(){} // pointcut signature

    // 트랜잭션 pointcut을 만들기 위해서 Service 계층에 도입하기 위한 pointcut 생성
    @Pointcut("execution(* *..*Service.*(..))") // 포인트컷
    private void allService(){} // pointcut signature

    @Around("allOrder()") // pointcut signature 사용
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // joinpoint 시그니쳐
        return joinPoint.proceed();
    }

    // com.example.springaop.kimteacher.order 포함 하위 패키지 && 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
