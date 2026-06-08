package com.improvemyskills.evolution.utils;

import com.improvemyskills.evolution.tp.Transaction;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class DataGenerator {

    private static final List<String> USERS = List.of("Alice", "Bob", "Charlie", "David", "Eve");
    private static final List<String> CURRENCIES = List.of("EUR", "USD", "XOF");

    public static List<Transaction> generate(int size) {
        Random random = new Random();

        return IntStream.range(0, size)
                .mapToObj(i -> new Transaction(
                        UUID.randomUUID().toString(),
                        USERS.get(random.nextInt(USERS.size())),
                        random.nextDouble() * 10_000,  // montant réaliste
                        CURRENCIES.get(random.nextInt(CURRENCIES.size())),
                        random.nextDouble() < 0.05     // 5% fraude
                ))
                .toList();
    }
}
