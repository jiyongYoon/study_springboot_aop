package com.example.springaop.zerobase;

// Real Object
public class GreetingMachine implements IGreetingMachine {

    @Override
    public void greet(User user) {
        // 핵심기능
        System.out.println("어서 오세요!");
    }
}
