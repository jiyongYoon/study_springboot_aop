package com.example.springaop.kimteacher.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 이 전에는 자기 자신을 다시 주입하는 등 다소 어색한 모습이었다.
 * 이번에는 구조 자체를 변경하여 순환참조와 같은 구조도 만들지 않고 적용해보자.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalService internalService;

    public void external() {
        log.info("call external");
        internalService.internal();
    }
}
