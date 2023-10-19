package com.example.springaop.kimteacher.pointcut;

import com.example.springaop.kimteacher.member.MemberService;
import com.example.springaop.kimteacher.member.annotation.ClassAop;
import com.example.springaop.kimteacher.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ParameterTest.ParameterAspect.class)
@SpringBootTest
public class ParameterTest {
    @Autowired
    MemberService memberService;
    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        // memberService Proxy=class com.example.springaop.kimteacher.member.MemberServiceImpl$$EnhancerBySpringCGLIB$$a7c56d28
        memberService.hello("helloA");
    }
    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* com.example.springaop.kimteacher.member..*.*(..))")
        private void allMember() {}
        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(), arg1);
            // [logArgs1]String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String), arg=helloA
            return joinPoint.proceed();
        }
        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2]{}, arg={}", joinPoint.getSignature(), arg);
            // [logArgs2]String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String), arg=helloA
            return joinPoint.proceed();
        }
        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] arg={}", arg);
            // [logArgs3] arg=helloA
        }
        @Before("allMember() && this(obj)")
        public void thisArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[this]{}, obj={}", joinPoint.getSignature(), obj.getClass());
            // [this]String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String), obj=class com.example.springaop.kimteacher.member.MemberServiceImpl$$EnhancerBySpringCGLIB$$a7c56d28
        }
        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[target]{}, obj={}", joinPoint.getSignature(), obj.getClass());
            // [target]String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String), obj=class com.example.springaop.kimteacher.member.MemberServiceImpl
        }
        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target]{}, obj={}", joinPoint.getSignature(), annotation);
            // [@target]String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String), obj=@com.example.springaop.kimteacher.member.annotation.ClassAop()
        }
        @Before("allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@within]{}, obj={}", joinPoint.getSignature(), annotation);
            // [@within]String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String), obj=@com.example.springaop.kimteacher.member.annotation.ClassAop()
        }
        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation) {
            log.info("[@annotation]{}, annotationValue={}", joinPoint.getSignature(), annotation.value());
            // [@annotation]String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String), annotationValue=test value
        }
    }
}
