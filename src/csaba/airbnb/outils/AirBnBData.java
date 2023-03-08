package csaba.airbnb.outils;

import csaba.airbnb.logements.Logement;
import csaba.airbnb.utilisateurs.Hote;
import csaba.airbnb.utilisateurs.Voyageur;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class AirBnBData {
    private static final AirBnBData instance;

    static {
        try {
            instance = new AirBnBData();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Hote> listHotes = new ArrayList<Hote>();
    private ArrayList<Logement> listLogements = new ArrayList<Logement>();
    private ArrayList<Voyageur> listVoyageurs = new ArrayList<Voyageur>();

    private AirBnBData() throws ParserConfigurationException, IOException, SAXException {
        LogementsSaxParser.parse("logements.xml", listLogements, listHotes);
        Voyageur voyageur1 = new Voyageur("Albert", "Camus", 47);
        Voyageur voyageur2 = new Voyageur("Victor", "Hugo", 83);
        Voyageur voyageur3 = new Voyageur("Alexandre", "Dumas", 68);
        Voyageur voyageur4 = new Voyageur("Emile", "Zola", 62);
        listVoyageurs.add(voyageur1);
        listVoyageurs.add(voyageur2);
        listVoyageurs.add(voyageur3);
        listVoyageurs.add(voyageur4);
    }

    public static AirBnBData getInstance() {
        return instance;
    }

    public ArrayList<Hote> getListHotes() {
        return listHotes;
    }

    public ArrayList<Logement> getListLogements() {
        return listLogements;
    }

    public ArrayList<Voyageur> getListVoyageurs() {
        return listVoyageurs;
    }
}
