package com.improvemyskills.evolution.reactivestreams.models;

public class Product {
    String title;
    double price;
    public Product(String title, double price) { this.title = title; this.price = price; }
    @Override public String toString() { return title + " (" + price + "€)"; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
