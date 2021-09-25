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
			if (this.v�rifEntr�e(line)) // V�rifies que l'entr�e est correcte
				return;
			StringTokenizer stk = new StringTokenizer(line, ";");
			Objet obj = new Objet(stk.nextToken(), Float.valueOf(stk.nextToken()), Float.valueOf(stk.nextToken()));
			// Pas du tout n�cessaire, c'est pour tester
			System.out.println("Objet:");
			System.out.println(obj.getNom());
			System.out.println(obj.getPoids());
			System.out.println(obj.getPrix());
			System.out.println("");

			contenu.add(obj);

		}
		in.close();
		this.poidsMax = poidsMax;
	}

	// Pas s�r que cette m�thode soit n�cessaire
	public boolean v�rifEntr�e(String s) {
		// Regular expression: ajouter mots et chiffre � espace + chiffre avec x.0
		if (!Pattern.matches("^[\\w\\s]{1,} ;{1} [\\d]{1,}[.][\\d]{1,} ;{1} [\\d]{1,}[.][\\d]{1,}$", s)) {
			System.out.println("Erreur du format de l'entr�e");
			return true;
		}
		return false;
	}
	
}
