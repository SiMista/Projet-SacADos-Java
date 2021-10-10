
package appli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class SacADos {
	private ArrayList<Objet> sac;
	private ArrayList<Objet> listeObjets;
	private float poidsMax;
	private float poidsActuel;
	private int nbObjetsMax;

	public SacADos() {
		sac = new ArrayList<>();
		listeObjets = new ArrayList<Objet>();
		poidsActuel = 0;
	}

	public SacADos(String chemin, float poidsMax) throws NumberFormatException, IOException {
		sac = new ArrayList<>();
		listeObjets = new ArrayList<Objet>();
		poidsActuel = 0;
		BufferedReader in = new BufferedReader(new FileReader(chemin));
		String line;
		while ((line = in.readLine()) != null) {
			if (this.vérifEntrée(line)) // Vérifies que l'entrée est correcte
				return;
			StringTokenizer stk = new StringTokenizer(line, ";");
			Objet obj = new Objet(stk.nextToken(), Float.valueOf(stk.nextToken()), Float.valueOf(stk.nextToken()));
			listeObjets.add(obj);
		}
		in.close();
		this.poidsMax = poidsMax;

		System.out.println("   Objets ajoute dans la liste\n");
		float poids = 0;
		for (Objet o : listeObjets) {
			System.out.println(o.getNom());
			System.out.println(o.getPoids());
			System.out.println(o.getPrix());
			poids += o.getPoids();
		}
		nbObjetsMax = listeObjets.size();
		System.out.println("\n Voici le poids de la liste d'objets " + poids);
	}

	public boolean vérifEntrée(String s) {
		// Regular expression: ajouter mots et chiffre à espace + chiffre avec x.0
		if (!Pattern.matches("^[\\w\\s]{1,} ;{1} [\\d]{1,}[.][\\d]{1,} ;{1} [\\d]{1,}[.][\\d]{1,}$", s)) {
			System.out.println("Erreur du format de l'entrée");
			return true;
		}
		return false;
	}

	public void ajouter(Objet objet) {
		sac.add(objet);
		poidsActuel += objet.getPoids();
	}

	public float maxEntre(float a, float b) {
		return a > b ? a : b;
	}

	public ArrayList<Objet> getSac() {
		return this.sac;
	}
	
	public ArrayList<Objet> getListeObjets() {
		return this.listeObjets;
	}
	
	public float getPoidsMax() {
		return poidsMax;
	}
	
	public float getPoidsActuel() {
		return poidsActuel;
	}
	
	public int getNbObjetsMax() {
		return nbObjetsMax;
	}
}
