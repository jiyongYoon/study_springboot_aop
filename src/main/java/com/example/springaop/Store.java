package com.example.springaop;

import com.example.springaop.decorator.AlarmGreetingMachineDecorator;
import com.example.springaop.decorator.DancingGreetingMachineDecorator;
import com.example.springaop.proxy.GreetingMachineProxy;

public class Store {

    private String name;

    public String getOperationTime() {
        return "AM 08:00 ~ PM 08:00";
    }

    public void visitedBy(User user) {
        // 핵심기능 1
        greeting(user);
    }

    private void greeting(User user) {
//        프록시 패턴을 사용
//        IGreetingMachine greetingMachine = new GreetingMachineProxy();
//        greetingMachine.greet(user);

//        데코레이터 패턴을 사용
        IGreetingMachine greetingMachine =
            new DancingGreetingMachineDecorator(
                new AlarmGreetingMachineDecorator(
                    new GreetingMachine()
                )
            );
        greetingMachine.greet(user);
    }
}
