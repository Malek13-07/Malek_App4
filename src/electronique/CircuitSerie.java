package electronique;

public class CircuitSerie extends Circuit  {
    private String type = "serie";

    public CircuitSerie (String type, Composant listeComposant){
        super(type , listeComposant );
    }

    public double calculerResistance(){
        double valeurTotale = 0;
        for (Composant c : getListeComposants()){
            valeurTotale += c.calculerResistance();
        }
        return valeurTotale ;
    }
}
