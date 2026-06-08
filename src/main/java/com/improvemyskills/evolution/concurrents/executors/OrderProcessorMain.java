package com.improvemyskills.evolution.concurrents.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Utiliser ExecutorService pour traiter des commandes en parallèle
 * Utiliser ScheduledExecutorService pour envoyer des notifications périodiques
 */
public class OrderProcessorMain {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Traitement de commandes
        for (int i = 1; i <= 5; i++) {
            executor.submit(new OrderProcessor(i));
        }

        // Envoi de notifications toutes les 2 secondes
/*
        scheduler.scheduleAtFixedRate(new NotificationService(), 0, 2, TimeUnit.SECONDS);

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
*/

        scheduler.shutdown();
    }
}