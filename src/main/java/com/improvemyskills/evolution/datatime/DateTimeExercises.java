package com.improvemyskills.evolution.datatime;

import java.time.*;
        import java.time.format.DateTimeFormatter;
        //import java.time.temporal.ChronoUnit;
        import java.time.temporal.TemporalAdjusters;
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
        return Duration.between(r.getDeparture(), r.getArrival());
    }

    // ===== 2. Format =====
    public String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

    // ===== 3. Parsing =====
    public LocalDateTime parse(String input) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    // ===== 4. Long trajet =====
    public boolean isLongTrip(Reservation r) {
        return Duration.between(r.getDeparture(), r.getArrival())
                .toHours() > 3;
    }

    // ===== 5. Ajouter délai =====
    public LocalDateTime addDelay(LocalDateTime dt) {
        return dt.plusDays(2);
    }
}

