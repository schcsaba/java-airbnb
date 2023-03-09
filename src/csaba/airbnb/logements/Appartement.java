package csaba.airbnb.logements;

import csaba.airbnb.utilisateurs.Hote;
import org.jetbrains.annotations.NotNull;

public class Appartement extends Logement {
    private final int numeroEtage;
    private final int superficieBalcon;

    public Appartement(String name, Hote hote, int tarifParNuit, String adresse, int superficie, int nbVoyageursMax, int numeroEtage, int superficieBalcon) {
        super(name, hote, tarifParNuit, adresse, superficie, nbVoyageursMax);
        this.numeroEtage = numeroEtage;
        this.superficieBalcon = superficieBalcon;
    }

    public int getSuperficieTotal() {
        return getSuperficie() + superficieBalcon;
    }

    public void afficher() {
        Hote hote = getHote();
        hote.afficher();
        String etage;
        if (numeroEtage == 0) {
            etage = "rez-de-chaussée";
        } else if (numeroEtage == 1) {
            etage = "1er étage";
        } else {
            etage = numeroEtage + "ème étage";
        }
        System.out.println(".\nLe logement est un appartement situé " + getAdresse() + " au " + etage + ".");
        System.out.println("Superficie : " + getSuperficie() + "m2");
        String balcon;
        if (superficieBalcon > 0) {
            balcon = "Oui (" + superficieBalcon + "m2)";
        } else {
            balcon = "Non";
        }
        System.out.println("Balcon : " + balcon);
        System.out.println("Nombre maximal de voyageurs : " + getNbVoyageursMax());
    }

    public int getSuperficieBalcon() {
        return superficieBalcon;
    }

    @Override
    public int compareTo(@NotNull Logement o) {
        return super.compareTo(o);
    }
}
