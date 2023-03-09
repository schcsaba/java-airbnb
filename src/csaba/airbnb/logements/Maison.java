package csaba.airbnb.logements;

import csaba.airbnb.utilisateurs.Hote;
import org.jetbrains.annotations.NotNull;

public class Maison extends Logement{
    private final int superficieJardin;
    private final boolean possedePiscine;

    public Maison(String name, Hote hote, int tarifParNuit, String adresse, int superficie, int nbVoyageursMax, int superficieJardin, boolean possedePiscine) {
        super(name, hote, tarifParNuit, adresse, superficie, nbVoyageursMax);
        this.superficieJardin = superficieJardin;
        this.possedePiscine = possedePiscine;
    }

    public int getSuperficieTotal() {
        return getSuperficie() + superficieJardin;
    }

    public void afficher() {
        Hote hote = getHote();
        hote.afficher();
        System.out.println(".\nLe logement est une maison situé " + getAdresse() + ".");
        System.out.println("Superficie : " + getSuperficie() + "m2");
        String jardin;
        if (superficieJardin > 0) {
            jardin = "Oui (" + superficieJardin + "m2)";
        } else {
            jardin = "Non";
        }
        System.out.println("Jardin : " + jardin);
        String piscine;
        if (possedePiscine) {
            piscine = "Oui";
        } else {
            piscine = "Non";
        }
        System.out.println("Piscine : " + piscine);
        System.out.println("Nombre maximal de voyageurs : " + getNbVoyageursMax());
    }

    public boolean isPossedePiscine() {
        return possedePiscine;
    }

    public int getSuperficieJardin() {
        return superficieJardin;
    }

    @Override
    public int compareTo(@NotNull Logement o) {
        return super.compareTo(o);
    }
}
