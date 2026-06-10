package com.improvemyskills.evolution.process;

import java.util.List;

public class ProcessBeforeVsAfterJava9 {

    public static void main(String[] args) {

        /**
         * Get PID
         */

        ProcessHandle processHandle = ProcessHandle.current();
        long pid = processHandle.pid();

        System.out.println("PID = " + pid);

        /**
         * Get All Processes : same as PS or TASKLIST
         */

        ProcessHandle.allProcesses()
                .forEach(ph -> System.out.println(ph.pid()));

        /**
         * Informations sur un process
         *
         * ProcessHandle.Info
         * Permet d’accéder à :
         *
         * commande
         * arguments
         * utilisateur
         * date de lancement
         * CPU time
         */

        ProcessHandle.current()
                .info()
                .command()
                .ifPresent(System.out::println);

        /**
         * Hierachy Parent / Child
         */

        ProcessHandle.current()
                .parent()
                .ifPresent(parent -> System.out.println("Parent PID: " + parent.pid()));

        ProcessHandle.current().children();
        ProcessHandle.current().descendants();


        /**
         *  Kill a Process
         */

        ProcessHandle ph = ProcessHandle.current();

        ph.destroy();        // kill normal
        ph.destroyForcibly(); // kill -9

        /**
         *  Wait till the end (async), based on CompletableFuture
         */

        ProcessHandle.current()
                .onExit()
                .thenRun(() -> System.out.println("Process terminé"));
    }

    /**
     * 1. Lister les processus actifs
     */
    public static void listProcesses() {
        ProcessHandle.allProcesses()
                .limit(20) // limiter pour affichage
                .forEach(ph -> {
                    ProcessHandle.Info info = ph.info();

                    System.out.println(
                            "PID=" + ph.pid()
                                    + " | CMD=" + info.command().orElse("unknown")
                                    + " | USER=" + info.user().orElse("unknown")
                    );
                });
    }

    /**
     * Filtrer les processus Windows (ex: notepad)
     * @return
     */
    public static List<ProcessHandle> findNotepadProcesses() {
        return ProcessHandle.allProcesses()
                .filter(ph -> ph.info()
                        .command()
                        .map(cmd -> cmd.toLowerCase().contains("notepad"))
                        .orElse(false))
                .toList();
    }

    /**
     * Démarrer un process Windows
     * @return
     * @throws Exception
     */
    public static Process startNotepad() throws Exception {
        return new ProcessBuilder("notepad.exe").start();
    }

    /**
     * Attendre la fin (async moderne)
     * @param process
     */
    public static void monitorProcess(Process process) {
        ProcessHandle handle = process.toHandle();

        handle.onExit()
                .thenAccept(ph ->
                        System.out.println("Process terminé PID=" + ph.pid()));
    }

    /**
     * Tuer un process
     * @param processes
     */
    public static void killProcesses(List<ProcessHandle> processes) {
        processes.forEach(ph -> {
            System.out.println("Killing PID=" + ph.pid());
            ph.destroy();
        });
    }

}
