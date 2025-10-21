package com.improvemyskills.evolution.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdvancedProcessWindows {
    public static void main(String[] args) {
        try {
            // Commande Windows : dir pour lister, et un chemin invalide pour générer une erreur
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir C:\\nonexistent C:\\Windows");
            builder.redirectErrorStream(false); // On garde les flux séparés
            Process process = builder.start();

            // Lire la sortie standard
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.out.println("=== SORTIE STANDARD ===");
            String line;
            while ((line = stdOutput.readLine()) != null) {
                System.out.println(line);
            }

            // Lire la sortie d'erreur
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            System.out.println("\n=== SORTIE D'ERREUR ===");
            while ((line = stdError.readLine()) != null) {
                System.out.println(line);
            }

            // Attendre la fin du processus et afficher le code de sortie
            int exitCode = process.waitFor();
            System.out.println("\nCode de sortie : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}