package com.example.springaop;

import com.example.springaop.aop.AlarmGreetingMachine;
import org.springframework.stereotype.Component;

@Component
public class Library extends Store {

    private String name;
    private int visitCountByUser = 0;

    public void setVisitCountByUser(int visitCountByUser) {
        this.visitCountByUser = visitCountByUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @AlarmGreetingMachine
    public void visitedBy(User user) {
        System.out.println("환영합니다! " + getName() + " 입니다.");
    }

    @Override
    public boolean isVip(User user) {
        return visitCountByUser > 10;
    }
}
