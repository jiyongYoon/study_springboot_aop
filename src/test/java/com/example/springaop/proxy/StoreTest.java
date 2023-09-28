package com.example.springaop.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springaop.Store;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreTest {

    @Test
    void test() {
        //given
        Store store = new Store();

        //when
        //then
        assertThat(store.getOperationTime()).isEqualTo("AM 08:00 ~ PM 08:00");
    }
}
