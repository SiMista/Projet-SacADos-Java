
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
	private ArrayList<Objet> listeObjet;
	private float poidsMax;
	private float poidsActuel;
	

	public SacADos() {
		sac = new ArrayList<>();
		listeObjet = new ArrayList<Objet>();
		poidsActuel = 0;
	}

	public SacADos(String chemin, float poidsMax) throws NumberFormatException, IOException {
		sac = new ArrayList<>();
		listeObjet = new ArrayList<Objet>();
		poidsActuel = 0;
		BufferedReader in = new BufferedReader(new FileReader(chemin));
		String line;
		while ((line = in.readLine()) != null) {
			if (this.vérifEntrée(line)) // Vérifies que l'entrée est correcte
				return;
			StringTokenizer stk = new StringTokenizer(line, ";");
			Objet obj = new Objet(stk.nextToken(), Float.valueOf(stk.nextToken()), Float.valueOf(stk.nextToken()));
			listeObjet.add(obj);
		}
		in.close();
		this.poidsMax = poidsMax;
		
		System.out.println("   Objets ajoute dans la liste\n");
		float poids = 0;
		for (Objet o : listeObjet) {
			System.out.println(o.getNom());
			System.out.println(o.getPoids());
			System.out.println(o.getPrix());
			poids += o.getPoids();
		}
		System.out.println("\n Voici le poids de la liste d'objets " + poids);
	}
	
	public void resoudre(String s) {
		switch (s) {
		case "gloutonne":
			glouttonne();
		}
	}
	
	public void ajouter (Objet o) {
		poidsActuel += listeObjet.get(0).getPoids();
		sac.add(listeObjet.get(0));
	}
	
	public void glouttonne() {
		ArrayList<Objet> tmp = triRapide(this.listeObjet);
		for (int i = 0; i<tmp.size()-1; i++) {
			//System.out.println(tmp.get(i).getPrix()/tmp.get(i).getPoids());
			}
		while (!this.listeObjet.isEmpty()) {
				this.poidsActuel += this.listeObjet.get(0).getPoids();
				if(this.poidsActuel<=poidsMax) {
					this.sac.add(this.listeObjet.get(0));
					this.listeObjet.remove(0);
				}
				else break;
			}
		}
	
	public int repartition(ArrayList<Objet> T, int premier, int dernier, int pivot) {
		Collections.swap(T, pivot, dernier);
		int j = premier;
		for(int i = premier; i<dernier-1; i++) {
			if(T.get(i).getPrix()/T.get(i).getPoids()>=T.get(dernier).getPrix()/T.get(dernier).getPoids()) {
				Collections.swap(T, i, j);
				j += 1;
			}
		}
		Collections.swap(T, dernier, j);
		return j;
	}
	
	public ArrayList<Objet> triRapideRec(ArrayList<Objet> T, int premier, int dernier){
		if (premier<dernier) {
			int pivot = (premier + dernier)/2;
			int nouvPivot = repartition(T, premier, dernier, pivot);
			triRapideRec(T, premier, nouvPivot-1);
			triRapideRec(T, nouvPivot+1, dernier);
		}
		return T;
	}
	
	public ArrayList<Objet> triRapide(ArrayList<Objet> T){
		return triRapideRec(T, 0, T.size()-1);
	}
		
	public ArrayList<Objet> getSac(){
		return this.sac;
	}
	
	// Pas sûr que cette méthode soit nécessaire
	public boolean vérifEntrée(String s) {
		// Regular expression: ajouter mots et chiffre à espace + chiffre avec x.0
		if (!Pattern.matches("^[\\w\\s]{1,} ;{1} [\\d]{1,}[.][\\d]{1,} ;{1} [\\d]{1,}[.][\\d]{1,}$", s)) {
			System.out.println("Erreur du format de l'entrée");
			return true;
		}
		return false;
	}	
}
