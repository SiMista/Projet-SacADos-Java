package appli;

public class Objet {
	private String nom;
	private float poids;
	private float prix;

	// Pas sûr de ce constructeur
	public Objet() {
		nom = "";
		poids = 0;
		prix = 0;
	}

	public Objet(String nom, float poids, float prix) {
		this.nom = nom;
		this.poids = poids;
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}
	
	public float getPoids() {
		return poids;
	}
	
	public float getPrix() {
		return prix;
	}
		
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPoids(float poids) {
		this.poids = poids;
	}
	
	public void setPrix(float prix) {
		this.prix = prix;
	}
}
