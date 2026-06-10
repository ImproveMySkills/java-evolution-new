package com.improvemyskills.evolution.tp;


import java.util.List;

/**
 * TP : Mini “Process Monitor & Killer” (Java 9+)
 */

public class ProcessMonitorExercise {

    // ===== 1. Lister tous les PID =====
    public List<Long> listAllProcesses() {
        // TODO: retourner la liste des PID de tous les processus
        return null;
    }

    // ===== 2. Filtrer par nom =====
    public List<Long> findProcessesByName(String name) {
        // TODO:
        // filtrer les processus contenant "name" dans la commande
        return null;
    }

    // ===== 3. Récupérer CPU =====
    public List<String> listProcessesWithCpu() {
        // TODO:
        // afficher PID + cpuTime si présent
        // format: "PID=123 CPU=PT0.01S"
        return null;
    }

    // ===== 4. Kill process =====
    public void killProcess(long pid) {
        // TODO:
        // récupérer le ProcessHandle et appeler destroy()
    }

    // ===== 5. Kill tous les processus correspondants =====
    public void killAllByName(String name) {
        // TODO:
        // réutiliser findProcessesByName et killProcess
    }

    // ===== 6. MAIN TEST =====
    public static void main(String[] args) throws Exception {

        ProcessMonitorExercise service = new ProcessMonitorExercise();

        System.out.println("=== START NOTEPAD ===");
        Process process = new ProcessBuilder("notepad.exe").start();
        long pid = process.toHandle().pid();
        System.out.println("PID lancé: " + pid);

        Thread.sleep(2000);

        System.out.println("=== LIST ALL ===");
        service.listAllProcesses()
                .stream()
                .limit(10)
                .forEach(System.out::println);

        System.out.println("=== FIND NOTEPAD ===");
        List<Long> pids = service.findProcessesByName("notepad");
        pids.forEach(System.out::println);

        System.out.println("=== CPU INFO ===");
        service.listProcessesWithCpu()
                .stream()
                .limit(5)
                .forEach(System.out::println);

        Thread.sleep(5000);

        System.out.println("=== KILL ===");
        service.killAllByName("notepad");
    }
}
