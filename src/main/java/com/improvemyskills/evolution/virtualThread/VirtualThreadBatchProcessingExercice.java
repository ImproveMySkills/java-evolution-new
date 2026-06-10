package com.improvemyskills.evolution.virtualThread;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.improvemyskills.evolution.reactivestreams.models.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class VirtualThreadBatchProcessingExercice {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    // ===== Appel externe (bloquant simulé réel HTTP) =====
    static void callApi() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/"))
                .GET()
                .build();

        HttpResponse<String> responseOrder =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = mapper.readTree(responseOrder.body());
        List<Order> orders = new ArrayList<>();

        for (JsonNode node : root) {
            int id = node.get("id").asInt();
            String title = node.get("title").asText();
            orders.add(new Order(id, title));
        }

        System.out.println("=== Orders ==="+ orders.size());

        /**
         * Customer
         */

        // TODO

        System.out.println("=== Customers ==="+ orders.size());
        /**
         * Products
         */

        // TODO

        System.out.println("=== Products ==="+ orders.size());
    }

    public static void main(String[] args) {
        try (ExecutorService executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()){
            long start = System.currentTimeMillis();

            callApi();

            System.out.println("Time: " + (System.currentTimeMillis() - start));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
