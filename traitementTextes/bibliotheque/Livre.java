package traitementTextes.bibliotheque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Livre implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Auteur auteur;
	private String titre;
	private String resume;
	private int anneePublication;
	private int nbTomes;
	private String theme;
	private String langue;
	private boolean preter = false;
	private Date dateRetour;
	private ArrayList<Emprunteur> listeEmprunteurs = new ArrayList<Emprunteur>();
	private Emprunteur emprunteur;
	private double amende = 0.0;


	private boolean retard = false;

	public ArrayList<Emprunteur> getListeEmprunteurs() {
		return listeEmprunteurs;
	}

	public void setListeEmprunteurs(ArrayList<Emprunteur> listeEmprunteurs) {
		this.listeEmprunteurs = listeEmprunteurs;
	}

	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Emprunteur emprunteur) {

		this.emprunteur = emprunteur;
		this.listeEmprunteurs.add(this.emprunteur);
	}

	
	public Livre(Auteur auteur,String titre) {
		this.auteur=auteur;
		this.titre=titre;
	}

	@Override
	public boolean equals(Object livre) {
		if (this == livre) {
			return true;
		}
		if (livre instanceof Livre) {
			return ((this.getAuteur().equals(((Livre) livre).getAuteur()))
					&& (this.titre.equals(((Livre) livre).getTitre())));
		}
		return false;
	}

	public int hashCode() {
		return titre.hashCode();
	}


	public Auteur getAuteur() {
		return auteur;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbTomes() {
		return nbTomes;
	}

	public void setNbTomes(int nbTomes) {
		this.nbTomes = nbTomes;
	}


	public boolean isPreter() {
		return preter;
	}
	public void setPreter(boolean preter) {
		this.preter = preter;
	}

	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public boolean isRetard() { return retard; }
	public void setRetard(boolean retard) { this.retard = retard; }

	public String getLangue() { return langue; }
	public void setLangue(String langue) { this.langue = langue; }

	public String getTheme() { return theme; }
	public void setTheme(String theme) { this.theme = theme; }

	public double getAmende() { return amende; }
	public void setAmende(double amende) { this.amende = amende; }






}
