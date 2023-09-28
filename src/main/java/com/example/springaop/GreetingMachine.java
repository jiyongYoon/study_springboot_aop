package com.example.springaop;

import com.example.springaop.IGreetingMachine;
import com.example.springaop.User;

// Real Object
public class GreetingMachine implements IGreetingMachine {

    @Override
    public void greet(User user) {
        // 핵심기능
        System.out.println("어서 오세요!");
    }
}
