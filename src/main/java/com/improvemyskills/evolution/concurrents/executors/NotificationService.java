package com.improvemyskills.evolution.concurrents.executors;

public class NotificationService implements Runnable {
    @Override
    public void run() {
        System.out.println("📢 Notification envoyée à " + java.time.LocalTime.now());
    }
}