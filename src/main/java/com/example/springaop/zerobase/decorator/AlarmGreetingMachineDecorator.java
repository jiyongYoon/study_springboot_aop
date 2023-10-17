package com.example.springaop.zerobase.decorator;

import com.example.springaop.zerobase.IGreetingMachine;
import com.example.springaop.zerobase.User;

public class AlarmGreetingMachineDecorator extends GreetingMachineDecorator {

    public AlarmGreetingMachineDecorator(IGreetingMachine greetingMachine) {
        super(greetingMachine);
    }

    @Override
    public void greet(User user) {
        // 알람기능 추가
        alarm(user);

        // 부모의 greet 메서드 호출
        greetingMachine.greet(user);
    }

    private void alarm(User user) {
        System.out.println(user.getName() + "이(가) 방문했습니다.");
    }
}
