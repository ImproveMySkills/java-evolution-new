package com.improvemyskills.evolution.reactivestreams;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.improvemyskills.evolution.reactivestreams.models.Customer;
import com.improvemyskills.evolution.reactivestreams.models.Order;
import com.improvemyskills.evolution.reactivestreams.models.Product;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class AsyncApiReactorReal {

    static HttpClient client = HttpClient.newHttpClient();
    static ObjectMapper mapper = new ObjectMapper();

    // ===== API =====

    static Mono<List<Order>> fetchOrders() {
        return Mono.fromFuture(
                        client.sendAsync(
                                HttpRequest.newBuilder()
                                        .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                                        .GET().build(),
                                HttpResponse.BodyHandlers.ofString()
                        ))
                .map(HttpResponse::body)
                .map(body -> {
                    try {
                        JsonNode root = mapper.readTree(body);
                        List<Order> orders = new ArrayList<>();
                        for (JsonNode n : root) {
                            orders.add(new Order(n.get("id").asInt(), n.get("title").asText()));
                        }
                        return orders;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    static Mono<List<Product>> fetchProducts(List<Order> orders) {
        return Mono.fromFuture(
                        client.sendAsync(
                                HttpRequest.newBuilder()
                                        .uri(URI.create("https://fakestoreapi.com/products"))
                                        .GET().build(),
                                HttpResponse.BodyHandlers.ofString()
                        ))
                .map(HttpResponse::body)
                .map(body -> {
                    try {
                        JsonNode root = mapper.readTree(body);
                        List<Product> products = new ArrayList<>();

                        for (JsonNode n : root) {
                            String title = n.get("title").asText();
                            double price = n.get("price").asDouble();
                            products.add(new Product(title, price));

/*                            if (orders.stream()
                                    .anyMatch(o -> title.toLowerCase().contains(o.title.split(" ")[0].toLowerCase()))) {
                                products.add(new Product(title, price));
                            }*/
                        }
                        return products;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    static Mono<List<Customer>> fetchCustomers() {
        return Mono.fromFuture(
                        client.sendAsync(
                                HttpRequest.newBuilder()
                                        .uri(URI.create("https://randomuser.me/api/?results=5"))
                                        .GET().build(),
                                HttpResponse.BodyHandlers.ofString()
                        ))
                .map(HttpResponse::body)
                .map(body -> {
                    try {
                        JsonNode root = mapper.readTree(body).get("results");

                        List<Customer> list = new ArrayList<>();
                        for (JsonNode n : root) {
                            list.add(new Customer(
                                    n.get("name").get("first").asText(),
                                    n.get("email").asText()
                            ));
                        }
                        return list;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Mono<List<Order>> orders = fetchOrders();

        Mono<List<Product>> products =
                orders.flatMap(AsyncApiReactorReal::fetchProducts);

        Mono<List<Customer>> customers = fetchCustomers();

        Mono.zip(orders, products, customers)
                .map(tuple -> {
                    System.out.println("Orders: " + tuple.getT1().size());
                    System.out.println("Products: " + tuple.getT2().size());
                    System.out.println("Customers: " + tuple.getT3().size());
                    return "DONE";
                })
                .timeout(java.time.Duration.ofSeconds(5))
                .retry(1)
                .onErrorReturn("FALLBACK")
                .doFinally(s -> System.out.println(
                        "Time: " + (System.currentTimeMillis() - start)))
                .block();
    }
}