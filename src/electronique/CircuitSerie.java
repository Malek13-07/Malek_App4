package electronique;

import java.util.List;

public class CircuitSerie extends Circuit {

    public CircuitSerie(List<Composant> listeComposants) {
        super("serie", listeComposants);
    }

    @Override
    public double calculerResistance() {
        double total = 0;
        for (Composant c : getListeComposants()) {
            total += c.calculerResistance(); // appel récursif si c est un Circuit
        }
        return total;
    }
}

