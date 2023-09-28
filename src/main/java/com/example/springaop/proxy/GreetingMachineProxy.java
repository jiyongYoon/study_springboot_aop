package com.example.springaop.proxy;

import com.example.springaop.GreetingMachine;
import com.example.springaop.IGreetingMachine;
import com.example.springaop.User;

public class GreetingMachineProxy implements IGreetingMachine {
    private GreetingMachine greetingMachine;
    public GreetingMachineProxy() {
        this.greetingMachine = new GreetingMachine();
    }

    @Override
    public void greet(User user) {
        // 부가기능은 Proxy 구현체에서 동작
        System.out.println(user.getName() + "이(가) 방문했습니다.");

        // 핵심기능은 Real Object에 위임
        greetingMachine.greet(user);
    }
}
