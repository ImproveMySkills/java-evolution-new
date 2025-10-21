package com.improvemyskills.evolution.concurrents;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Résultat de la tâche Callable";
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new CallableExample());
        String result = future.get(); // attend et récupère le résultat
        System.out.println(result);
        executor.shutdown();
    }
}