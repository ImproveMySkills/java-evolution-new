package com.improvemyskills.evolution.concurrents.executors;

public class OrderProcessor implements Runnable {
    private final int orderId;

    public OrderProcessor(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        System.out.println("Traitement de la commande #" + orderId + " par " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // Simule un traitement
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Commande #" + orderId + " traitée.");
    }
}