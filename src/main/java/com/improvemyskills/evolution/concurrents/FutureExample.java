package com.improvemyskills.evolution.concurrents;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000); // Simule un traitement
            return "Résultat calculé";
        };

        Future<String> future = executor.submit(task);

        System.out.println("Tâche soumise, en attente du résultat...");

        // get() bloque jusqu’à ce que le résultat soit prêt
        String result = future.get();
        System.out.println("Résultat : " + result);

        executor.shutdown();
    }
}