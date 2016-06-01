package joueur;

import IA.*;
import blokus.*;

/**
  * L'IA est un sous type de joueur, elle peut remplacer un joueur durant une partie.
  * Elle posséde toutes les metodes et attribut du joueur.
  * Elle posséde en plus une methode pour placer une piece sur le plateau de jeu.
  * Son attribut difficulte contient l'objet qui fera les calcule du placement de piece.
  */
public class IA extends Joueur {

	private Difficulte difficulte;

	/**
	 * Constructeur identique au joueur mais avec un niveau de difficulté
	 * @param nom nom de l'IA
	 * @param couleur ça couleur
	 * @param niveau le niveau qui permettre de générer un objet Difficulte
	 */
	public IA(String nom, Couleur couleur, String niveau) {
		super(nom, couleur);
		
	}

	/**
	 * Place une piece parmi les pieces restantes sur le plateau, l'algorithme qui placera la piece se trouve dans l'objet Difficulte
	 * @param p le plateau
	 */
	public boolean placePiece(Plateau p) {
		// TODO - implement IA.placePiece
		throw new UnsupportedOperationException();
	}

}