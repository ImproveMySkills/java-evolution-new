package com.improvemyskills.evolution.datatime;

import com.improvemyskills.evolution.tp.Reservation;

import java.time.*;
        import java.time.format.DateTimeFormatter;
        //import java.time.temporal.ChronoUnit;


public class DateTimeHandsOn {

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

