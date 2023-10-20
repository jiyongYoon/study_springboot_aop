package com.example.springaop.kimteacher.internalcall;

import com.example.springaop.kimteacher.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest("spring.main.allow-circular-references=true") // 스프링부트 2.6 이후부터 순환참조가 아예 불가능하게 되어서, 가능하도록 springboot 설정값을 세팅! -> https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.6-Release-Notes
class CallServiceV1Test {

    @Autowired
    CallServiceV1 callServiceV1;

    @Test
    void external() {
        callServiceV1.external();
        /**
         * 2023-10-20 22:35:19.333  INFO 33132 --- [    Test worker] c.e.s.k.internalcall.aop.CallLogAspect   : aop=void com.example.springaop.kimteacher.internalcall.CallServiceV1.external()
         * 2023-10-20 22:35:19.345  INFO 33132 --- [    Test worker] c.e.s.k.internalcall.CallServiceV1       : call external
         * 2023-10-20 22:35:19.345  INFO 33132 --- [    Test worker] c.e.s.k.internalcall.aop.CallLogAspect   : aop=void com.example.springaop.kimteacher.internalcall.CallServiceV1.internal()
         * 2023-10-20 22:35:19.345  INFO 33132 --- [    Test worker] c.e.s.k.internalcall.CallServiceV1       : call internal
         * external() 메서드에서 internal()을 호출할 때, Proxy를 거쳐서 internal() 메서드를 호출하기 때문에 Proxy 로직(=AOP)을 거치게 된다.
         */
    }

}