package com.example.springaop.kimteacher.proxyvs;

import com.example.springaop.kimteacher.member.MemberService;
import com.example.springaop.kimteacher.member.MemberServiceImpl;
import com.example.springaop.kimteacher.proxyvs.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false", "spring.main.allow-circular-references=true"}) // JDK 동적 프록시, 순환참조 가능 설정
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true", "spring.main.allow-circular-references=true"}) // CGLIB 프록시, 순환참조 가능 설정
@SpringBootTest("spring.main.allow-circular-references=true") // springboot가 CGLIB를 기본으로 채택함
@Import(ProxyDiAspect.class)
public class ProxyDiTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        /**
         * [JDK 동적 프록시]
         * 캐스팅이 불가능해서 주입을 받지 못한다.
         * org.springframework.beans.factory.UnsatisfiedDependencyException:
         * Error creating bean with name 'com.example.springaop.kimteacher.proxyvs.ProxyDiTest':
         * Unsatisfied dependency expressed through field 'memberServiceImpl';
         * nested exception is org.springframework.beans.factory.BeanNotOfRequiredTypeException:
         * Bean named 'memberServiceImpl' is expected to be of type 'com.example.springaop.kimteacher.member.MemberServiceImpl'
         * but was actually of type 'com.sun.proxy.$Proxy56'
         */

        /**
         * [CGLIB 프록시]
         * 캐스팅이 가능해서 주입을 받았다.
         * 2023-10-20 23:12:19.662  INFO 26244 --- [    Test worker] c.e.s.kimteacher.proxyvs.ProxyDiTest     : memberService class=class com.example.springaop.kimteacher.member.MemberServiceImpl$$EnhancerBySpringCGLIB$$b0b7b542
         * 2023-10-20 23:12:19.662  INFO 26244 --- [    Test worker] c.e.s.kimteacher.proxyvs.ProxyDiTest     : memberServiceImpl class=class com.example.springaop.kimteacher.member.MemberServiceImpl$$EnhancerBySpringCGLIB$$b0b7b542
         * 2023-10-20 23:12:19.667  INFO 26244 --- [    Test worker] c.e.s.k.proxyvs.code.ProxyDiAspect       : [proxyDiAdvice] String com.example.springaop.kimteacher.member.MemberServiceImpl.hello(String)
         */
        memberServiceImpl.hello("hello");
    }
}
