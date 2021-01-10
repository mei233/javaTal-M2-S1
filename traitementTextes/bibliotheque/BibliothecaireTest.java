package traitementTextes.bibliotheque;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.assertArrayEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BibliothecaireTest {
	
	private Bibliothecaire bibliothecaire;

	@BeforeEach
	void setUp() throws Exception {
		
		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testListerOeuvresAuteur() {
		
		//GIVEN : condition départ
		Auteur auteur=new Auteur("nomAuteur");
		ArrayList<Livre> livres=new ArrayList<>();
		String titre = "un titre presomptueux";
		Livre premierLivre=new Livre(auteur, titre);
		livres.add(premierLivre);
		bibliothecaire.getCatalogue().put(auteur, livres);
		
		//WHEN : ce qui en train de faire
		String listeOeuvres = bibliothecaire.listerOeuvresAuteur(auteur);
		
		//THEN : ce qui attend
		assertNotNull(listeOeuvres);
		assertTrue(listeOeuvres.contains(titre));
		System.out.println(listeOeuvres);
	}
	
	
	@Test
	void testAjouterLivre() {
//		GIVEN
		Auteur auteur = new Auteur("Didier");
		String titre = "Retour à Reims";
		Livre ajoutLivre = new Livre(auteur,titre);

//		WHEN
		bibliothecaire.ajouterLivre(ajoutLivre);
//		THEN
		assertNotNull(ajoutLivre.getAuteur());
		ArrayList<Livre> livresDidier = bibliothecaire.getCatalogue().get(auteur);
		assertNotNull(livresDidier);
//		assertNotNull(livresDidier.contains(ajoutLivre));
		assertTrue(livresDidier.contains(ajoutLivre));
	}

	@Test
	void testEnleverLivre() {

//		GIVEN
		Livre livreDidier = creerLivre("Didier","Retour à Reims");

		bibliothecaire.ajouterLivre(livreDidier);
		assertEquals(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).size(), 1);

//		WHEN
		bibliothecaire.enleverLivre(livreDidier);

//		THEN
//		assertNotNull(enleverLivre.getAuteur())
		assertFalse(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).contains(livreDidier));
		assertEquals(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).size(), 0);

