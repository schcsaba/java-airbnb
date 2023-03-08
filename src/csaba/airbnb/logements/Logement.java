package csaba.airbnb.logements;

import csaba.airbnb.utilisateurs.Hote;
import org.jetbrains.annotations.NotNull;

public abstract class Logement implements Comparable<Logement> {

    private final String name;
    private static int compteur = 1;
    private final Hote hote;
    private final int tarifParNuit;
    private final String adresse;
    private final int superficie;
    private final int nbVoyageursMax;
    private final int identifiant;

    public Logement(String name, Hote hote, int tarifParNuit, String adresse, int superficie, int nbVoyageursMax) {
        identifiant = compteur;
        this.name = name;
        this.hote = hote;
        this.tarifParNuit = tarifParNuit;
        this.adresse = adresse;
        this.superficie = superficie;
        this.nbVoyageursMax = nbVoyageursMax;
        compteur++;
    }

    public Hote getHote() {
        return hote;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getTarifParNuit() {
        return tarifParNuit;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getNbVoyageursMax() {
        return nbVoyageursMax;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public String getName() {
        return name;
    }

    public abstract int getSuperficieTotal();

    public abstract void afficher();

    @Override
    public int compareTo(@NotNull Logement o) {
        return Integer.compare(this.tarifParNuit, o.getTarifParNuit());
    }
}
