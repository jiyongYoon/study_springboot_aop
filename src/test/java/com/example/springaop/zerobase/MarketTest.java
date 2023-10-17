package com.example.springaop.zerobase;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springaop.zerobase.Market;
import com.example.springaop.zerobase.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MarketTest {

    @Autowired
    private Market market;

    @Test
    void test() {
        //given
        Market market = new Market();

        //when
        //then
        assertThat(market.getOperationTime()).isEqualTo("AM 08:00 ~ PM 08:00");
    }

    @Test
    void testVisitToStore() {
        //given
        User user = new User();
        user.setName("스프링");

        market.setVisitCountByUser(11);
        //when
        //then
        user.visitTo(market);
    }

    @Test
    void isVipUser() {
        //given
        Market market = new Market();
        market.setVisitCountByUser(15);

        //when
        boolean result = market.isVip(new User());

        //then
        assertThat(result).isTrue();
    }
}
