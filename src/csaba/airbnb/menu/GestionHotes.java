package csaba.airbnb.menu;

import csaba.airbnb.utilisateurs.Hote;

class GestionHotes {

    static void listerHotes() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des hôtes ");
        System.out.println();

        for (int i = 0; i < Menu.listHotes.size(); i++) {
            System.out.print(i + 1 + " : ");
            Menu.listHotes.get(i).afficher();
            System.out.println();
        }
        System.out.println();

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un hôte");
        System.out.println("2 : Supprimer un hôte");
        System.out.println("3 : Retour");

        switch (Menu.choix(3)) {
            case 1 -> {
                try {
                    ajouterHote();
                } catch (Exception e) {
                    System.out.println("L'hôte n'a pas été créé à cause d'une erreur.");
                    Menu.scanner.next();
                    listerHotes();
                }
            }
            case 2 -> {
                if (Menu.listHotes.size() > 0) {
                    try {
                        supprimerHote();
                    } catch (Exception e) {
                        System.out.println("L'hôte n'a pas été supprimé à cause d'une erreur.");
                    }
                } else {
                    System.out.println("Il n'y a pas d'hôtes à supprimer.");
                    listerHotes();
                }
            }
            case 3 -> Menu.listerMenu();
        }
    }

    private static void ajouterHote() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un nouvel hôte");
        System.out.println();

        System.out.print("Prénom : ");
        String prenom = Menu.scanner.next();

        System.out.print("Nom : ");
        String nom = Menu.scanner.next();

        System.out.print("Age : ");
        int age = Menu.scanner.nextInt();

        System.out.print("Délai de réponse en heures : ");
        int delaiDeReponse = Menu.scanner.nextInt();

        Hote hote = new Hote(prenom, nom, age, delaiDeReponse);

        Menu.listHotes.add(hote);

        System.out.print("L'hôte est créé : ");
        hote.afficher();
        System.out.println();

        listerHotes();
    }

    private static void supprimerHote() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un hôte");
        System.out.println();

        int indexHote = Menu.choix(Menu.listHotes.size());

        Hote hoteSupprimee = Menu.listHotes.remove(indexHote-1);
        System.out.println("L'hôte a bien été supprimée : ");
        hoteSupprimee.afficher();

        listerHotes();
    }
}
