package méthodesDeRésolution;

import appli.SacADos;

public class PSE {
	public void résoudre(SacADos sac) {
		Arbre arbre = new Arbre();
		for (int i = 0; i < sac.getListeObjets().size() - 1; ++i) {
			arbre.ajouter(i, sac);
		}
	}
}

class Arbre {
	Float valeur;
	Arbre noeudHaut;
	Arbre noeudBas;
	float borneSup;
	
	public Arbre() {
		
	}

	public Arbre(Float valeur) {
		this.valeur = valeur;
	}

	public void ajouter(int i, SacADos sac) {
		if (this.valeur == null) {
			noeudHaut = new Arbre();
			noeudHaut.ajouter(i + 1, sac);
			noeudBas = new Arbre();
		} else {
			if (i > this.valeur)
				noeudHaut = new Arbre();
		}
	}
}