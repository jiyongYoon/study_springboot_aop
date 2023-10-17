package com.example.springaop.zerobase.decorator;

import com.example.springaop.zerobase.IGreetingMachine;
import com.example.springaop.zerobase.User;

public class DancingGreetingMachineDecorator extends GreetingMachineDecorator {

    public DancingGreetingMachineDecorator(IGreetingMachine greetingMachine) {
        super(greetingMachine);
    }

    @Override
    public void greet(User user) {
        // 기존 로직
        greetingMachine.greet(user);

        // 댄싱 기능 추가
        dance();
    }

    private void dance() {
        System.out.println("(손님이 자리에 앉을 때 까지 춤을 춘다.)");
    }
}
