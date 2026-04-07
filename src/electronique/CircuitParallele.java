package electronique;
import java.util.List;

public class CircuitParallele extends Circuit {

    public CircuitParallele(List<Composant> listeComposants) {
        super("parallele", listeComposants);
    }

    @Override
    public double calculerResistance() {
        double inverseTotal = 0;
        for (Composant c : getListeComposants()) {
            double r = c.calculerResistance(); // appel récursif si c est un Circuit
            if (r != 0) {
                inverseTotal += 1.0 / r;
            }
        }
        // Si tous les composants ont R=0, on retourne 0 pour éviter une division par zéro
        return (inverseTotal != 0) ? 1.0 / inverseTotal : 0;
    }
}
