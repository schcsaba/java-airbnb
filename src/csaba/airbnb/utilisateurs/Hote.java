package csaba.airbnb.utilisateurs;

import org.jetbrains.annotations.NotNull;

public class Hote extends Personne implements Comparable<Personne> {
    private final int delaiDeReponse;

    public Hote(String prenom, String nom, int age, int delaiDeReponse) {
        super(prenom, nom, age);
        this.delaiDeReponse = delaiDeReponse;
    }

    public int getDelaiDeReponse() {
        return delaiDeReponse;
    }

    @Override
    public void afficher() {
        super.afficher();
        String fin;
        if (delaiDeReponse == 1) {
            fin = "l'heure";
        } else {
            fin = "les " + delaiDeReponse + " heures";
        }
        System.out.print(" qui s'engage à répondre dans " + fin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Hote hote = (Hote) o;
        return delaiDeReponse == hote.delaiDeReponse;
    }

    @Override
    public int compareTo(@NotNull Personne o) {
        if (o instanceof Hote) {
            return Integer.compare(this.delaiDeReponse, ((Hote) o).getDelaiDeReponse());
        } else {
            return super.compareTo(o);
        }
    }
}
