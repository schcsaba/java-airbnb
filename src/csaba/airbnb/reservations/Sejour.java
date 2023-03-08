package csaba.airbnb.reservations;

import csaba.airbnb.logements.Logement;
import csaba.airbnb.outils.MaDate;

import java.util.Date;

public abstract class Sejour implements SejourInterface {
    private Date dateArrivee;
    private final int nbNuits;
    private Logement logement;
    private final int nbVoyageurs;
    protected int tarif;

    public Sejour(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        // Une copie défensive doit être créée.
        this.dateArrivee = (Date) dateArrivee.clone();
        this.nbNuits = nbNuits;
        this.logement = logement;
        this.nbVoyageurs = nbVoyageurs;
        miseAJourDuTarif();
    }

    public abstract void miseAJourDuTarif();

    @Override
    public boolean verificationDateArrivee() {
        return dateArrivee.after(new Date());
    }

    @Override
    public boolean verificationNombreDeVoyageurs() {
        return nbVoyageurs > 0 && nbVoyageurs <= logement.getNbVoyageursMax();
    }

    @Override
    public void afficher() {
        logement.afficher();
        System.out.println("La date d'arrivée est le " + dateArrivee + " pour " + nbNuits + " nuits.");
        System.out.println("Nombre de voyageurs : " + nbVoyageurs);
    }

    public int getNbNuits() {
        return nbNuits;
    }

    public Date getDateArrivee() {
        // Une copie défensive doit être créée.
        return (Date) dateArrivee.clone();
    }

    public Logement getLogement() {
        return logement;
    }

    public int getNbVoyageurs() {
        return nbVoyageurs;
    }

    public void setDateArrivee(Date dateArrivee) {
        // Une copie défensive doit être créée.
        this.dateArrivee = (Date) dateArrivee.clone();
    }

    /**
     *
     * @param logement Le logement
     * @throws IllegalArgumentException Le logement est null...
     */
    public void setLogement(Logement logement) {
        if (logement == null) {
            throw new IllegalArgumentException("Le logement est null...");
        }
        this.logement = logement;
        miseAJourDuTarif();
    }
}
