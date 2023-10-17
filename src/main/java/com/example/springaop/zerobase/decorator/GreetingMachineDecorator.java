package com.example.springaop.zerobase.decorator;

import com.example.springaop.zerobase.IGreetingMachine;

public abstract class GreetingMachineDecorator implements IGreetingMachine {
    protected IGreetingMachine greetingMachine;

    public GreetingMachineDecorator(IGreetingMachine greetingMachine) {
        this.greetingMachine = greetingMachine;
    }

}
