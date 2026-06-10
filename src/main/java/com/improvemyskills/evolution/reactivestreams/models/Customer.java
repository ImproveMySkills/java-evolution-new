package com.improvemyskills.evolution.reactivestreams.models;

public class Customer {
    String name;
    String email;
    Customer(String name, String email) { this.name = name; this.email = email; }
    @Override public String toString() { return name + " <" + email + ">"; }
}
