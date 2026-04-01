package app;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    public CircuitBuilder() {}

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

                // 4. Création selon le type
                if ("resistance".equals(type)) {

                    double valeur = noeudComposant.get("valeur").asDouble();
                    listeComposant.add(new Resistance("resisitance",valeur));

                } else if ("serie".equals(type)) {

                    List<Composant> sousComposants = lireComposants(noeudComposant.get("composants"));
                    listeComposant.add(new CircuitSerie("serie", sousComposants));

                } else if ("parallele".equals(type)) {

                    List<Composant> sousComposants = lireComposants(noeudComposant.get("composants"));
                    listeComposant.add(new CircuitParallele("parallele",sousComposants));

                } else {
                    throw new IllegalArgumentException("Type inconnu : " + type);
                }
            }

        } catch (IOException e) {

            System.err.println("Erreur de lecture : " + e.getMessage());

        }
        return listeComposant;
    }

    private List<Composant> lireComposants(JsonNode node) {

        List<Composant> liste = new ArrayList<>();

        for (JsonNode noeud : node) {

            String type = noeud.get("type").asText();

            if ("resistance".equals(type)) {

                double valeur = noeud.get("valeur").asDouble();
                liste.add(new Resistance("resistance",valeur));

            } else if ("serie".equals(type)) {

                List<Composant> sous = lireComposants(noeud.get("composants"));
                liste.add(new CircuitSerie("serie",sous));

            } else if ("parallele".equals(type)) {

                List<Composant> sous = lireComposants(noeud.get("composants"));
                liste.add(new CircuitParallele("parallele",sous));

            } else {
                throw new IllegalArgumentException("Type inconnu : " + type);
            }
        }

        return liste;
    }
}





