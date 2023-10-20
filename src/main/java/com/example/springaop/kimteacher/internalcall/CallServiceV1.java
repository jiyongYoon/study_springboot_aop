package com.example.springaop.kimteacher.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    // 자기 자신을 주입하여 internal()을 바로 호출하지 않고 프록시 객체를 거쳐서 오도록 한다.
    private CallServiceV1 callServiceV1;

    // setter를 사용하여 주입하면 자신 객체가 생성된 후에 setter가 동작하기 때문에 주입이 된다.
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
