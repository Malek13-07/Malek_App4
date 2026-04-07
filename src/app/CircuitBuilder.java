package app;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {

    private ObjectMapper mapper = new ObjectMapper();

    public Composant construireDepuisFichier(File fichier) throws IOException {
        JsonNode racine = mapper.readTree(fichier);
        return parseComposant(racine.get("circuit"));
    }

    private Composant parseComposant(JsonNode noeud) {
        String type = noeud.get("type").asText();

        switch (type) {

            case "resistance":
                return new Resistance(noeud.get("valeur").asDouble());

            case "serie":
                return new CircuitSerie(parseListe(noeud.get("composants")));

            case "parallele":
                return new CircuitParallele(parseListe(noeud.get("composants")));

            default:
                throw new IllegalArgumentException("Type inconnu : " + type);
        }
    }

    private List<Composant> parseListe(JsonNode tableau) {
        List<Composant> liste = new ArrayList<>();

        if (tableau == null) {
            return liste; // validation minimale
        }

        for (JsonNode n : tableau) {
            liste.add(parseComposant(n));
        }

        return liste;
    }
}