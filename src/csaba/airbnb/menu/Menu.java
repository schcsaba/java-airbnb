package csaba.airbnb.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import csaba.airbnb.logements.Appartement;
import csaba.airbnb.logements.Logement;
import csaba.airbnb.logements.Maison;
import csaba.airbnb.outils.Comparator;
import csaba.airbnb.outils.ComparatorPlus;
import csaba.airbnb.outils.LogementsSaxParser;
import csaba.airbnb.outils.MaDate;
import csaba.airbnb.reservations.Reservation;
import csaba.airbnb.reservations.Sejour;
import csaba.airbnb.reservations.SejourLong;
import csaba.airbnb.utilisateurs.Hote;
import csaba.airbnb.utilisateurs.Personne;
import csaba.airbnb.utilisateurs.Voyageur;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class Menu {

    static Scanner scanner;

    static ArrayList<Hote> listHotes = new ArrayList<Hote>();
    static ArrayList<Logement> listLogements = new ArrayList<Logement>();
    static ArrayList<Voyageur> listVoyageurs = new ArrayList<Voyageur>();
    static ArrayList<Reservation> listReservations = new ArrayList<Reservation>();

    public static void main(String[] args) {

        System.out.println("Bienvenu chez AirBnB");

//        scanner = new Scanner(System.in);

        try {
            init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        System.out.println("-------------------------------------");
//        Maison maison = findMaisonByName("Maison 1");
//        if (maison != null) maison.afficher();
//        System.out.println("-------------------------------------");
//        Appartement appartement = findAppartementByName("Appartement 2");
//        if (appartement != null) appartement.afficher();
//        System.out.println("-------------------------------------");
//        maison = (Maison) findLogementByName("Maison 1");
//        if (maison != null) maison.afficher();
//        System.out.println("-------------------------------------");
//        appartement = (Appartement) findLogementByName("Appartement 2");
//        if (appartement != null) appartement.afficher();
//        System.out.println("-------------------------------------");
//        Maison maison1 = findLogementByNameWithGenericity("Maison 1");
//        if (maison1 != null) maison1.afficher();
//        System.out.println("-------------------------------------");
//        Appartement appartement1 = findLogementByNameWithGenericity("Appartement 2");
//        if (appartement1 != null) appartement1.afficher();

        Voyageur voyageur1 = new Voyageur("Aaaa", "Bbbb", 20);
        Voyageur voyageur2 = new Voyageur("Cccc", "Dddd", 22);
        listVoyageurs.add(voyageur1);
        listVoyageurs.add(voyageur2);

//        Comparator<Personne> comparator = new Comparator<>(listVoyageurs.get(0), listVoyageurs.get(1));
//        comparator.getLower().afficher();

//        ArrayList<Personne> selectedHotes = new ArrayList<>();
//        selectedHotes.add(listHotes.get(0));
//        selectedHotes.add(listHotes.get(1));
//        selectedHotes.add(listHotes.get(2));
//        ComparatorPlus<Personne> personneComparatorPlus = new ComparatorPlus<>(selectedHotes);
//        personneComparatorPlus.add(listHotes.get(3));
//        personneComparatorPlus.getHighest().afficher();
//        System.out.println();
//        personneComparatorPlus.remove(3);
//        personneComparatorPlus.getHighest().afficher();
//        System.out.println();
//        personneComparatorPlus.remove(listHotes.get(1));
//        personneComparatorPlus.getLowest().afficher();

        MaDate dateArrivee = new MaDate(18, 2, 2023);
        int nbNuits = 10;
        Logement logement = listLogements.get(4);
        int nbVoyageurs = 4;
//        Sejour sejour = new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
//        dateArrivee.setYear(98);
//        sejour.afficher();

        Sejour sejour = new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
        Date date = sejour.getDateArrivee();
        date.setYear(98);
        sejour.afficher();
        System.out.println("------------------------");
        MaDate dateArrivee2 = new MaDate(10, 3, 2023);
        sejour.setDateArrivee(dateArrivee2);
        sejour.afficher();
        System.out.println("------------------------");
        Logement logement2 = listLogements.get(5);
        sejour.setLogement(logement2);
        sejour.afficher();

//        listerMenu();
//
//        scanner.close();
    }

    static void listerMenu() {

        System.out.println("-------------------------------------");
        System.out.println("Saisir une option : ");
        System.out.println("1 : Liste des hôtes");
        System.out.println("2 : Liste des logements");
        System.out.println("3 : Liste des voyageurs");
        System.out.println("4 : Liste des réservations");
        System.out.println("5 : Fermer le programme");

        switch (choix(5)) {
            case 1 -> GestionHotes.listerHotes();
            case 2 -> GestionLogements.listerLogements();
            case 3 -> GestionVoyageurs.listerVoyageurs();
            case 4 -> GestionReservations.listerReservations();
            case 5 -> System.out.println("A bientôt");
        }
    }

    static int choix(int maxValue) {

        boolean choixValide = false;
        int choix = 0;

        while (!choixValide) {
            System.out.print("Rentrez votre choix (1-" + maxValue + ") : ");
            // String next = scanner.next();
            try {
                // choix = Integer.parseInt(next);
                choix = scanner.nextInt();
                if (choix <= maxValue && choix > 0) {
                    choixValide = true;
                } else {
                    System.out.println("La valeur n'est pas comprise entre 1 et " + maxValue + ".");
                }
            } catch (Exception e) {
                System.out.println("La valeur n'est pas un nombre entier.");
                scanner.next();
            }
        }

        return choix;
    }


/*	private static void init() {

		listHotes = new ArrayList<>();
		listLogements = new ArrayList<>();
		listVoyageurs = new ArrayList<>();
		listReservations = new ArrayList<>();


		// Création des Hotes
		Hote hote1 = new Hote("Peter", "Bardu", 28, 12);
		Hote hote2 = new Hote("Patrick", "Martin", 32, 12);
		Hote hote3 = new Hote("Jeanne", "Voisin", 26, 24);
		Hote hote4 = new Hote("Maurice", "Meunier", 46, 12);

		listHotes.add(hote1);
		listHotes.add(hote2);
		listHotes.add(hote3);
		listHotes.add(hote4);

		// Création de Logement
		Maison maison1 = new Maison(hote1, 40, "18 rue De Tours, 37000 Tours", 140, 2, 500, true);
		Maison maison2 = new Maison(hote1, 35, "146 Rue George Sand, 59553 Cuincy", 120, 2, 200, false);
		Maison maison3 = new Maison(hote1, 60, "13 Rue de la Liberté, 62800 Liévin", 90, 4, 2000, true);
		Appartement appartement1 = new Appartement(hote1, 35, "46 Rue des Canonniers, 59800 Lille", 72, 2, 3, 20);
		Appartement appartement2 = new Appartement(hote1, 35, "111 Rue Colbert, 37000 Tours", 42, 2, 2, 0);

		listLogements.add(maison1);
		listLogements.add(maison2);
		listLogements.add(maison3);
		listLogements.add(appartement1);
		listLogements.add(appartement2);

		// Création de voyageurs
		Voyageur voyageur1 = new Voyageur("Alain", "Favre", 54);
		Voyageur voyageur2 = new Voyageur("Maxime", "Albert", 29);

		listVoyageurs.add(voyageur1);
		listVoyageurs.add(voyageur2);
	}*/

    private static void init() throws ParserConfigurationException, IOException, SAXException {
        LogementsSaxParser.parse("logements.xml", listLogements, listHotes);
    }

    private static Maison findMaisonByName(String name) {
        ArrayList<Maison> listMaisons = new ArrayList<>();
        for (Logement l : listLogements) {
            if (l instanceof Maison) {
                listMaisons.add((Maison) l);
            }
        }
        Maison maison = null;
        for (Maison m : listMaisons) {
            if (Objects.equals(m.getName(), name)) {
                maison = m;
            }
        }
        return maison;
    }

    private static Appartement findAppartementByName(String name) {
        ArrayList<Appartement> listAppartements = new ArrayList<>();
        for (Logement l : listLogements) {
            if (l instanceof Appartement) {
                listAppartements.add((Appartement) l);
            }
        }
        Appartement appartement = null;
        for (Appartement a : listAppartements) {
            if (Objects.equals(a.getName(), name)) {
                appartement = a;
            }
        }
        return appartement;
    }

    private static Logement findLogementByName(String name) {
        Logement logement = null;
        for (Logement l : listLogements) {
            if (l != null && Objects.equals(l.getName(), name)) {
                logement = l;
            }
        }
        return logement;
    }

    private static <T extends Logement> T findLogementByNameWithGenericity(String name) {
        T logement = null;
        for (Logement l : listLogements) {
            if (l != null && Objects.equals(l.getName(), name)) logement = (T) l;
        }
        return logement;
    }

}
