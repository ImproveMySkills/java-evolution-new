package com.improvemyskills.evolution.concurrents;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample implements Runnable {
    @Override
    public void run() {
        System.out.println("Exécution d'une tâche Runnable");
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new RunnableExample());
        executor.shutdown();
    }
}