package com.improvemyskills.evolution.datatime;

import com.improvemyskills.evolution.tp.Reservation;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

public class DateTimeExerciseTpSolution {

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

    // ===== 6. Jours entre =====
    public int daysBetween(LocalDate start, LocalDate end) {
        //return (int) java.time.temporal.ChronoUnit.DAYS.between(start, end);
        return 0;
    }

    // ===== 7. Fuseau horaire =====
    public ZonedDateTime convertToNY(LocalDateTime parisTime) {
        ZonedDateTime paris = parisTime.atZone(ZoneId.of("Europe/Paris"));
        return paris.withZoneSameInstant(ZoneId.of("America/New_York"));
    }

    // ===== 8. Aujourd’hui =====
    public LocalDate today() {
        return LocalDate.now();
    }

    // ===== 9. Prochain samedi =====
    public LocalDate nextSaturday(LocalDate date) {
        return date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }

    // ===== 10. Validation métier =====
    public boolean isValid(Reservation r) {
        Duration d = Duration.between(r.getDeparture(), r.getArrival());
        return d.toHours() > 0 && d.toHours() < 24;
    }

    // ===== 11. Moyenne durée =====
    public double avgDuration(List<Reservation> reservations) {
        return reservations.stream()
                .mapToLong(r -> Duration
                        .between(r.getDeparture(), r.getArrival())
                        .toMinutes())
                .average()
                .orElse(0);
    }

    // ===== MAIN POUR TEST =====
    public static void main(String[] args) {

        DateTimeExerciseTpSolution ex = new DateTimeExerciseTpSolution();

        Reservation r = new Reservation(
                LocalDateTime.of(2026, 6, 8, 10, 0),
                LocalDateTime.of(2026, 6, 8, 15, 30)
        );

        System.out.println("Durée: " + ex.getDuration(r)); // PT5H30M

        System.out.println("Format: " + ex.format(r.getDeparture())); // 08/06/2026 10:00

        System.out.println("Parsed: " + ex.parse("08/06/2026 14:30"));

        System.out.println("Long trip: " + ex.isLongTrip(r)); // true

        System.out.println("Delay: " + ex.addDelay(r.getDeparture()));

        System.out.println("Days between: " +
                ex.daysBetween(LocalDate.now(), LocalDate.now().plusDays(10)));

        System.out.println("Paris -> NY: " +
                ex.convertToNY(r.getDeparture()));

        System.out.println("Today: " + ex.today());

        System.out.println("Next Saturday: " +
                ex.nextSaturday(LocalDate.now()));

        System.out.println("Valid reservation: " + ex.isValid(r)); // true

        List<Reservation> list = List.of(r);
        System.out.println("Average duration: " + ex.avgDuration(list));
    }
}