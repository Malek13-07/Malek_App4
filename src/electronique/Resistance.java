package electronique;
public class Resistance extends Composant {

    private final double valeur;

    public Resistance(double valeur) {
        super("resistance");
        if (valeur < 0) {
            throw new IllegalArgumentException("La valeur d'une résistance ne peut pas être négative.");
        }
        this.valeur = valeur;
    }

    public double getValeur() {
        return valeur;
    }

    /**
     * Cas de base de la récursivité : retourne simplement la valeur de la résistance.
     */
    @Override
    public double calculerResistance() {
        return valeur;
    }
    @Override
    public String afficher(int profondeur) {
        return "Resistance";
    }

}

