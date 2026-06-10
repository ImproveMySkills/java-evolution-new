package com.improvemyskills.evolution.reactivestreams.models;

public class Product {
    String title;
    double price;
    Product(String title, double price) { this.title = title; this.price = price; }
    @Override public String toString() { return title + " (" + price + "€)"; }
}
