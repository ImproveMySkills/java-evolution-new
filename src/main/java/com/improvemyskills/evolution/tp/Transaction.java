package com.improvemyskills.evolution.tp;

public record Transaction(String id, String user, double amount, String currency, boolean fraud) {}
