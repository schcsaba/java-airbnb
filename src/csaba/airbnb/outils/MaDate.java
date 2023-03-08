package csaba.airbnb.outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MaDate extends Date {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public MaDate() {
        super();
    }

    public MaDate(long date) {
        super(date);
    }

    /**
     * @param jour  entre 1 et 31
     * @param mois  entre 1 et 12
     * @param annee du style 2023
     */
    public MaDate(int jour, int mois, int annee) {
        super(annee - 1900, mois - 1, jour);
    }

    //public MaDate(String dateEnChaineDeCaracteres) throws ParseException {
    //  date = SIMPLE_DATE_FORMAT.parse(dateEnChaineDeCaracteres);
    //}

    /**
     * this est l'objet de type MaDate qui appelle la methode toString
     *
     * @return la date formatée
     */
    @Override
    public String toString() {
        return SIMPLE_DATE_FORMAT.format(this);
    }
}
