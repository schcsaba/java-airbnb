package csaba.airbnb;

import csaba.airbnb.logements.Appartement;
import csaba.airbnb.logements.Logement;
import csaba.airbnb.logements.Maison;
import csaba.airbnb.outils.AirBnBData;
import csaba.airbnb.outils.MaDate;
import csaba.airbnb.reservations.*;
import csaba.airbnb.utilisateurs.Hote;
import csaba.airbnb.utilisateurs.Voyageur;
import csaba.airbnb.logements.Search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;


public class Main {
    public static void main(String[] args) {
//        Voyageur maximeAlbert = new Voyageur("Maxime", "Albert", 29);

//        Hote peterBardu = new Hote("Peter", "Bardu", 28, 12);
//        Maison maison = new Maison("maison1", peterBardu, 120, "81 Rue Colbert, 37000 Tours", 140, 4, 500, true);
//        Appartement appartement = new Appartement("app1", peterBardu, 50, "46 Rue des Canonniers, 59800 Lille", 72, 3, 1, 12);

//        Date date1 = Utile.construireDate("05/12/2022");
//        Date date2 = Utile.construireDate("20/02/2023");
//        Date date3 = Utile.construireDate("14/04/2023");

        // Paramètres
//        MaDate date = new MaDate(18, 3, 2023);
//        int nbNuits = 6;
//        int nbVoyageurs = 4;

        // Creation du séjour
//        Sejour sejour = SejourFactory.createSejour(date, nbNuits, maison, nbVoyageurs);

        // Creation de la réservation
//        Reservation reservation = null;
//        try {
//            reservation = new Reservation(sejour, maximeAlbert);
//            // Afficher la réservation
//            reservation.afficher();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Erreur : " + e.getMessage());
//        }

//        AirBnBData airBnBData = AirBnBData.getInstance();
//        Sejour sejour = SejourFactory.createSejour(date, nbNuits, airBnBData.getListLogements().get(1), nbVoyageurs);
//        Reservation reservation = null;
//        try {
//            reservation = new Reservation(sejour, airBnBData.getListVoyageurs().get(2));
//            // Afficher la réservation
//            reservation.afficher();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Erreur : " + e.getMessage());
//        }

        Search search = new Search.Builder(1).tarifMaxParNuit(200).possedeJardin(true).possedeBalcon(false).build();
        ArrayList<Logement> logements = search.result();

        OptionalDouble optionalDoubleAverage = logements.stream().mapToInt(Logement::getTarifParNuit).average();
        if (optionalDoubleAverage.isPresent()) {
            System.out.println("Prix moyen : " + optionalDoubleAverage.getAsDouble());
        }
        System.out.println("Nombre de logements trouvé : " + logements.size());

        int difference;
        int cheapPrice = 0;
        int expensivePrice = 0;

        System.out.println("----------------------------");

        Optional<Logement> optionalCheapestLogement = logements.stream().min(Comparator.naturalOrder());
        if (optionalCheapestLogement.isPresent()) {
            System.out.println("Le moins cher : ");
            Logement cheapestLogement = optionalCheapestLogement.get();
            cheapestLogement.afficher();
            cheapPrice = cheapestLogement.getTarifParNuit();
        }

        System.out.println("----------------------------");

        Optional<Logement> optionalMostExpensiveLogement = logements.stream().max(Comparator.naturalOrder());
        if (optionalMostExpensiveLogement.isPresent()) {
            System.out.println("Le plus cher : ");
            Logement mostExpensiveLogement = optionalMostExpensiveLogement.get();
            mostExpensiveLogement.afficher();
            expensivePrice = mostExpensiveLogement.getTarifParNuit();
        }

        System.out.println("----------------------------");

        difference = expensivePrice - cheapPrice;
        System.out.println("La différence : " + expensivePrice + "-" + cheapPrice + "=" + difference);

        System.out.println("----------------------------");
//        logements.forEach(Logement::afficher);


//        for (Logement logement: logements) {
//            System.out.println("--------------------------");
//            logement.afficher();
//        }

//        System.out.println();
//        System.out.println("Superficie total de la maison : " + maison.getSuperficieTotal() + "m2");
//        System.out.println("Superficie total d'appartement : " + appartement.getSuperficieTotal() + "m2");
    }
}


