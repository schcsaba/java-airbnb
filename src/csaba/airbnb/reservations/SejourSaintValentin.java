package csaba.airbnb.reservations;

import csaba.airbnb.logements.Logement;

import java.util.Date;

class SejourSaintValentin extends Sejour {

    private final int nbBouteilleDeVouvray;

    SejourSaintValentin(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        super(dateArrivee, nbNuits, logement, nbVoyageurs);
        nbBouteilleDeVouvray = nbNuits;
    }

    @Override
    public void miseAJourDuTarif() {
        tarif = getNbNuits() * getLogement().getTarifParNuit() - 10;
    }

    @Override
    public boolean verificationNombreDeNuits() {
        return getNbNuits() <= 5;
    }

    @Override
    public void afficher() {
        System.out.println("C'est un séjour pour la Saint Valentin et on va avoir " + nbBouteilleDeVouvray + " de bouteille de Vouvray.");
        System.out.println("Le prix de ce séjour est de " + tarif + "€.");
    }
}
