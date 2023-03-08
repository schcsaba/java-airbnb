package csaba.airbnb.menu;

class GestionReservations {
    static void listerReservations() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des réservations ");
        System.out.println();

        for (int i = 0; i < Menu.listReservations.size(); i++) {
            System.out.print(i + 1 + " : ");
            Menu.listReservations.get(i).afficher();
            System.out.println();
        }
        System.out.println();

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter une réservation");
        System.out.println("2 : Supprimer une réservation");
        System.out.println("3 : Retour");

        switch (Menu.choix(3)) {
            case 1 -> {
                try {
                    ajouterReservation();
                } catch (Exception e) {
                    System.out.println("La réservation n'a pas été créée à cause d'une erreur.");
                    Menu.scanner.next();
                    listerReservations();
                }
            }
            case 2 -> {
                if (Menu.listReservations.size() > 0) {
                    try {
                        supprimerReservation();
                    } catch (Exception e) {
                        System.out.println("La réservation n'a pas été supprimée à cause d'une erreur.");
                    }
                } else {
                    System.out.println("Il n'y a pas de réservations à supprimer.");
                    listerReservations();
                }
            }
            case 3 -> Menu.listerMenu();
        }
    }

    private static void ajouterReservation() {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter une nouvelle réservation");
        System.out.println();
    }

    private static void supprimerReservation() {
    }
}