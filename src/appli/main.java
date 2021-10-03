package appli;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		// Siméon chemin
		//SacADos sacADos = new SacADos("C:\\Users\\simeo\\OneDrive\\Bureau\\ListeObj.txt", 7);
		// Niru chemin
		SacADos sacADos = new SacADos("C:\\Users\\sniru\\eclipse-workspace\\porjetAAV\\ListeObj.txt", 7);
		sacADos.glouttonne();
		// Syso pour montrer tout les objets dans le sac
		System.out.println("\n   Objets dans le sac\n");
		for (Objet obj : sacADos.getSac()) {
			System.out.println(obj.getNom());
			System.out.println(obj.getPoids());
			System.out.println(obj.getPrix());
			System.out.println("");
		}
	}
}
