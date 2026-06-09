package com.improvemyskills.evolution.datatime;

import java.time.*;

public class Reservation {

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