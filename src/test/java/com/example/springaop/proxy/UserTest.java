package com.example.springaop.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springaop.Store;
import com.example.springaop.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

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
        user.setName("홍길동");
        Store store = new Store();

        //when
        //then
        user.visitTo(store);
    }
}
