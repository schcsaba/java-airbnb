package csaba.airbnb.utilisateurs;

import org.jetbrains.annotations.NotNull;

public class Voyageur extends Personne {

    public Voyageur(String prenom, String nom, int age) {
        super(prenom, nom, age);
    }

    @Override
    public int compareTo(@NotNull Personne o) {
        return super.compareTo(o);
    }
}
