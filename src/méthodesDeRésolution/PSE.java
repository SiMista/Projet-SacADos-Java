package méthodesDeRésolution;

import appli.SacADos;

public class PSE {
	public void résoudre(SacADos sac) {
		
	}
}

class Arbre {
	Float valeur;
	Arbre noeudHaut;
	Arbre noeudBas;
	
	public Arbre() {
		
	}
	
	public Arbre(Float valeur) {
		this.valeur = valeur;
	}
	
	public void ajouter(float f) {
		if (this.valeur == null) {
			this.valeur = f;
			noeudHaut = new Arbre();
			noeudBas = new Arbre();
		}
		else {
			if (f > this.valeur)
				noeudHaut = new Arbre()
		}
	}
	
	
}