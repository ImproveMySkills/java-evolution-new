package com.improvemyskills.evolution.tp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyExample {

    static HttpClient client = HttpClient.newHttpClient();

    static String callApi(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            var orders = scope.fork(() ->
                    callApi("https://jsonplaceholder.typicode.com/posts")
            );

            var products = scope.fork(() ->
                    callApi("https://fakestoreapi.com/products")
            );

            var customers = scope.fork(() ->
                    callApi("https://randomuser.me/api/")
            );

            // attendre que tout finisse
            scope.join();

            // si une tâche échoue → toutes annulées
            scope.throwIfFailed();

            System.out.println("Orders size: " + orders.get().length());
            System.out.println("Products size: " + products.get().length());
            System.out.println("Customers size: " + customers.get().length());
        }

        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }
}