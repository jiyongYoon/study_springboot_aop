package com.example.springaop.kimteacher.pointcut;

/**
 * application.properties
 * spring.aop.proxy-target-class=true CGLIB
 * spring.aop.proxy-target-class=false JDK 동적 프록시
 */

import com.example.springaop.kimteacher.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") //JDK 동적 프록시
//@SpringBootTest(properties = "spring.aop.proxy-target-class=true") //CGLIB -> springboot 기본 옵션
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        // JDK 동적 프록시: memberService Proxy=class com.sun.proxy.$Proxy55
        // CGLIB 프록시: memberService Proxy=class com.example.springaop.kimteacher.member.MemberServiceImpl$$EnhancerBySpringCGLIB$$803b0fda
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {

        //부모 타입 허용
        @Around("this(com.example.springaop.kimteacher.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            // JDK 동적 프록시: [this-interface] String com.example.springaop.kimteacher.member.MemberService.hello(String)
            // CGLIB 프록시: [this-interface] String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String)
            return joinPoint.proceed();
        }

        //부모 타입 허용
        @Around("target(com.example.springaop.kimteacher.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            // JDK 동적 프록시: [target-interface] String com.example.springaop.kimteacher.member.MemberService.hello(String)
            // CGLIB 프록시: [target-interface] String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String)
            return joinPoint.proceed();
        }

        //this: 스프링 AOP 프록시 객체 대상
        //JDK 동적 프록시는 인터페이스를 기반으로 생성되므로 구현 클래스를 알 수 없음
        //CGLIB 프록시는 구현 클래스를 기반으로 생성되므로 구현 클래스를 알 수 있음
        @Around("this(com.example.springaop.kimteacher.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            // JDK 동적 프록시: log 안찍힘
            // CGLIB 프록시: [this-impl] String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String)
            return joinPoint.proceed();
        }

        //target: 실제 target 객체 대상
        @Around("target(com.example.springaop.kimteacher.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());
            // JDK 동적 프록시: [target-impl] String com.example.springaop.kimteacher.member.MemberService.hello(String)
            // CGLIB 프록시: [target-impl] String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String)
            return joinPoint.proceed();
        }
    }
}
