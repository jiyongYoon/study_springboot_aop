package com.example.springaop.kimteacher.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    // 스프링 컨텍스트에서 꺼내와서 주입받기 위함. 그러나 이걸 위해 ApplicationContext를 불러오기에, 해당 객체는 너무 무거운 객체다.
//    private final ApplicationContext applicationContext;

    // 컨테이너에서 해당 타입 객체만 가져오는 기능을 활용하자.
    private final ObjectProvider<CallServiceV2> callServiceV2ObjectProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceV2ObjectProvider) {
        this.callServiceV2ObjectProvider = callServiceV2ObjectProvider;
    }

    public void external() {
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceV2ObjectProvider.getObject();
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        callServiceV2.internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
