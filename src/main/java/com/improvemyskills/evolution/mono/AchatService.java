package com.improvemyskills.evolution.mono;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;

public class AchatService {

    // Classe Customer
    static class Customer {
        String name;
        public Customer(String name) { this.name = name; }
    }

    // Classe Product
    static class Product {
        String name;
        double price;
        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    // Méthode achat asynchrone
    public Mono<UUID> achat(Customer customer, List<Product> produits) {
        /*return Mono.fromCallable(() -> {
            // Simule un traitement (ex: calcul total, création commande)
            double total = produits.stream().mapToDouble(p -> p.price).sum();
            System.out.println("Création de la commande pour " + customer.name +
                    " | Total: " + total +
                    " | Thread: " + Thread.currentThread().getName());
            Thread.sleep(1000); // Simule un délai
            return UUID.randomUUID(); // ID de commande
        }).subscribeOn(Schedulers.boundedElastic());*/

        return Mono.fromCallable(() -> {
            double total = produits.stream().mapToDouble(p -> p.price).sum();
            return UUID.randomUUID();
        }).subscribeOn(Schedulers.parallel());
    }

    public static void main(String[] args) throws InterruptedException {
        AchatService service = new AchatService();

        Customer customer = new Customer("Ahmadou");
        List<Product> produits = List.of(
                new Product("Laptop", 1200.0),
                new Product("Mouse", 25.0)
        );

        service.achat(customer, produits)
                .doOnNext(id -> System.out.println("Commande créée avec ID: " + id))
                .subscribe();

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        // Attendre la fin (car asynchrone)
        Thread.sleep(1500);
    }
}