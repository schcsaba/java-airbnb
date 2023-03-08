package csaba.airbnb.reservations;

import csaba.airbnb.logements.Logement;

public interface SejourInterface {
    boolean verificationDateArrivee();
    boolean verificationNombreDeNuits();
    boolean verificationNombreDeVoyageurs();
    void afficher();
}
