package com.improvemyskills.evolution.process;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcessMonitorSolution {

    // ===== 1. Lister tous les PID =====
    public List<Long> listAllProcesses() {
        return ProcessHandle.allProcesses()
                .map(ProcessHandle::pid)
                .collect(Collectors.toList());
    }

    // ===== 2. Filtrer par nom =====
    public List<Long> findProcessesByName(String name) {
        return ProcessHandle.allProcesses()
                .filter(ph -> ph.info()
                        .command()
                        .map(cmd -> cmd.toLowerCase().contains(name.toLowerCase()))
                        .orElse(false))
                .map(ProcessHandle::pid)
                .collect(Collectors.toList());
    }

    // ===== 3. CPU =====
    public List<String> listProcessesWithCpu() {
        return ProcessHandle.allProcesses()
                .map(ph -> {
                    Optional<Duration> cpu = ph.info().totalCpuDuration();

                    return "PID=" + ph.pid() +
                            " CPU=" + cpu.map(Duration::toString).orElse("unknown");
                })
                .collect(Collectors.toList());
    }

    // ===== 4. Kill process =====
    public void killProcess(long pid) {
        ProcessHandle.of(pid)
                .ifPresent(ProcessHandle::destroy);
    }

    // ===== 5. Kill all =====
    public void killAllByName(String name) {
        findProcessesByName(name)
                .forEach(this::killProcess);
    }

    // ===== MAIN =====
    public static void main(String[] args) throws Exception {

        ProcessMonitorSolution service = new ProcessMonitorSolution();

        System.out.println("=== START NOTEPAD ===");
        Process process = new ProcessBuilder("notepad.exe").start();

        long pid = process.toHandle().pid();
        System.out.println("PID lancé: " + pid);

        Thread.sleep(2000);

        System.out.println("=== ALL ===");
        service.listAllProcesses()
                .stream()
                .limit(10)
                .forEach(System.out::println);

        System.out.println("=== FIND ===");
        List<Long> pids = service.findProcessesByName("notepad");
        pids.forEach(System.out::println);

        System.out.println("=== CPU ===");
        service.listProcessesWithCpu()
                .stream()
                .limit(5)
                .forEach(System.out::println);

        Thread.sleep(5000);

        System.out.println("=== KILL ===");
        service.killAllByName("notepad");
    }
}
