
package appli;

import java.io.BufferedReader;
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
		for (int x = 0; x<this.listeObjet.size()-2; x++) {
			int max = x;
			for (int j = x; j<this.listeObjet.size()-1; j++) {
				if(this.listeObjet.get(j).getPrix()/this.listeObjet.get(j).getPoids()>this.listeObjet.get(max).getPrix()/this.listeObjet.get(max).getPoids()) {
					max = j;
				}
			}
			//echanger x avec max
			Objet tmp = this.listeObjet.get(max);
			this.listeObjet.set(max, this.listeObjet.get(x));
			this.listeObjet.set(x, tmp);
		}
		// Syso pour vérifier les objets mis dans la listeobjets
		System.out.println("   Objets mis dans la liste\n");
		for (int i = 0; i<this.listeObjet.size()-1; i++) {
			System.out.println(listeObjet.get(i).getNom());
			System.out.println(this.listeObjet.get(i).getPrix()/this.listeObjet.get(i).getPoids() + "\n");
		}
		
		System.out.println("   Objets ajouté dans le sac\n");
		while (!this.listeObjet.isEmpty()) {
				if(poidsActuel + this.listeObjet.get(0).getPoids()<=poidsMax) {
					// Syso pour montrer l'objet mis dans le sac
					System.out.println("Poids du sac = " + poidsActuel + "   Poids Max = " + poidsMax);
					ajouter(this.listeObjet.get(0));
					
				}
				this.listeObjet.remove(0);
			}
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
