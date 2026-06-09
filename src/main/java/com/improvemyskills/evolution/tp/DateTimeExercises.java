package com.improvemyskills.evolution.tp;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateTimeExercises {

    // ===== Classe métier =====
    public static class Reservation {
        private LocalDateTime departure;
        private LocalDateTime arrival;

        public Reservation(LocalDateTime departure, LocalDateTime arrival) {
            this.departure = departure;
            this.arrival = arrival;
        }

        public LocalDateTime getDeparture() {
            return departure;
        }

        public LocalDateTime getArrival() {
            return arrival;
        }
    }

    // ===== 1. Durée =====
    public Duration getDuration(Reservation r) {
        // TODO: retourner la durée entre departure et arrival
        return null;
    }

    // ===== 2. Format =====
    public String format(LocalDateTime dateTime) {
        // TODO: formatter en "dd/MM/yyyy HH:mm"
        return null;
    }

    // ===== 3. Parsing =====
    public LocalDateTime parse(String input) {
        // TODO: parser "dd/MM/yyyy HH:mm"
        return null;
    }

    // ===== 4. Long trajet =====
    public boolean isLongTrip(Reservation r) {
        // TODO: retourner true si durée > 3h
        return false;
    }

    // ===== 5. Ajouter délai =====
    public LocalDateTime addDelay(LocalDateTime dt) {
        // TODO: ajouter 2 jours
        return null;
    }

    // ===== 6. Jours entre =====
    public int daysBetween(LocalDate start, LocalDate end) {
        // TODO: retourner le nombre de jours entre 2 dates
        return 0;
    }

    // ===== 7. Fuseau horaire =====
    public ZonedDateTime convertToNY(LocalDateTime parisTime) {
        // TODO: convertir Europe/Paris vers America/New_York
        return null;
    }

    // ===== 8. Aujourd’hui =====
    public LocalDate today() {
        // TODO: retourner la date du jour
        return null;
    }

    // ===== 9. Prochain samedi =====
    public LocalDate nextSaturday(LocalDate date) {
        // TODO: retourner le prochain samedi
        return null;
    }

    // ===== 10. Validation métier =====
    public boolean isValid(Reservation r) {
        // TODO:
        // - arrivée après départ
        // - durée < 24h
        return false;
    }

    // ===== 11. Moyenne durée =====
    public double avgDuration(List<Reservation> reservations) {
        // TODO: moyenne des durées en minutes
        return 0;
    }

    // ===== MAIN POUR TEST =====
    public static void main(String[] args) {

        DateTimeExercises ex = new DateTimeExercises();

        Reservation r = new Reservation(
                LocalDateTime.of(2026, 6, 8, 10, 0),
                LocalDateTime.of(2026, 6, 8, 15, 30)
        );

        System.out.println("Durée: " + ex.getDuration(r));
        System.out.println("Format: " + ex.format(r.getDeparture()));
        System.out.println("Parsed: " + ex.parse("08/06/2026 14:30"));
        System.out.println("Long trip: " + ex.isLongTrip(r));
        System.out.println("Delay: " + ex.addDelay(r.getDeparture()));
        System.out.println("Days between: " +
                ex.daysBetween(LocalDate.now(), LocalDate.now().plusDays(10)));
        System.out.println("Paris -> NY: " + ex.convertToNY(r.getDeparture()));
        System.out.println("Today: " + ex.today());
        System.out.println("Next Saturday: " +
                ex.nextSaturday(LocalDate.now()));
        System.out.println("Valid reservation: " + ex.isValid(r));

        List<Reservation> list = List.of(r);
        System.out.println("Average duration: " + ex.avgDuration(list));
    }
}
