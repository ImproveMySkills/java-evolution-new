package com.improvemyskills.evolution.concurrents;

import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinSumMain {
    public static void main(String[] args) {
        int[] array = new int[1000000000];
        for (int i = 0; i < array.length; i++) array[i] = i + 1;

        System.out.println("initial time 1 "+ LocalDateTime.now());
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSum task = new ForkJoinSum(array, 0, array.length);

        System.out.println("final time 1 "+ LocalDateTime.now());


        long result = pool.invoke(task);
        System.out.println("Somme du tableau : " + result);

        int sum = 0;
        System.out.println("initial time 2"+ LocalDateTime.now());
        for (int i = 0; i < array.length; i++) sum = sum + array[i] ;
        System.out.println("final time 2 "+ LocalDateTime.now());

    }
}