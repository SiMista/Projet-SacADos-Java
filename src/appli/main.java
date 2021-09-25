package appli;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		//"C:\\Users\\sniru\\eclipse-workspace\\porjetAAV\\ListeObj.txt"
		//"C:\Users\simeo\OneDrive\Bureau\ListeObj.txt"
		SacADos sacados = new SacADos("C:\\Users\\sniru\\eclipse-workspace\\porjetAAV\\ListeObj.txt", 45);
		sacados.glouttonne();
		/*for(Objet o : sacados.getSac()) {
			System.out.println(o.toString());
		}*/
	}
}
