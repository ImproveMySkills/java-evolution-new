package com.improvemyskills.evolution.reactivestreams;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.improvemyskills.evolution.reactivestreams.models.Customer;
import com.improvemyskills.evolution.reactivestreams.models.Order;
import com.improvemyskills.evolution.reactivestreams.models.Product;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;

public class AsyncApiRxJavaReal {



    static OkHttpClient client = new OkHttpClient();
    static ObjectMapper mapper = new ObjectMapper();

    // ===== HTTP CALL =====
    static String call(String url) {
        try {
            Response response = client.newCall(
                    new Request.Builder().url(url).build()
            ).execute();
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ===== API =====

    static Single<List<Order>> fetchOrders() {
        return Single.fromCallable(() -> {
            String body = call("https://jsonplaceholder.typicode.com/posts");

            JsonNode root = mapper.readTree(body);

            List<Order> orders = new ArrayList<>();
            for (JsonNode n : root) {
                orders.add(new Order(n.get("id").asInt(), n.get("title").asText()));
            }
            return orders;
        }).subscribeOn(Schedulers.io());
    }

    static Single<List<Product>> fetchProducts(List<Order> orders) {
        return Single.fromCallable(() -> {
            String body = call("https://fakestoreapi.com/products");
            JsonNode root = mapper.readTree(body);

            List<Product> products = new ArrayList<>();

            for (JsonNode n : root) {
                String title = n.get("title").asText();
                double price = n.get("price").asDouble();
                products.add(new Product(title, price));

                // dépendance métier réelle
/*                if (orders.stream().anyMatch(o -> title.toLowerCase().contains(o.title.split(" ")[0].toLowerCase()))) {
                    products.add(new Product(title, price));
                }*/
            }
            return products;
        }).subscribeOn(Schedulers.io());
    }

    static Single<List<Customer>> fetchCustomers() {
        return Single.fromCallable(() -> {
            String body = call("https://randomuser.me/api/?results=5");

            JsonNode root = mapper.readTree(body).get("results");

            List<Customer> res = new ArrayList<>();
            for (JsonNode n : root) {
                res.add(new Customer(
                        n.get("name").get("first").asText(),
                        n.get("email").asText()
                ));
            }
            return res;
        }).subscribeOn(Schedulers.io());
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Single<List<Order>> orders = fetchOrders();

        Single<List<Product>> products =
                orders.flatMap(AsyncApiRxJavaReal::fetchProducts);

        Single<List<Customer>> customers = fetchCustomers();

        Single.zip(orders, products, customers,
                        (o, p, c) -> {
                            System.out.println("Orders: " + o.size());
                            System.out.println("Products: " + p.size());
                            System.out.println("Customers: " + c.size());
                            return "DONE";
                        })
                //.timeout(java.time.Duration.ofSeconds(5))
                .retry(1)
                .onErrorReturnItem("FALLBACK")
                .blockingGet();

        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }
}