package csaba.airbnb.menu;

import csaba.airbnb.utilisateurs.Voyageur;

class GestionVoyageurs {

    static void listerVoyageurs() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des voyageurs ");
        System.out.println();

        for (int i = 0; i < Menu.listVoyageurs.size(); i++) {
            System.out.print(i + 1 + " : ");
            Menu.listHotes.get(i).afficher();
            System.out.println();
        }
        System.out.println();

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un voyageur");
        System.out.println("2 : Supprimer un voyageur");
        System.out.println("3 : Retour");

        switch (Menu.choix(3)) {
            case 1 -> {
                try {
                    ajouterVoyageur();
                } catch (Exception e) {
                    System.out.println("Le voyageur n'a pas été créé à cause d'une erreur.");
                    Menu.scanner.next();
                    listerVoyageurs();
                }
            }
            case 2 -> {
                if (Menu.listVoyageurs.size() > 0) {
                    try {
                        supprimerVoyageur();
                    } catch (Exception e) {
                        System.out.println("Le voyageur n'a pas été supprimé à cause d'une erreur.");
                    }
                } else {
                    System.out.println("Il n'y a pas de voyageurs à supprimer.");
                    listerVoyageurs();
                }
            }
            case 3 -> Menu.listerMenu();
        }
    }

    private static void ajouterVoyageur() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un nouvel voyageur");
        System.out.println();

        System.out.print("Prénom : ");
        String prenom = Menu.scanner.next();

        System.out.print("Nom : ");
        String nom = Menu.scanner.next();

        System.out.print("Age : ");
        int age = Menu.scanner.nextInt();

        Voyageur voyageur = new Voyageur(prenom, nom, age);

        Menu.listVoyageurs.add(voyageur);

        System.out.print("Le voyageur est créé : ");
        voyageur.afficher();
        System.out.println();

        listerVoyageurs();
    }

    private static void supprimerVoyageur() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un voyageur");
        System.out.println();

        int indexVoyageur = Menu.choix(Menu.listVoyageurs.size());

        Voyageur voyageurSupprime = Menu.listVoyageurs.remove(indexVoyageur-1);
        System.out.println("Le voyageur a bien été supprimé :");
        voyageurSupprime.afficher();

        listerVoyageurs();
    }
}
