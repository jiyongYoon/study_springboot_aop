package com.example.springaop;

public abstract class Store {

    abstract void visitedBy(User user);

    public abstract boolean isVip(User user);
}
