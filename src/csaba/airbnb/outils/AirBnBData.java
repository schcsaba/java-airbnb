package csaba.airbnb.outils;

import csaba.airbnb.logements.Logement;
import csaba.airbnb.utilisateurs.Hote;
import csaba.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

public class AirBnBData {
    private static final AirBnBData instance = new AirBnBData();
    private final ArrayList<Hote> listHotes;
    private final ArrayList<Logement> listLogements;
    private final ArrayList<Voyageur> listVoyageurs;

    private AirBnBData() {
        listHotes = new ArrayList<>();
        listLogements = new ArrayList<>();
        listVoyageurs = new ArrayList<>();
        try {
            LogementsSaxParser.parse("logements.xml", listLogements, listHotes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