//		fail("Not yet implemented");
	}

	private Livre creerLivre(String nonAuteur, String nomLivre){
		Auteur auteur = new Auteur(nonAuteur);
		String titre = nomLivre;
		Livre livreDidier = new Livre(auteur,titre);
		return livreDidier;
	}

	@Test
	void testEnleverLivrePlusieur() {

//		GIVEN
		Auteur auteurA = new Auteur("Didier");
		String titreA = "Retour à ReimsA";
		Livre livreDidierA = new Livre(auteurA,titreA);
//		Auteur auteurB = new Auteur("Didier");
		String titreB = "Retour à ReimsB";
		Livre livreDidierB = new Livre(auteurA,titreB);
		livreDidierB.setAnneePublication(2020);
//		Auteur auteurC = new Auteur("Didier");
		String titreC = "Retour à ReimsC";
		Livre livreDidierC = new Livre(auteurA,titreA);

		bibliothecaire.ajouterLivre(livreDidierA);
		bibliothecaire.ajouterLivre(livreDidierB);
//		bibliothecaire.ajouterLivre(livreDidierC);
		assertEquals(bibliothecaire.getCatalogue().get(auteurA).size(), 2);

//		WHEN
//		bibliothecaire.enleverLivre(livreDidierA);
		bibliothecaire.enleverLivre(livreDidierC);

//		THEN
//		assertNotNull(enleverLivre.getAuteur())
		assertFalse(bibliothecaire.getCatalogue().get(auteurA).contains(livreDidierA));
		assertEquals(bibliothecaire.getCatalogue().get(auteurA).size(), 1);

//		fail("Not yet implemented");
	}


	
	
	/*
	 * 
	 * Partie concernee par le devoir
	 * Voici le d�compte des notes:
	 * 1pts par test OK==>10pts
	 * 2 pour pour la mis en place de l'heritage
	 * 1pt pour la javadoc
	 * 1pt pour le polymorphisme et la surchage
	 * 1pt pour la reutilisation de l'existant
	 * 1pt pour la gestion des exceptions
	 * 1pt pour la creation d'exceptions
	 * 1pt: utilisation de l'encapsulation
	 * 1pt: utilisation de git
	 * 1pt: lisibilité du code
	 * -1pt: méthode avec plus de 3 arguments
	 * -1pt: classe de plus de 200 lignes
	 * -1pt: plus de 2 boucles for
	 * -1pt: trop d'utilisation de if
	 * 
	 */
	
	@Test
	void testPreterUnLivre() {
//		given
		Auteur auteur = new Auteur("Didier");
		String titre = "Retour à Reims";
		Livre livreDidier = new Livre(auteur,titre);
		bibliothecaire.ajouterLivre(livreDidier);
//		when
		bibliothecaire.preterLivre(livreDidier);
//		then
		assertTrue(livreDidier.isPreter());

//		fail("Not yet implemented");
	}
	
	@Test
	void testRelancerEmprunteurEnRetard() throws ParseException {
//		given
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
//		ajoute 1er livre
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter";
		Livre livreJK = new Livre(auteurJ,titreJ);
//		setting la date de retour
		String retourTimeJK = new String("15-05-2021");
		Date btJK=sdf.parse(retourTimeJK);
		livreJK.setDateRetour(btJK);
//		ajoute 2eme livre
		Auteur auteur = new Auteur("Didier");
		String titre = "Retour à Reims";
		Livre livreDidier = new Livre(auteur,titre);
//		setting la date de retour
		String retourTime = new String("15-12-2020");
		Date bt=sdf.parse(retourTime);
		livreDidier.setDateRetour(bt);
//		creer un emprunteur
		Emprunteur p = new Emprunteur("Marie");
		livreDidier.setEmprunteur(p);
		livreJK.setEmprunteur(p);

//		ajoute dans la cataloque
		bibliothecaire.ajouterLivre(livreJK);
		bibliothecaire.ajouterLivre(livreDidier);

		p.ajouteLivre(livreDidier);
		p.ajouteLivre(livreJK);

//		when
		bibliothecaire.RelancerEmprunteurEnRetard();
//		then
		assertTrue(livreDidier.isRetard());
		assertEquals(livreDidier.getEmprunteur(),p);
	}
	
	@Test
	void testListerPersonnesAyantEmpruntesUnLivre() {
		//		given
		Emprunteur tom = new Emprunteur("Tom");
		Emprunteur paul = new Emprunteur("paul");

//		ajoute livre
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter";
		Livre livreJK = new Livre(auteurJ,titreJ);
		livreJK.setEmprunteur(tom);
		livreJK.setEmprunteur(paul);
		bibliothecaire.ajouterLivre(livreJK);
//		when
		ArrayList<Emprunteur> emprunteurs = bibliothecaire.ListerPersonnesAyantEmpruntesUnLivre(livreJK);
//		then
		assertNotNull(bibliothecaire.getCatalogue().get(livreJK.getAuteur()));
		assertTrue(bibliothecaire.getCatalogue().get(livreJK.getAuteur()).contains(livreJK));
		assertArrayEquals(livreJK.getListeEmprunteurs().toArray(),emprunteurs.toArray());

	}

	private void assertArrayEquals(Object[] listeEmprunteurs, Object[] emprunteurs) {
	}

	@Test
	void testListerLivresEmpruntesParEtudiant() {
		//		given
		Etudiant etudiantLin = new Etudiant("Lin");
//		etudiantLin.setProfession("etudiant");
		Etudiant etudiantBin = new Etudiant("Bin");
//		etudiantBin.setProfession("etudiant");
//		ajoute 1er livre
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter";
		Livre livreJK = new Livre(auteurJ,titreJ);
		livreJK.setEmprunteur(etudiantLin);

//		ajoute 2eme livre
		Auteur auteur = new Auteur("Didier");
		String titre = "Retour à Reims";
		Livre livreDidier = new Livre(auteur,titre);
		livreDidier.setEmprunteur(etudiantBin);

		bibliothecaire.ajouterLivre(livreJK);
		bibliothecaire.ajouterLivre(livreDidier);

//		when
		ArrayList<Livre> livresEmpruntesParEtudiant = bibliothecaire.testListerLivresEmpruntesParEtudiant();

//		then
		ArrayList<Livre> livresTest = new ArrayList<Livre>();
		livresTest.add(livreJK);
		livresTest.add(livreDidier);
		assertArrayEquals(livresEmpruntesParEtudiant.toArray(),livresTest.toArray());

//		fail("Not yet implemented");
	}
	
	@Test
	void testListerLivresEmpruntes() {
		//		given
//		ajoute 1er livre
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter";
		Livre livreJK = new Livre(auteurJ,titreJ);
		livreJK.setPreter(true);

//		ajoute 2eme livre
		Auteur auteur = new Auteur("Didier");
		String titre = "Retour à Reims";
		Livre livreDidier = new Livre(auteur,titre);
		bibliothecaire.ajouterLivre(livreJK);
		bibliothecaire.ajouterLivre(livreDidier);

//		when
		ArrayList<Livre> livres = bibliothecaire.ListerLivresEmpruntes();

//		then
		ArrayList<Livre> livresTest = new ArrayList<Livre>();
		livresTest.add(livreJK);
		assertArrayEquals(livresTest.toArray(),livres.toArray());
	}
	
	@Test
	void testListerLivresAnglais() {
		//		given
//		ajoute 1er livre
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter";
		Livre livreJK = new Livre(auteurJ,titreJ);
		livreJK.setPreter(true);
		livreJK.setLangue("anglais");

//		ajoute 2eme livre
		Auteur auteur = new Auteur("Didier");
		String titre = "Retour à Reims";
		Livre livreDidier = new Livre(auteur,titre);
		livreDidier.setLangue("français");
		bibliothecaire.ajouterLivre(livreJK);
		bibliothecaire.ajouterLivre(livreDidier);
//		when
		ArrayList<Livre> livreLangue = bibliothecaire.ListerLivresAnglais();
//		then
		ArrayList<Livre> livresTest = new ArrayList<Livre>();
		livresTest.add(livreJK);
		assertArrayEquals(livreLangue.toArray(),livresTest.toArray());
//		fail("Not yet implemented");
	}
	
	@Test
	void testListerNbLivresEmpruntesPourUnAuteur() {
		//		given
//		ajoute 1er livre
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter";
		Livre livreJK = new Livre(auteurJ,titreJ);
		livreJK.setPreter(true);

//		ajoute 2eme livre
		Auteur auteur = new Auteur("Didier");
		String titreA = "Retour à Reims";
		String titreB = "Michel Foucault";
		Livre livreDidierA = new Livre(auteur,titreA);
		livreDidierA.setPreter(true);
		Livre livreDidierB = new Livre(auteur,titreB);
//		ajouter dans bibliothecaire
		bibliothecaire.ajouterLivre(livreJK);
		bibliothecaire.ajouterLivre(livreDidierA);
		bibliothecaire.ajouterLivre(livreDidierB);
//		when
		int NbLivresEmpruntesPourUnAuteur = bibliothecaire.ListerNbLivresEmpruntesPourUnAuteur(auteur);
//		then
		assertEquals(NbLivresEmpruntesPourUnAuteur,1);
	}
	
	@Test
	void testTrouverLivreSurUnTheme() {
		//		given
//		ajoute 1er et 2eme livres
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter1";
		String titreK = "Harry Potter2";
		Livre livreJKOne = new Livre(auteurJ,titreJ);
		Livre livreJKTwo = new Livre(auteurJ,titreK);
		livreJKOne.setTheme("magie");
		livreJKTwo.setTheme("Magie");

//		ajoute 3eme livre
		Auteur auteur = new Auteur("Didier");
		String titreA = "Retour à Reims";
		Livre livreDidierA = new Livre(auteur,titreA);
		livreDidierA.setTheme("vie");

//		ajouter dans bibliothecaire
		bibliothecaire.ajouterLivre(livreJKOne);
		bibliothecaire.ajouterLivre(livreJKTwo);
		bibliothecaire.ajouterLivre(livreDidierA);

//		when
		ArrayList<Livre> LivreSurUnTheme = bibliothecaire.TrouverLivreSurUnTheme("magie");
//		then
		ArrayList<Livre> livresTest = new ArrayList<Livre>();
		livresTest.add(livreJKOne);
		livresTest.add(livreJKTwo);
		assertArrayEquals(LivreSurUnTheme.toArray(),livresTest.toArray());

	}
	
	@Test
	void testEnvoyerAmendeRetardaire() {
		//		given
		Emprunteur tom = new Emprunteur("Tom");
//		ajoute 1er et 2eme livres
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter1";
		String titreK = "Harry Potter2";
		Livre livreJKOne = new Livre(auteurJ,titreJ);
		Livre livreJKTwo = new Livre(auteurJ,titreK);
		livreJKOne.setEmprunteur(tom);
		livreJKTwo.setEmprunteur(tom);
		livreJKOne.setAmende(1.25);
//		ajouter dans bibliothecaire
		bibliothecaire.ajouterLivre(livreJKOne);
		bibliothecaire.ajouterLivre(livreJKTwo);

//		when
		bibliothecaire.EnvoyerAmendeRetardaire();
//		then
//		TODO
//		assertFalse();
//		fail("Not yet implemented");
	}
	
	@Test
	void testEncaisserAmendeRetardaire() {
		//		given
		Emprunteur tom = new Emprunteur("Tom");
//		ajoute 1er et 2eme livres
		Auteur auteurJ = new Auteur("J.K");
		String titreJ = "Harry Potter1";
		String titreK = "Harry Potter2";
		Livre livreJKOne = new Livre(auteurJ,titreJ);
		Livre livreJKTwo = new Livre(auteurJ,titreK);
		livreJKOne.setEmprunteur(tom);
		livreJKTwo.setEmprunteur(tom);
		livreJKOne.setAmende(1.25);
		livreJKOne.setAmende(0.89);
//		ajouter dans bibliothecaire
		bibliothecaire.ajouterLivre(livreJKOne);
		bibliothecaire.ajouterLivre(livreJKTwo);

//		when
		bibliothecaire.EncaisserAmendeRetardaire(livreJKOne);
//		then
		assertEquals(livreJKOne.getAmende(),0);
//		fail("Not yet implemented");
	}

	public static void main(String[] args) throws ParseException {
		BibliothecaireTest tester = new BibliothecaireTest();
		tester.testEnleverLivre();
		tester.testEnleverLivrePlusieur();
//	le projet
		tester.testPreterUnLivre();
		tester.testRelancerEmprunteurEnRetard();
		tester.testListerPersonnesAyantEmpruntesUnLivre();
		tester.testListerLivresEmpruntesParEtudiant();
		tester.testListerLivresEmpruntes();
		tester.testListerLivresAnglais();
		tester.testListerNbLivresEmpruntesPourUnAuteur();
		tester.testTrouverLivreSurUnTheme();
		tester.testEnvoyerAmendeRetardaire();
		tester.testEncaisserAmendeRetardaire();






	}

}
