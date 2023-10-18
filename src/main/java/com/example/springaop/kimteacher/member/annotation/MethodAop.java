package com.example.springaop.kimteacher.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메서드에 붙이는 어노테이션
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 살아있는 어노테이션
public @interface MethodAop {
    String value(); // 어노테이션이 가지는 변수
}
