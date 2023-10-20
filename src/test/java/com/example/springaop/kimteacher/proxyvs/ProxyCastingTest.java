package com.example.springaop.kimteacher.proxyvs;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.springaop.kimteacher.member.MemberService;
import com.example.springaop.kimteacher.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시 생성

        // 프록시를 인터페이스로 캐스팅 --> 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 --> 실패
        assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });
        /**
         * java.lang.ClassCastException: class com.sun.proxy.$Proxy11 cannot be cast to class com.example.springaop.kimteacher.member.MemberServiceImpl
         * (com.sun.proxy.$Proxy11 and com.example.springaop.kimteacher.member.MemberServiceImpl are in unnamed module of loader 'app')
         */
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 프록시 생성

        // 프록시를 인터페이스로 캐스팅 --> 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // CGLIB 동적 프록시를 구현 클래스로 캐스팅 시도 --> 성공
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }

}
