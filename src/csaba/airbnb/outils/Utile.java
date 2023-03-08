package csaba.airbnb.outils;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utile {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private Utile() {}

    /**
     * Convertir une date en chaîne de caractères de la forme dd/MM/yyyy en java.util.Date
     * @param dateEnChaineDeCaracteres une date en chaîne de caractères en forme dd/MM/yyyy
     * @return la date convertie en java.util.Date
     */
    public static @NotNull Date construireDate(@NotNull String dateEnChaineDeCaracteres) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(dateEnChaineDeCaracteres);
    }

    /**
     * Convertir une date java.util.Date en chaîne de caractères de la forme dd/MM/yyyy
     * @param date une date en java.util.Date
     * @return la date en chaîne de caractères en forme dd/MM/yyyy
     */
    public static @NotNull String afficherDate(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
