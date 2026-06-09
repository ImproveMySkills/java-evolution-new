package com.improvemyskills.evolution.process;

import java.util.List;

public class ProcessMonitor {

    public static void main(String[] args) throws Exception {

        System.out.println("=== START NOTEPAD ===");
        Process process = new ProcessBuilder("notepad.exe").start();

        ProcessHandle handle = process.toHandle();

        System.out.println("Started PID = " + handle.pid());

        // monitoring async
        handle.onExit().thenAccept(ph ->
                System.out.println("Process terminé: " + ph.pid()));

        Thread.sleep(2000);

        System.out.println("=== LIST PROCESSES ===");
        ProcessHandle.allProcesses()
                .limit(10)
                .forEach(ph -> System.out.println(ph.pid()));

        System.out.println("=== FIND NOTEPAD ===");
        List<ProcessHandle> notepads =
                ProcessHandle.allProcesses()
                        .filter(ph -> ph.info()
                                .command()
                                .map(cmd -> cmd.contains("notepad"))
                                .orElse(false))
                        .toList();

        notepads.forEach(ph -> System.out.println("Found: " + ph.pid()));

        Thread.sleep(5000);

        System.out.println("=== KILL NOTEPAD ===");
        notepads.forEach(ProcessHandle::destroy);

        Thread.sleep(2000);

        System.out.println("=== END ===");
    }
}
