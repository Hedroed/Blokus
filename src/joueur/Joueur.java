package joueur;

import java.util.*;
import blokus.*;

/**
  * Le Joueur contient tout les parametres d'un joueur durant une partie de Blokus
  * Une liste de pieces, une couleur et un nom
  * Il permet de savoir quel joueur est en train de jouer.
  */
public class Joueur {

	private Collection<Piece> pieces;
	private Couleur couleur;
	private String nom;
	private boolean peutJouer;

	/**
	 * Constructeur prend un nom et une couleur
	 * @param nom
	 * @param couleur
	 */
	public Joueur(String nom, Couleur couleur) {
		// TODO - implement Joueur.Joueur
		throw new UnsupportedOperationException();
	}
	
	/**
	  * Initalise les pieces en debut de partie
	  * est appeler par le constructeur
	  */
	private void creePieces() {
		// TODO - implement Joueur.creePieces
		throw new UnsupportedOperationException();
	}
	
	/**
	  * Permet de savoir si ce joueur est une IA pour appeler la method placePiece de l'IA
	  * @return un boolean
	  */
	public boolean isIA() {

		return false;
	}

}