package com.improvemyskills.evolution.datatime;

import java.time.*;
import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;

public class DateTimeMain {
    public static void main(String[] args) {
        // 1. Création de dates
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(1990, Month.JANUARY, 1);
        System.out.println("Aujourd'hui : " + today);
        System.out.println("Date de naissance : " + birthDate);

        // 2. Calcul d'âge avec Period
        Period age = Period.between(birthDate, today);
        System.out.println("Âge : " + age.getYears() + " ans");

        // 3. Manipulation de temps
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Durée depuis 9h : " + duration.toMinutes() + " minutes");

        // 4. Date + heure + fuseau horaire
        ZonedDateTime zonedNow = ZonedDateTime.now();
        System.out.println("Date et heure avec fuseau : " + zonedNow);

        // 5. Formatage
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = zonedNow.format(formatter);
        System.out.println("Format personnalisé : " + formattedDate);

        // 6. Parsing
        String dateStr = "15/10/2025 14:30";
        LocalDateTime parsedDate = LocalDateTime.parse(dateStr, formatter);
        System.out.println("Date parsée : " + parsedDate);

        // 7. Différence en jours
/*        long daysBetween = ChronoUnit.DAYS.between(birthDate, today);
        System.out.println("Jours depuis naissance : " + daysBetween);*/
    }
}