package electronique;

public class CircuitParallele extends Circuit{

    private String type= "parallele";

    public CircuitParallele(String type , Composant listeComposant){
        super(type,listeComposant);
    }

    //Calcul en parallèle : 1/R_total = 1/R1 + 1/R2

    public double calculerResistance(){
        double inverseTotale = 0;
        for (Composant composant : getListeComposants()) {
            double r = composant.calculerResistance();
            if (r != 0) {
                inverseTotale += 1.0 / r;
            }
        }
        return inverseTotale ;
    }
}
