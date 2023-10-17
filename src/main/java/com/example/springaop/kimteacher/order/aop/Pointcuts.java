package com.example.springaop.kimteacher.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
public class Pointcuts {

    @Pointcut("execution(* com.example.springaop.kimteacher.order..*(..))") // 포인트컷
    public void allOrder(){} // pointcut signature

    // 트랜잭션 pointcut을 만들기 위해서 Service 계층에 도입하기 위한 pointcut 생성
    @Pointcut("execution(* *..*Service.*(..))") // 포인트컷
    public void allService(){} // pointcut signature

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}

}
