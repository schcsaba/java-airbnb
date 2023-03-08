package csaba.airbnb.reservations;

import csaba.airbnb.logements.Logement;

import java.util.Date;

class SejourCourt extends Sejour implements ConditionsTarifairesInterface {

    SejourCourt(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        super(dateArrivee, nbNuits, logement, nbVoyageurs);
    }

    @Override
    public void miseAJourDuTarif() {
        tarif = getNbNuits() * getLogement().getTarifParNuit();
    }

    @Override
    public boolean beneficiePromotion() {
        return false;
    }

    @Override
    public int getTarif() {
        return tarif;
    }

    @Override
    public boolean verificationNombreDeNuits() {
        return getNbNuits() > 0 && getNbNuits() < 6;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Le prix de ce séjour court est de " + getTarif() + "€.");
    }
}
