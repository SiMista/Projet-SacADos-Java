package appli;

import java.io.IOException;
import méthodesDeRésolution.Glouttonne;
import méthodesDeRésolution.ProgDynamique;

public class main {
	public static void main(String[] args) throws IOException {
		// Siméon chemin
		Glouttonne glouttonne = new Glouttonne();
		ProgDynamique progDynamique = new ProgDynamique();
<<<<<<< HEAD
		//SacADos sacADos = new SacADos("C:\\Users\\simeo\\OneDrive\\Bureau\\ListeObj.txt", 4.55F);
=======
		SacADos sacADos = new SacADos("C:\\Users\\simeo\\OneDrive\\Bureau\\ListeObj.txt", 4.4F);
>>>>>>> parent of bb5ae28 (dÃ©but PSE)
		// Niru chemin
		SacADos sacADos = new SacADos("C:\\Users\\sniru\\eclipse-workspace\\porjetAAV\\ListeObj.txt", 7);
		progDynamique.résoudre(sacADos);
		// Syso pour montrer tout les objets dans le sac
		System.out.println("\n   Objets dans le sac\n");
		float val = 0.0F;
		float poi = 0.0F;
		for (Objet obj : sacADos.getSac()) {
			System.out.println(obj.getNom());
			System.out.println(obj.getPoids());
			System.out.println(obj.getPrix());
			val += obj.getPrix();
			poi += obj.getPoids();
			
		}
		System.out.println("\n Voici le poids actuel du sac " + poi);
		System.out.println("\n Voici la valeur total du sac " + val);
		
	}
}
