package com.example.springaop;

public class User {
    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String greeting() {
        return "hello";
    }

    public void visitTo(Market market) {
        // store에 user 방문을 알림
        market.visitedBy(this);
    }

    public void visitTo(Library library) {
        library.visitedBy(this);
    }
}
