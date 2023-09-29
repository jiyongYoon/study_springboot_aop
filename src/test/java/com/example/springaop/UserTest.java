package com.example.springaop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private Market market;

    @Test
    void test() {
        //given
        User user = new User();

        //when
        //then
        assertThat(user.greeting()).isEqualTo("hello");
    }

    @Test
    void testVisitTo() {
        //given
        User user = new User();
        user.setName("김선생");
//        Store store = new Store();  // aop는 스프링 빈이 필요하기 때문에 주석 처리함

        //when
        //then
        user.visitTo(market);
    }
}
