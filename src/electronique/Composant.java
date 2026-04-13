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

    public abstract double calculerResistance();
    public abstract String afficher(int profondeur);
}
