package com.example.springaop;

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
        IGreetingMachine greetingMachine = new GreetingMachineProxy();
        greetingMachine.greet(user);
    }
}
