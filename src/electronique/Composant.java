package electronique;

public abstract class Composant {

    protected final String type;

    public Composant(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type d'un composant ne peut pas être vide.");
        }
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * Calcule la résistance équivalente de ce composant.
     * Pour une Resistance, retourne sa valeur directement.
     * Pour un Circuit, déclenche le calcul récursif sur ses sous-composants.
     *
     * @return résistance équivalente en ohms (Ω)
     */
    public abstract double calculerResistance();
    public abstract String afficher(int profondeur);
}
