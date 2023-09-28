package com.example.springaop.decorator;

import com.example.springaop.IGreetingMachine;

public abstract class GreetingMachineDecorator implements IGreetingMachine {
    protected IGreetingMachine greetingMachine;

    public GreetingMachineDecorator(IGreetingMachine greetingMachine) {
        this.greetingMachine = greetingMachine;
    }

}
