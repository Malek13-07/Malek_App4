package electronique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Circuit extends Composant {

    private final List<Composant> listeComposants;

    public Circuit(String type, List<Composant> listeComposants) {
        super(type);
        this.listeComposants = new ArrayList<>();
        if (listeComposants != null) {
            for (Composant c : listeComposants) {
                if (c != null) {
                    this.listeComposants.add(c);
                }
            }
        }
    }
    public List<Composant> getListeComposants() {
        return listeComposants;
    }

    public void ajouterComposant(Composant composant) {
        listeComposants.add(composant);
    }

    @Override
    public String afficher(int profondeur) {
        String resultat = "";

        for (int i = 0; i < profondeur; i++) {
            resultat += "  ";
        }

        resultat += "Circuit [" + type + "] -> " + calculerResistance() + " ohms\n";

        for (Composant c : listeComposants) {
            resultat += c.afficher(profondeur + 1) + "\n";
        }

        return resultat;
    }
}