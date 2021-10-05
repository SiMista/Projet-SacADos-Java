
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
	public float poidsActuel; // CHANGER APRES !!

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

	public void ajouterAuSac(Objet objet) {
		sac.add(objet);
		poidsActuel += objet.getPoids();
	}

	public void glouttonne() {
		ArrayList<Objet> tmp = triRapide(this.listeObjets);
		for (int i = 0; i < tmp.size() - 1; i++) {
			 // System.out.println(tmp.get(i).getPrix()/tmp.get(i).getPoids());
		}
		while (!this.listeObjets.isEmpty()) {
			if(this.poidsActuel + this.listeObjets.get(0).getPoids() <=poidsMax) {
				ajouterAuSac(listeObjets.get(0));
				listeObjets.remove(0);
			}
			else break;
		}
	}

	public int repartition(ArrayList<Objet> T, int premier, int dernier, int pivot) {
		echanger(T, pivot, dernier);
		int j = premier;
		for (int i = premier; i < dernier - 1; i++) {
			if (T.get(i).getPrix() / T.get(i).getPoids() >= T.get(dernier).getPrix() / T.get(dernier).getPoids()) {
				echanger(T, i, j);
				j += 1;
			}
		}
		echanger(T, dernier, j);
		return j;
	}
	
	private void echanger(ArrayList<Objet> liste, int a, int b) {
		Objet tmp = liste.get(a);
		liste.set(a, liste.get(b));
		liste.set(b, tmp);
	}

	public ArrayList<Objet> triRapideRec(ArrayList<Objet> T, int premier, int dernier) {
		if (premier < dernier) {
			int pivot = (premier + dernier) / 2;
			int nouvPivot = repartition(T, premier, dernier, pivot);
			triRapideRec(T, premier, nouvPivot - 1);
			triRapideRec(T, nouvPivot + 1, dernier);
		}
		return T;
	}

	public ArrayList<Objet> triRapide(ArrayList<Objet> T) {
		return triRapideRec(T, 0, T.size() - 1);
	}

	public void progDynamique() {
		int ligneMax = listeObjets.size();
		int colonneMax = (int) poidsMax;
		float tabVal[][] = new float[ligneMax][colonneMax + 1];

		for (int j = 0; j <= colonneMax; ++j) {
			if (listeObjets.get(0).getPoids() > j)
				tabVal[0][j] = 0;
			else
				tabVal[0][j] = listeObjets.get(0).getPrix();
		}

		for (int i = 1; i < ligneMax; ++i) {
			for (int j = 0; j <= colonneMax; ++j) {
				if (listeObjets.get(i).getPoids() > j)
					tabVal[i][j] = tabVal[i - 1][j];
				else
					tabVal[i][j] = maxEntre(tabVal[i - 1][j],
							tabVal[i - 1][(int) (j - listeObjets.get(i).getPoids())] + listeObjets.get(i).getPrix());
			}
		}
		/*
		for (; colonneMax > 0; --colonneMax) {
			if (tabVal[ligneMax - 1][colonneMax] != tabVal[ligneMax - 1][colonneMax - 1])
				break;
		}

		for (; colonneMax > 0; --colonneMax) {
			for (; ligneMax > 0; --ligneMax) {
				if (tabVal[ligneMax - 1][colonneMax] != tabVal[ligneMax - 2][colonneMax]) {
					colonneMax -= listeObjets.get(ligneMax - 1).getPoids();
					if (colonneMax >= 0) {
						ajouterAuSac(ligneMax - 1);
						--ligneMax;
					}
					break;
				}
			}
		}
		*/
		int i = ligneMax - 1;
		int j = colonneMax;

		while (j >= 0 && tabVal[i][j] == tabVal[i][j - 1]) {
			--j;
		}
		
		while (j > 0) {
			while (i > 0 && tabVal[i][j] == tabVal[i - 1][j])
				--i;
			j = (int) (j - listeObjets.get(i).getPoids());
			if (j >= 0) {
				ajouterAuSac(listeObjets.get(i));
			}
			--i;
		}
		
		for (int l = 0; l < 8; ++l) {
			for (int c = 0; c < poidsMax + 1; ++c) {
				System.out.print(tabVal[l][c]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		
	}

	public float maxEntre(float a, float b) {
		if (a > b)
			return a;
		return b;
	}

	public ArrayList<Objet> getSac() {
		return this.sac;
	}
}
