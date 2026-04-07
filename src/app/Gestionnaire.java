package app;
import electronique.Composant;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Gestionnaire {
    private Scanner scanner = new Scanner(System.in);
    private CircuitBuilder builder = new CircuitBuilder();

    public void demarrer() {
        boolean continuer = true;

        while (continuer) {
            File dossier = new File("src/donnees"); // ou "donnees" si tu déplaces le dossier à la racine
            List<File> fichiers = new ArrayList<>();

            // Parcours des fichiers et sous-dossiers
            File[] contenus = dossier.listFiles();
            if (contenus != null) {
                for (File f : contenus) {
                    if (f.isFile() && f.getName().endsWith(".json")) {
                        fichiers.add(f);
                    } else if (f.isDirectory()) {
                        File[] sousFichiers = f.listFiles(sub -> sub.isFile() && sub.getName().endsWith(".json"));
                        if (sousFichiers != null) {
                            fichiers.addAll(Arrays.asList(sousFichiers));
                        }
                    }
                }
            }

            if (fichiers.isEmpty()) {
                System.out.println("Aucun fichier trouvé.");
                return;
            }

            // Affichage des fichiers disponibles
            System.out.println("Fichiers JSON disponibles :");
            for (int i = 0; i < fichiers.size(); i++) {
                System.out.println((i + 1) + " - " + fichiers.get(i).getName());
            }

            // Sélection du fichier
            File fichier = null;
            while (fichier == null) {
                System.out.print("Choisir un fichier : ");
                try {
                    int choix = Integer.parseInt(scanner.nextLine().trim());
                    if (choix >= 1 && choix <= fichiers.size()) {
                        fichier = fichiers.get(choix - 1);
                    } else {
                        System.out.println("Numéro invalide. Réessayez.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Saisie invalide. Entrez un numéro.");
                }
            }

            // Analyse du fichier choisi
            try {
                Composant c = builder.construireDepuisFichier(fichier);
                System.out.println(c.afficher(0));
                System.out.printf("Résistance équivalente = %.2f Ω%n", c.calculerResistance());
            } catch (Exception e) {
                System.out.println("Erreur lors de la lecture ou du calcul : " + e.getMessage());
            }

            // Menu recommencer / quitter
            System.out.print("[R] recommencer / [Q] quitter : ");
            String saisie = scanner.nextLine().trim().toUpperCase();

            switch (saisie) {
                case "R":
                    continuer = true;
                    break;
                case "Q":
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide, on quitte.");
                    continuer = false;
            }
        }
    }
}