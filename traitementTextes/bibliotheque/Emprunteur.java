package traitementTextes.bibliotheque;


//import bibliotheques.Livre;

import java.util.ArrayList;

public class Emprunteur {
    public String nom;
    protected int id;
    protected String profession;
    protected ArrayList<Livre> livreEmprunte = new ArrayList<Livre>();


    public Emprunteur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Livre> getLivreEmprunte() {
        return livreEmprunte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void ajouteLivre(Livre l){
        this.livreEmprunte.add(l);
    }


    public String getProfession() { return profession; }

    public void setProfession(String profession) { this.profession = profession; }


    @Override
    public String toString() {
        return getNom();
    }

    @Override
    public boolean equals(Object emprunteur) {
        if (this == emprunteur) {
            return true;
        }
        if (emprunteur instanceof Emprunteur) {
            return this.getNom().equals(((Emprunteur) emprunteur).getNom());
        }
        return false;
    }

}

