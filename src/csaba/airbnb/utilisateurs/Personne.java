package csaba.airbnb.utilisateurs;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Personne implements Comparable<Personne> {
    private static int compteur = 1;
    private final String prenom;
    private final String nom;
    private final int age;
    private final int identifiant;

    public Personne(String prenom, String nom, int age) {
        identifiant = compteur;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        compteur++;
    }

    public void afficher() {
        System.out.print(prenom + " " + nom + " (" + age + " ans)");
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Personne personne = (Personne) o;
        return age == personne.age && Objects.equals(nom, personne.nom) &&
                Objects.equals(prenom, personne.prenom);
    }

    @Override
    public int compareTo(@NotNull Personne o) {
        return Integer.compare(this.age, o.getAge());
    }
}
