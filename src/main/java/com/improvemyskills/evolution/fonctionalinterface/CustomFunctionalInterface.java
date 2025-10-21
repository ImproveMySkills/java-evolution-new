package com.improvemyskills.evolution.fonctionalinterface;

@FunctionalInterface
public interface CustomFunctionalInterface {
    String transform(String input);

    default int sum(int a, int b){
        return a + b;
    }

    static int product (int a, int b){
        return a * b;
    }
}