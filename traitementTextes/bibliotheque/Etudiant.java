package traitementTextes.bibliotheque;

public class Etudiant extends Emprunteur {
    private String etablissement;

    public Etudiant(String nom, String unversite) {
        super(nom);
        this.etablissement = etablissement;
    }

    public Etudiant(String nom) {
        super(nom);
        this.profession = "etudiant";
    }

    public String getEtablissement() { return etablissement; }

    public void setEtablissement(String etablissement) { this.etablissement = etablissement; }

}
