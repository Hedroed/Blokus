package blokus;

/**
  * Definie une couleur graphique, elle permet aussi de determiner la couleur d'un joueur ou d'un bloc
  */
public class Couleur {

	private String nom;
	
	/**
	  * Constructeur prend le nom de sa couleur
	  */
	public Couleur(String c) {
		
		nom = c;
		
	}
	
	public boolean equals(Couleur autre) {
		return this.nom.equals(autre.nom);
	}
	
	/**
	 * Retourne la couleur sous forme de String
	 */
	public String toString() {
		return nom;
	}

}