package csaba.airbnb.reservations;

import csaba.airbnb.logements.Logement;

import java.util.Calendar;
import java.util.Date;

class SejourDerniereMinute extends Sejour implements ConditionsTarifairesInterface {

    private static final int PROMOTION_EN_POURCENTAGE = 40;

    private int promotion;

    SejourDerniereMinute(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        super(dateArrivee, nbNuits, logement, nbVoyageurs);
    }

    @Override
    public void miseAJourDuTarif() {
        int initialTarif = getNbNuits() * getLogement().getTarifParNuit();
        promotion = initialTarif * PROMOTION_EN_POURCENTAGE / 100;
        tarif = initialTarif - promotion;
    }

    @Override
    public boolean verificationNombreDeNuits() {
        return getNbNuits() > 0 && getNbNuits() < 32;
    }

    @Override
    public boolean verificationDateArrivee() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        return super.verificationDateArrivee() && getDateArrivee().before(cal.getTime());
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
    public void afficher() {
        super.afficher();
        System.out.println("Le prix de ce séjour dernière minute est de " + getTarif() + "€ (" + promotion + "€ de promotion).");
    }
}
