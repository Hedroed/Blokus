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
	
	/**
	 * Retourne la couleur sous forme de String
	 */
	public String toString() {
		return nom;
	}

}