package com.improvemyskills.evolution.concurrents;

import java.util.concurrent.RecursiveTask;

/**
 * Cette classe calcule la somme d’un tableau d’entiers
 * en divisant récursivement le travail.
 */
public class ForkJoinSum extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10;
    private final int[] array;
    private final int start, end;

    public ForkJoinSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            ForkJoinSum left = new ForkJoinSum(array, start, mid);
            ForkJoinSum right = new ForkJoinSum(array, mid, end);
            left.fork(); // lance en parallèle
            return right.compute() + left.join(); // attend le résultat
        }
    }
}