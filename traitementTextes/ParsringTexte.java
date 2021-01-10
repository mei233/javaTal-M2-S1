package traitementTextes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParsringTexte {
    public String texte="";

    protected void lireFichierAvecBufferedReader(String path) throws IOException {
        BufferedReader lecteurAvecBuffer = null;
        String ligne;

        lecteurAvecBuffer = new BufferedReader(new FileReader(path));

        while ((ligne = lecteurAvecBuffer.readLine()) != null) {
            texte = texte+ligne;
//            System.out.println(ligne);
        }
        lecteurAvecBuffer.close();
    }
    protected int counterStr(String strToFind) {
//		String texte = lireFichierAvecBufferedReader(argv[0]);
        int count = 0;
        int index = 0;
        while ((index = texte.indexOf(strToFind, index)) != -1) {
            index = index + strToFind.length();
            count++;
        }
        return count;
    }
    protected int counterNom() {
        int count = 0;
        for(int i = 0; i < texte.length(); i++) {
            if (Character.isDigit(texte.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        ParsringTexte parsingFichier = new ParsringTexte();
        try {
            System.out.println("lecture fichier avec lireFichierAvecScanner");
            parsingFichier.lireFichierAvecBufferedReader(args[0]);
//			System.out.println(parsingFichier.texte);
            int nb = parsingFichier.counterStr("Il meurt lentement");
            System.out.println("le nombre(Str) est : " + nb);
            int chiffre = parsingFichier.counterNom();
            System.out.println("le nombre de chiffre est : " + chiffre);
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture du fichier");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
