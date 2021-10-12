package méthodesDeRésolution;

import java.util.ArrayList;

import appli.Objet;
import appli.SacADos;

public class Glouttonne {
	public void résoudre(SacADos sac) {
		ArrayList<Objet> tmp = triRapide(sac.getListeObjets());
		while (!sac.getListeObjets().isEmpty()) {
			if (sac.getPoidsActuel() + sac.getListeObjets().get(0).getPoids() <= sac.getPoidsMax()) {
				sac.ajouter(sac.getListeObjets().get(0));
				sac.getListeObjets().remove(0);
			} else
				break;
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
}
