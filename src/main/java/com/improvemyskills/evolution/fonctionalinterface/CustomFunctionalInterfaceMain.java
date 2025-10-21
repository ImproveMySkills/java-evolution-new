package com.improvemyskills.evolution.fonctionalinterface;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CustomFunctionalInterfaceMain {
    public static void main(String[] args) {
        // 1. Predicate : filtrer les noms
        List<String> noms = Arrays.asList("Ali", "Bobo", "Cissé", "Anna");
        Predicate<String> commenceParA = s -> s.startsWith("A");
        noms.stream().filter(commenceParA).forEach(System.out::println);

        // 2. Function : transformer les noms en majuscule
        Function<String, String> enMajuscules = String::toUpperCase;
        noms.stream().map(enMajuscules).forEach(System.out::println);

        // 3. Consumer : afficher les noms
        Consumer<String> afficher = s -> System.out.println("Nom : " + s);
        noms.forEach(afficher);

        // 4. Supplier : générer un UUID
        Supplier<String> generateur = () -> UUID.randomUUID().toString();
        System.out.println("UUID : " + generateur.get());

        // 5. Interface personnalisée
        CustomFunctionalInterface inverser = s -> new StringBuilder(s).reverse().toString();
        System.out.println("Inversé : " + inverser.transform("Lambda"));

        // 6. Méthode de référence
        noms.forEach(System.out::println);
    }
}