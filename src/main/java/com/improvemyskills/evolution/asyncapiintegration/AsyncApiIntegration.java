package com.improvemyskills.evolution.asyncapiintegration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


class Customer {
    String name;
    String email;
    Customer(String name, String email) { this.name = name; this.email = email; }
    @Override public String toString() { return name + " <" + email + ">"; }
}

class Product {
    String title;
    double price;
    Product(String title, double price) { this.title = title; this.price = price; }
    @Override public String toString() { return title + " (" + price + "€)"; }
}

class Order {
    int id;
    String title;
    Order(int id, String title) { this.id = id; this.title = title; }
    @Override public String toString() { return "Order#" + id + " [" + title + "]"; }
}

public class AsyncApiIntegration {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    // Appel API pour récupérer des clients
    static CompletableFuture<List<Customer>> fetchCustomers() {
        return client.sendAsync(HttpRequest.newBuilder()
                                .uri(URI.create("https://randomuser.me/api/?results=5"))
                                .GET().build(),
                        HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    try {
                        JsonNode root = mapper.readTree(body).get("results");
                        List<Customer> customers = new ArrayList<>();
                        for (JsonNode node : root) {
                            String name = node.get("name").get("first").asText() + " " +
                                    node.get("name").get("last").asText();
                            String email = node.get("email").asText();
                            customers.add(new Customer(name, email));
                        }
                        return customers;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    // Appel API pour récupérer des produits
    static CompletableFuture<List<Product>> fetchProducts() {
        return client.sendAsync(HttpRequest.newBuilder()
                                .uri(URI.create("https://fakestoreapi.com/products"))
                                .GET().build(),
                        HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    try {
                        JsonNode root = mapper.readTree(body);
                        List<Product> products = new ArrayList<>();
                        for (JsonNode node : root) {
                            String title = node.get("title").asText();
                            double price = node.get("price").asDouble();
                            products.add(new Product(title, price));
                        }
                        return products;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    // Appel API pour récupérer des commandes
    static CompletableFuture<List<Order>> fetchOrders() {
        return client.sendAsync(HttpRequest.newBuilder()
                                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                                .GET().build(),
                        HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    try {
                        JsonNode root = mapper.readTree(body);
                        List<Order> orders = new ArrayList<>();
                        for (JsonNode node : root) {
                            int id = node.get("id").asInt();
                            String title = node.get("title").asText();
                            orders.add(new Order(id, title));
                        }
                        return orders.subList(0, 5); // Limiter à 5 commandes
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static void main(String[] args) {
        CompletableFuture<List<Customer>> customersFuture = fetchCustomers();
        CompletableFuture<List<Product>> productsFuture = fetchProducts();
        CompletableFuture<List<Order>> ordersFuture = fetchOrders();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(customersFuture, productsFuture, ordersFuture);

        allFutures.thenRun(() -> {
            try {
                System.out.println("=== Clients ===");
                customersFuture.get().forEach(System.out::println);

                System.out.println("\n=== Produits ===");
                productsFuture.get().forEach(System.out::println);

                System.out.println("\n=== Commandes ===");
                ordersFuture.get().forEach(System.out::println);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}