package csaba.airbnb.reservations;

import csaba.airbnb.logements.Logement;
import csaba.airbnb.outils.MaDate;

import java.util.Calendar;

public final class SejourFactory {

    private final static MaDate DATE_ST_VALENTIN = new MaDate(14, 2, 2023);
    private static final int NB_NUITS = 6;

    private static final Calendar CAL = Calendar.getInstance();

    private SejourFactory() {
        CAL.add(Calendar.DATE, 7);
    }

    public static Sejour createSejour(MaDate date, int nbNuits, Logement logement, int nbVoyageurs) {

        Sejour sejour;

        if (date.equals(DATE_ST_VALENTIN) && nbNuits <= 5 && nbVoyageurs == 2) {
            sejour = new SejourSaintValentin(date, nbNuits, logement, nbVoyageurs);
        } else if (date.before(CAL.getTime())) {
            sejour = new SejourDerniereMinute(date, nbNuits, logement, nbVoyageurs);
        } else if (nbNuits < NB_NUITS) {
            sejour = new SejourCourt(date, nbNuits, logement, nbVoyageurs);
        } else {
            sejour = new SejourLong(date, nbNuits, logement, nbVoyageurs);
        }

        return sejour;
    }
}
