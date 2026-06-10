package com.improvemyskills.evolution.reactivestreams.models;

public class Order {
    int id;
    String title;
    Order(int id, String title) { this.id = id; this.title = title; }
    @Override public String toString() { return "Order#" + id + " [" + title + "]"; }
}
