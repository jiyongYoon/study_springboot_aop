package com.example.springaop;

import com.example.springaop.aop.AlarmGreetingMachine;
import org.springframework.stereotype.Component;

@Component
public class Market extends Store {

    private String name;

    private int visitCountByUser = 0;

    public void setVisitCountByUser(int visitCountByUser) {
        this.visitCountByUser = visitCountByUser;
    }

    public String getOperationTime() {
        return "AM 08:00 ~ PM 08:00";
    }

    @AlarmGreetingMachine
    public void visitedBy(User user) {
        // 핵심기능 1
        greeting();
        visitCountByUser++;
    }

    @Override
    public boolean isVip(User user) {
        return visitCountByUser > 10;
    }

    private void greeting() {
        System.out.println("어서 오세요!");

//        프록시 패턴을 사용
//        IGreetingMachine greetingMachine = new GreetingMachineProxy();
//        greetingMachine.greet(user);

//        데코레이터 패턴을 사용
//        IGreetingMachine greetingMachine =
//            new DancingGreetingMachineDecorator(
//                new AlarmGreetingMachineDecorator(
//                    new GreetingMachine()
//                )
//            );
//        greetingMachine.greet(user);
    }
}
