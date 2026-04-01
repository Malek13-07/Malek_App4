package app;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    public CircuitBuilder(){}

    public List<Composant> construireCircuit(String cheminFichier){
        List<Composant> listeComposant = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            // on lit le fichier
            JsonNode donneescircuit = mapper.readTree(new File(cheminFichier));
            // On boucle sur chaque noeud
            for(JsonNode noeudComposant : donneescircuit){
                // Extraction des donnees
                String type = noeudComposant.get("type").asText();

            }

        }catch (IOException e) {

            System.err.println("Erreur de lecture : " + e.getMessage());

        }
        return listeComposant;
    }
    public List<Composant> lireComposant(JsonNode node){

    }
}
