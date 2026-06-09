package com.improvemyskills.evolution.tp;


import com.improvemyskills.evolution.utils.DataGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalysis {

    public static void main(String[] args) {

        List<Transaction> transactions = DataGenerator.generate(1_000_000);

        // 1. Filtrer les transactions non frauduleuses
        List<Transaction> validTransactions = transactions.stream()
                .filter(t -> !t.fraud())
                .toList();

        // 2. Grouper par utilisateur
        Map<String, List<Transaction>> byUser = validTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::user));

        // 3. Total et moyenne par utilisateur
        Map<String, DoubleSummaryStatistics> statsByUser =
                validTransactions.stream()
                        .collect(Collectors.groupingBy(
                                Transaction::user,
                                Collectors.summarizingDouble(Transaction::amount)
                        ));

        // Avec EntrySet

/*        Map<String, Double> totalByUser =
                byUser.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue()
                                        .stream()
                                        .mapToDouble(Transaction::amount)
                                        .sum()
                        ));*/

        // groupingBy vs groupingByConcurrent

/*        Map<String, DoubleSummaryStatistics> statsByUser =
                validTransactions.parallelStream()
                        .collect(Collectors.groupingByConcurrent(
                                Transaction::user,
                                Collectors.summarizingDouble(Transaction::amount)
                        ));*/

        // affichage
        statsByUser.forEach((user, stats) -> {
            System.out.println(user +
                    " -> total: " + stats.getSum() +
                    ", moyenne: " + stats.getAverage());
        });

        // 4. Transaction maximale
        Optional<Transaction> maxTransaction =
                validTransactions.stream()
                        .max(Comparator.comparingDouble(Transaction::amount));

        maxTransaction.ifPresent(t ->
                System.out.println("Max transaction: " + t)
        );

        // 5. Utilisateurs suspects (> 50 000 total)
        double THRESHOLD = 50_000;

        List<String> suspiciousUsers =
                statsByUser.entrySet()
                        .stream()
                        .filter(e -> e.getValue().getSum() > THRESHOLD)
                        .map(Map.Entry::getKey)
                        .toList();

        System.out.println("Utilisateurs suspects: " + suspiciousUsers);

        // Java 12

        Map<String, Object> result =
                validTransactions.stream()
                        .collect(Collectors.teeing(
                                Collectors.maxBy(Comparator.comparingDouble(Transaction::amount)),
                                Collectors.counting(),
                                (max, count) -> Map.of(
                                        "maxTransaction", max.orElse(null),
                                        "count", count
                                )
                        ));

    }
}
