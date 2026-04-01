package electronique;

public abstract class Composant {
    protected String type;
    protected double valeur;

    public Composant(String type, double valeur) {
        this.type = type;
        this.valeur=valeur;
    }
    public String getType() {
        return type;
    }
    public abstract double calculerResistance();

    public double getValeur() {
        return valeur;
    }
}
