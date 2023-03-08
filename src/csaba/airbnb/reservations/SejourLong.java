package csaba.airbnb.reservations;

import csaba.airbnb.logements.Logement;

import java.util.Date;

public class SejourLong extends Sejour implements ConditionsTarifairesInterface {

    private static final int PROMOTION_EN_POURCENTAGE = 20;
    private int promotion;

    public SejourLong(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        super(dateArrivee, nbNuits, logement, nbVoyageurs);
    }

    @Override
    public void miseAJourDuTarif() {
        int initialTarif = getNbNuits() * getLogement().getTarifParNuit();
        promotion = initialTarif * PROMOTION_EN_POURCENTAGE / 100;
        tarif = initialTarif - promotion;
    }

    @Override
    public boolean beneficiePromotion() {
        return true;
    }

    @Override
    public int getTarif() {
        return tarif;
    }

    @Override
    public boolean verificationNombreDeNuits() {
        return getNbNuits() > 5 && getNbNuits() < 32;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Le prix de ce séjour long est de " + getTarif() + "€ (" + promotion + "€ de promotion).");
    }
}
