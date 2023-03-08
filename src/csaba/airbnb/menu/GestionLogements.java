package csaba.airbnb.menu;

import csaba.airbnb.logements.Appartement;
import csaba.airbnb.logements.Logement;
import csaba.airbnb.logements.Maison;
import csaba.airbnb.utilisateurs.Hote;

class GestionLogements {
    static void listerLogements() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des logements ");
        System.out.println();

        for (int i = 0; i < Menu.listLogements.size(); i++) {
            System.out.print(i + 1 + " : ");
            Menu.listLogements.get(i).afficher();
            System.out.println();
        }
        System.out.println();

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter une maison");
        System.out.println("2 : Ajouter un appartement");
        System.out.println("3 : Supprimer un logement");
        System.out.println("4 : Retour");

        switch (Menu.choix(4)) {
            case 1 -> {
                try {
                    ajouterMaison();
                } catch (Exception e) {
                    System.out.println("La maison n'a pas été créée à cause d'une erreur.");
                    Menu.scanner.next();
                    listerLogements();
                }
            }
            case 2 -> {
                try {
                    ajouterAppartement();
                } catch (Exception e) {
                    System.out.println("L'appartement n'a pas été créé à cause d'une erreur.");
                    Menu.scanner.next();
                    listerLogements();
                }
            }
            case 3 -> {
                if (Menu.listLogements.size() > 0) {
                    try {
                        supprimerLogement();
                    } catch (Exception e) {
                        System.out.println("Le logement n'a pas été supprimé à cause d'une erreur.");
                    }
                } else {
                    System.out.println("Il n'y a pas de logements à supprimer.");
                    listerLogements();
                }
            }
            case 4 -> Menu.listerMenu();
        }
    }

    private static void ajouterAppartement() {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un nouvel appartement");
        System.out.println();

        System.out.print("Name : ");
        String name = Menu.scanner.next();

        System.out.print("Hôte : ");
        int indexHote = Menu.choix(Menu.listHotes.size());

        System.out.print("Tarif par nuit : ");
        int tarifParNuit = Menu.scanner.nextInt();

        System.out.print("Adresse : ");
        String adresse = Menu.scanner.next();

        System.out.print("Superficie : ");
        int superficie = Menu.scanner.nextInt();

        System.out.print("Nombre maximal de voyageurs : ");
        int nbVoyageursMax = Menu.scanner.nextInt();

        System.out.print("Nombre d'étage : ");
        int numeroEtage = Menu.scanner.nextInt();

        System.out.print("Superficie du balcon : ");
        int superficieBalcon = Menu.scanner.nextInt();

        try {
            Hote hote = Menu.listHotes.get(indexHote);
            Logement logement = new Appartement(name, hote, tarifParNuit, adresse, superficie, nbVoyageursMax, numeroEtage, superficieBalcon);
            Menu.listLogements.add(logement);

            System.out.print("L'appartement est créé : ");
            logement.afficher();
            System.out.println();
            listerLogements();
        } catch (Exception e) {
            System.out.println("Cet hôte n'existe pas.");
        }
    }

    private static void ajouterMaison() {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un nouvelle maison");
        System.out.println();

        System.out.print("Name : ");
        String name = Menu.scanner.next();

        System.out.print("Hôte : ");
        int indexHote = Menu.choix(Menu.listHotes.size());

        System.out.print("Tarif par nuit : ");
        int tarifParNuit = Menu.scanner.nextInt();

        System.out.print("Adresse : ");
        String adresse = Menu.scanner.next();

        System.out.print("Superficie : ");
        int superficie = Menu.scanner.nextInt();

        System.out.print("Nombre maximal de voyageurs : ");
        int nbVoyageursMax = Menu.scanner.nextInt();

        System.out.print("Superficie du jardin : ");
        int superficieJardin = Menu.scanner.nextInt();

        System.out.print("Est-ce qu'il y a une piscine : ");
        boolean possedePiscine = Menu.scanner.nextBoolean();

        try {
            Hote hote = Menu.listHotes.get(indexHote);
            Logement logement = new Maison(name, hote, tarifParNuit, adresse, superficie, nbVoyageursMax, superficieJardin, possedePiscine);
            Menu.listLogements.add(logement);

            System.out.print("La maison est créée : ");
            logement.afficher();
            System.out.println();
            listerLogements();
        } catch (Exception e) {
            System.out.println("Cet hôte n'existe pas.");
        }
    }

    private static void supprimerLogement() {

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un logement");
        System.out.println();

        int indexLogement = Menu.choix(Menu.listLogements.size());

        Logement logementSupprime = Menu.listLogements.remove(indexLogement-1);
        System.out.println("Le logement a bien été supprimé.");
        logementSupprime.afficher();

        listerLogements();
    }
}
