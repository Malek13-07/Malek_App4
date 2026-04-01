package electronique;

public class Resistance extends Composant{

    public Resistance(String type, double valeur) {
        super(type,valeur);

    }

    public double calculerResistance(){
        return valeur;
    }
}
