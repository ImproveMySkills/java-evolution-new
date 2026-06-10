package com.improvemyskills.evolution.virtualThread;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class VirtualThreadBatchProcessing {

    private static final HttpClient client = HttpClient.newHttpClient();

    // ===== Appel externe (bloquant simulé réel HTTP) =====
    static String callApi(int i) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + (i % 100 + 1)))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return "Task " + i + " OK (size=" + response.body().length() + ")";

        } catch (Exception e) {
            return "Task " + i + " ERROR";
        }
    }

    // ===== TRAITEMENT BATCH =====
    public static void main(String[] args) throws Exception {

        int NB_TASKS = 1000; // tu peux monter à 10 000+

        long start = System.currentTimeMillis();

        try (ExecutorService executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<String>> futures = new ArrayList<>();

            for (int i = 0; i < NB_TASKS; i++) {
                int id = i;

                futures.add(
                        executor.submit(() -> callApi(id))
                );
            }

            // récupérer résultats
            for (Future<String> f : futures) {
                System.out.println(f.get());
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("Total time: " + (end - start) + " ms");
    }
}