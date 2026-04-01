package app;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    public CircuitBuilder() {
    }

    public List<Composant> construireCircuit(String cheminFichier) {
        List<Composant> listeComposant = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            // on lit le fichier
            JsonNode donneescircuit = mapper.readTree(new File(cheminFichier));
            // On boucle sur chaque noeud
            for (JsonNode noeudComposant : donneescircuit) {
                // Extraction des donnees
                String type = noeudComposant.get("type").asText();
                double Valeur = noeudComposant.get("valeur").asDouble();
            }

        } catch (IOException e) {

            System.err.println("Erreur de lecture : " + e.getMessage());

        }
        return listeComposant;
    }

    public List<Composant> lireComposant(JsonNode node) {

    }
}
    List<Composant> listeComposants = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

        try {
        // 1. Lire le fichier JSON
        JsonNode donneesCircuit = mapper.readTree(new File(cheminFichier));

        // 2. Boucle sur chaque noeud
        for (JsonNode noeudComposant : donneesCircuit) {

            // 3. Extraction des données
            String type = noeudComposant.get("type").asText();

            // 4. Création selon le type
            if ("resistance".equals(type)) {

                double valeur = noeudComposant.get("valeur").asDouble();
                listeComposants.add(new Resistance(valeur));

            } else if ("serie".equals(type)) {

                List<Composant> sousComposants = lireComposants(noeudComposant.get("composants"));
                listeComposants.add(new CircuitSerie(sousComposants));

            } else if ("parallele".equals(type)) {

                List<Composant> sousComposants = lireComposants(noeudComposant.get("composants"));
                listeComposants.add(new CircuitParallele(sousComposants));

            } else {
                throw new IllegalArgumentException("Type inconnu : " + type);
            }
        }

    } catch (IOException e) {
        System.err.println("Erreur de lecture : " + e.getMessage());
    }

        return listeComposants;
}

// 🔁 Méthode récursive (comme Ecosysteme du prof)
private List<Composant> lireComposants(JsonNode node) {

    List<Composant> liste = new ArrayList<>();

    for (JsonNode noeud : node) {

        String type = noeud.get("type").asText();

        if ("resistance".equals(type)) {

            double valeur = noeud.get("valeur").asDouble();
            liste.add(new Resistance(valeur));

        } else if ("serie".equals(type)) {

            List<Composant> sous = lireComposants(noeud.get("composants"));
            liste.add(new CircuitSerie(sous));

        } else if ("parallele".equals(type)) {

            List<Composant> sous = lireComposants(noeud.get("composants"));
            liste.add(new CircuitParallele(sous));

        } else {
            throw new IllegalArgumentException("Type inconnu : " + type);
        }
    }

    return liste;
}

