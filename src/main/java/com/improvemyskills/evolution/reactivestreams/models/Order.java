package com.improvemyskills.evolution.reactivestreams.models;

public class Order {
    int id;
    String title;
    public Order(int id, String title) { this.id = id; this.title = title; }
    @Override public String toString() { return "Order#" + id + " [" + title + "]"; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
