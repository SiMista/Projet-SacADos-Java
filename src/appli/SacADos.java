package appli;

import java.io.BufferedReader;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class SacADos {
	private ArrayList<Objet> sac;
	private ArrayList<Objet> listeObjet;
	private float poidsMax;
	

	public SacADos() {
		sac = new ArrayList<>();
		listeObjet = new ArrayList<Objet>();
	}

	public SacADos(String chemin, float poidsMax) throws NumberFormatException, IOException {
		sac = new ArrayList<>();
		listeObjet = new ArrayList<Objet>();
		BufferedReader in = new BufferedReader(new FileReader(chemin));
		String line;
		while ((line = in.readLine()) != null) {
			if (this.vérifEntrée(line)) // Vérifies que l'entrée est correcte
				return;
			StringTokenizer stk = new StringTokenizer(line, ";");
			Objet obj = new Objet(stk.nextToken(), Float.valueOf(stk.nextToken()), Float.valueOf(stk.nextToken()));
			// Pas du tout nécessaire, c'est pour tester
			/*System.out.println("Objet:");
			System.out.println(obj.getNom());
			System.out.println(obj.getPoids());
			System.out.println(obj.getPrix());
			System.out.println("");*/
			listeObjet.add(obj);
		}
		in.close();
		this.poidsMax = poidsMax;
	}
	
	public void resoudre() {
		//faire appel au 3 méthodes
	}
	
	public void glouttonne() {
		float poidsObjets = 0;
		ArrayList<Objet> tmp = triRapide(this.listeObjet);
		for (int i = 0; i<tmp.size()-1; i++) {
			System.out.println(tmp.get(i).getPrix()/tmp.get(i).getPoids());
			}
		while (!this.listeObjet.isEmpty()) {
				poidsObjets += this.listeObjet.get(0).getPoids();
				if(poidsObjets<=poidsMax) {
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
