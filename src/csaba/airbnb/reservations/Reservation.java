package csaba.airbnb.reservations;

import csaba.airbnb.outils.MaDate;
import csaba.airbnb.utilisateurs.Voyageur;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Reservation {

    private static int compteur = 1;
    private static final String nomFichier = "reservations.txt";
    private final int identifiant;
    private final SejourInterface sejour;
    private final Voyageur voyageur;
    private final boolean estValidee;
    private final Date dateDeReservation;

    /**
     * @param sejour   sejour
     * @param voyageur voyageur
     * @throws IllegalArgumentException si le sejour est null ou ...
     */
    public Reservation(SejourInterface sejour, Voyageur voyageur) throws IllegalArgumentException {
        if (sejour == null) {
            throw new IllegalArgumentException("Sejour null...");
        }
        if (!sejour.verificationDateArrivee()) {
            throw new IllegalArgumentException("La date d'arrivée doit être dans le futur.");
        }
        if (!sejour.verificationNombreDeNuits()) {
            throw new IllegalArgumentException("Le nombre de nuits doit être compris entre 1 et 31.");
        }
        if (!sejour.verificationNombreDeVoyageurs()) {
            throw new IllegalArgumentException("Le nombre de voyageurs ne doit pas être supérieur à la capacité du logement.");
        }
        this.identifiant = compteur;
        this.sejour = sejour;
        this.voyageur = voyageur;
        this.estValidee = false;
        this.dateDeReservation = new MaDate();

        if (sejour instanceof Sejour monSejour) {
            String strReservation = "Numéro du voyageur : " +
                    voyageur.getIdentifiant() +
                    "\n" +
                    "Numéro du logement : " + monSejour.getLogement().getIdentifiant() +
                    "\n" +
                    "Date d'arrivée (DD/MM/YYYY) : " + monSejour.getDateArrivee() +
                    "\n" +
                    "Nombre de nuits : " + monSejour.getNbNuits() +
                    "\n" +
                    "Nombre de personnes : " + monSejour.getNbVoyageurs();
            ajouterReservationFichier(strReservation);
        }


        compteur++;
    }

    public void afficher() {
        System.out.println();
        System.out.println("Réservation n°" + identifiant);
        System.out.println("Date de réservation : " + dateDeReservation);
        System.out.println("Validée : " + estValidee);
        voyageur.afficher();
        System.out.print(" a fait une réservation chez ");
        sejour.afficher();
    }

    private void ajouterReservationFichier(String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(nomFichier, true));
            out.write(str + "\n\n");
            out.close();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue.");
        }
    }
}
