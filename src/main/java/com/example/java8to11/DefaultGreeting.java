package com.example.java8to11;

public class DefaultGreeting implements GreetingInterface {
    @Override
    public void printName() {
        System.out.println("DefaultGreeting");
    }
}
