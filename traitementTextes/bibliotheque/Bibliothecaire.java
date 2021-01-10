package traitementTextes.bibliotheque;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Bibliothecaire {

	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	
	public Bibliothecaire(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue=catalogue;
		
	}

	public void ajouterLivre(Livre nouveauLivre) {
		if (Objects.nonNull(getCatalogue().get(nouveauLivre.getAuteur()))) {
			getCatalogue().get(nouveauLivre.getAuteur()).add(nouveauLivre);
		} else {
			ArrayList<Livre> livres = new ArrayList<>();
			livres.add(nouveauLivre);
			getCatalogue().put(nouveauLivre.getAuteur(), livres);
		}

	}

	public String listerOeuvresAuteur(Auteur auteur) {

		ArrayList<Livre> livres= catalogue.get(auteur);
		String titreDesLivres="";
		for (Livre livre : livres) {
			titreDesLivres+=livre.getTitre()+ "\n";
		}
		return "L'auteur "+auteur.getNom()+" a ecrit "+livres.size() +" livres:\n"+ titreDesLivres;
	}
	
	public void enleverLivre(Livre ancienLivre) {
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
			getCatalogue().get(ancienLivre.getAuteur()).remove(ancienLivre);
		}


	}

	public void preterLivre(Livre ancienLivre) {
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
			ancienLivre.setPreter(true);
		}
	}

//	return livre et Emprunteur qui est en retard
	public void RelancerEmprunteurEnRetard() {
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		for (Auteur auhtor : getCatalogue().keySet()){
			for (Livre livre : getCatalogue().get(auhtor)){
				Date dateRetour = livre.getDateRetour();
				if (dateRetour.before(date)){
					livre.setRetard(true);
					System.out.println(livre.getTitre()+' ' +livre.getEmprunteur().toString());

				}
			}

		}
	}

	public ArrayList<Emprunteur> ListerPersonnesAyantEmpruntesUnLivre(Livre livre) {
		if ((Objects.nonNull(getCatalogue().get(livre.getAuteur()))) ) {
//			livre.getEmpruntePersonne();
			return livre.getListeEmprunteurs();

		}
		return null;
	}

	public ArrayList<Livre> testListerLivresEmpruntesParEtudiant(){
		ArrayList<Livre> livresEmpruntesParEtudiant = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())){
			for (Auteur a:getCatalogue().keySet() ){
				for (Livre l : getCatalogue().get(a)){
					if (l.getEmprunteur().getProfession().toUpperCase().equals("ETUDIANT")){
						livresEmpruntesParEtudiant.add(l);
					}
				}
			}
		}
		return livresEmpruntesParEtudiant;


	}

	public ArrayList<Livre> ListerLivresEmpruntes() {
		ArrayList<Livre> livresEmpruntes = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())){
			for (Auteur a:getCatalogue().keySet() ){
				for (Livre l : getCatalogue().get(a)){
					if (l.isPreter() == true){
						livresEmpruntes.add(l);

					}
				}
			}

		}
		return livresEmpruntes;
	}


	public ArrayList<Livre> ListerLivresAnglais() {
		ArrayList<Livre> livreLangue = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()) {
				for (Livre l : getCatalogue().get(a)){
					if (l.getLangue().toUpperCase().equals("ANGLAIS")){
						livreLangue.add(l);
					}
				}
			}

		}
		return livreLangue;
	}


	public int ListerNbLivresEmpruntesPourUnAuteur(Auteur author){
		int NbLivresEmpruntesPourUnAuteur = 0;
//		ArrayList<Livre> LivresEmpruntesPourUnAuteur = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()) {
				if (a.equals(author)){
					for (Livre l: getCatalogue().get(a)){
						if (l.isPreter() == true){
							NbLivresEmpruntesPourUnAuteur++;
						}
					}
				}
			}
		}
		return NbLivresEmpruntesPourUnAuteur;
	}

	public ArrayList<Livre> TrouverLivreSurUnTheme(String theme){
		ArrayList<Livre> LivreSurUnTheme = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()){
				for (Livre l : getCatalogue().get(a)){
					if (l.getTheme().toUpperCase().equals(theme)){
						LivreSurUnTheme.add(l);
					}
				}
			}
			
		}
		return LivreSurUnTheme;
	}


	public void EnvoyerAmendeRetardaire(){
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()){
				for (Livre l : getCatalogue().get(a)){
					if (l.getAmende()!=0){
						System.out.println(l.getEmprunteur().getNom()+" doit payer "+l.getAmende()+" euros pour le livre "+l.getTitre() );
					}
				}

			}
		}

	}


	public void EncaisserAmendeRetardaire(Livre livre){
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()) {
				for (Livre l : getCatalogue().get(a)) {
					if (l.getAmende()!=0){
						System.out.println(l.getEmprunteur().getNom()+" a encassé "+l.getAmende()+" euros pour le livre "+l.getTitre() );
						l.setAmende(0);
					}
				}
			}
		}


	}




	

	public HashMap<Auteur, ArrayList<Livre>> getCatalogue() {
		return catalogue;
	}

	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}

}
