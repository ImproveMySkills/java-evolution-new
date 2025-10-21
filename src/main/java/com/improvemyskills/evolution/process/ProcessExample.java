package com.improvemyskills.evolution.process;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessExample {
    public static void main(String[] args) {
        try {
            ProcessBuilder builder = new ProcessBuilder("ping", "-c", "3", "google.com");
            Process process = builder.start();

            // Lire la sortie standard
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

            //int exitCode = process `Runtime.exec()` et `ProcessBuilder`**, ou que je **montre un exemple plus avancé** (par exemple, exécuter une commande et capturer à la fois la sortie standard et la sortie d’erreur) ?