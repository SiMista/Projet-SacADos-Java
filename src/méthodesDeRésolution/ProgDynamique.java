package méthodesDeRésolution;

import java.util.ArrayList;

import appli.Objet;
import appli.SacADos;

public class ProgDynamique {
	public void résoudre(SacADos sac) {
		float coeff = coefMultiplicateur(sac.getListeObjets());
		int ligneMax = sac.getNbObjetsMax();
		int colonneMax = (int) (sac.getPoidsMax() * coeff);
		float tabVal[][] = new float[ligneMax][colonneMax + 1];

		for (int j = 0; j <= colonneMax; ++j) {
			if (sac.getListeObjets().get(0).getPoids() * coeff > j)
				tabVal[0][j] = 0F;
			else
				tabVal[0][j] = sac.getListeObjets().get(0).getPrix();
		}

		for (int i = 1; i < ligneMax; ++i) {
			for (int j = 0; j <= colonneMax; ++j) {
				if (sac.getListeObjets().get(i).getPoids() * coeff > j)
					tabVal[i][j] = tabVal[i - 1][j];
				else
					tabVal[i][j] = Math.max(tabVal[i - 1][j],
							tabVal[i - 1][(int) (j - sac.getListeObjets().get(i).getPoids() * coeff)]
									+ sac.getListeObjets().get(i).getPrix());
			}
		}

		int i = ligneMax - 1;
		int j = colonneMax;

		while (j > 0 && tabVal[i][j] == tabVal[i][j - 1])
			--j;

		while (j > 0) {
			while (i > 0 && tabVal[i][j] == tabVal[i - 1][j])
				--i;
			j = (int) (j - sac.getListeObjets().get(i).getPoids() * coeff);
			if (j >= 0) {
				sac.ajouter(sac.getListeObjets().get(i));
				System.out.println(j);
			}
			--i;
		}
	}

	private static int nombreDeChiffreAprèsLaVirgule(float n) {
		String nombre = String.valueOf(n);
		int positionVirgule = nombre.indexOf(".") + 1;
		String partieDecimale = nombre.substring(positionVirgule, nombre.length());

		while (partieDecimale.endsWith("0"))
			partieDecimale = nombre.substring(positionVirgule++, nombre.length());
		return partieDecimale.length();
	}

	private int coefMultiplicateur(ArrayList<Objet> listeObjet) {
		int maximumActuel = 0;
		for (Objet o : listeObjet) {
			maximumActuel = Math.max(maximumActuel, nombreDeChiffreAprèsLaVirgule(o.getPoids()));
		}
		if (maximumActuel == 0)
			return 1;
		return (int) Math.pow(10, maximumActuel);
	}
}
