package com.example.springaop.zerobase.aop;


import com.example.springaop.zerobase.Store;
import com.example.springaop.zerobase.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // spring에게 해당 클래스가 Aspect 클래스임을 알려줌
@Component // Bean에 등록되어야 함
public class AlarmGreetingMachineAspect {

    @Before(value = "@annotation(com.example.springaop.zerobase.aop.AlarmGreetingMachine)") // 해당 어노테이션이 붙은 메서드가 Target이 된다.
    public void alarm(JoinPoint joinPoint) {
        Store target = (Store) joinPoint.getTarget();

        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];

        if (target.isVip(user)) {
            System.out.println("VIP 유저입니다. 사은품을 받아가세요!");
        }

        System.out.println(user.getName() + "이(가) 방문했습니다."); // aop에서도 해당 객체를 받아서 동적으로 사용할 수 있어야 한다. 이 때, Reflaction 기능이 필요하다.
    }
}
