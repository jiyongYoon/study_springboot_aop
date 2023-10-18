package com.example.springaop.kimteacher.member;

import com.example.springaop.kimteacher.member.annotation.ClassAop;
import com.example.springaop.kimteacher.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component // AOP를 위해 빈 등록
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }
    public String internal(String param) {
        return "ok";
    }
}
