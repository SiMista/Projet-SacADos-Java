package appli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class SacADos {
	private ArrayList<Objet> contenu;
	private float poidsMax;

	public SacADos() {
		contenu = new ArrayList<Objet>();
	}

	public SacADos(String chemin, float poidsMax) throws NumberFormatException, IOException {
		contenu = new ArrayList<Objet>();
		BufferedReader in = new BufferedReader(new FileReader(chemin));
		String line;
		while ((line = in.readLine()) != null) {
			// sac.v�rifEntr�e(line); V�rifies que l'entr�e est correcte
			Objet obj = new Objet();
			StringTokenizer stk = new StringTokenizer(line, ";");
			System.out.println("Objet:");
			while (stk.hasMoreTokens()) {
				obj.setNom(stk.nextToken());
				obj.setPoids(Float.valueOf(stk.nextToken()));
				obj.setPrix(Float.valueOf(stk.nextToken()));
				System.out.println(obj.getNom());
				System.out.println(obj.getPoids());
				System.out.println(obj.getPrix());
			}
			System.out.println("");
			contenu.add(obj);
			
		}
		in.close();
		this.poidsMax = poidsMax;
	}
	
	// Pas s�r que cette m�thode soit n�cessaire
	public void v�rifEntr�e(String s) {
		// Regular expression: ajouter mots et chiffre � espace + chiffre avec x.0
		if (Pattern.matches("^[a-zA-Z]{1,} ;{1} [0-9]{1,} ;{1} [0-9]{1,}$", "Lampe ; 23 ; 32")) {
			System.out.println("c'est bien r�eel");
		} else {
			System.out.println("c'est faux sah");
		}
	}
	
	/*
	public ArrayList<Objet> getContenu() {
		return contenu;
	}
	*/

}
