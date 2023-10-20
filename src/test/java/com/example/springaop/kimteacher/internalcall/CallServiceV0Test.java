package com.example.springaop.kimteacher.internalcall;

import static org.junit.jupiter.api.Assertions.*;

import com.example.springaop.kimteacher.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0; // 프록시가 등록되게 된다. (Aspect Excution에서 패키지 대상이 되기 때문에)

    @Test
    void external() {
        callServiceV0.external();
        /**
         * 2023-10-20 22:21:25.379  INFO 30864 --- [    Test worker] c.e.s.k.internalcall.aop.CallLogAspect   : aop=void com.example.springaop.kimteacher.internalcall.CallServiceV0.external()
         * 2023-10-20 22:21:25.395  INFO 30864 --- [    Test worker] c.e.s.k.internalcall.CallServiceV0       : call external
         * 2023-10-20 22:21:25.395  INFO 30864 --- [    Test worker] c.e.s.k.internalcall.CallServiceV0       : call internal
         *
         * external() 메서드에서 internal()을 호출할 때, Proxy를 거치지 않고 바로 자신의 클래스에서 메서드를 호출하기 때문에 Proxy 로직(=AOP)을 거치지 않게 된다.
         */
    }

    @Test
    void internal() {
        callServiceV0.internal();
        /**
         * 2023-10-20 22:21:25.410  INFO 30864 --- [    Test worker] c.e.s.k.internalcall.aop.CallLogAspect   : aop=void com.example.springaop.kimteacher.internalcall.CallServiceV0.internal()
         * 2023-10-20 22:21:25.410  INFO 30864 --- [    Test worker] c.e.s.k.internalcall.CallServiceV0       : call internal
         */
    }

}