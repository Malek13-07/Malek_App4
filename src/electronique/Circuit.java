package electronique;

import java.util.*;
public abstract class Circuit {

    private List<Composant> listeComposants;
    //  type de circuit "serie" ou "parallele"
    private String type ;


    public Circuit(String type , Composant listeComposants){
        this.type=type;
        this.listeComposants = new ArrayList<>();
    }

    public void ajouterCmposant(Composant obj){
        if(obj!=null){
            listeComposants.add(obj);
        }
    }

    public List<Composant> getListeComposants() {
        return listeComposants;
    }

    public double calculerResistance() {
        double valeurTotale = 0;
        for (Composant c : listeComposants){
            if(c==null){
                valeurTotale+=0;
            }
            else if("serie".equals(type)){
                valeurTotale+=c.calculerResistance();
            }else{
                valeurTotale+=c.calculerResistance();
            }
        }
        return valeurTotale;
    }

}
