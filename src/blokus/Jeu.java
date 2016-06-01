package blokus;

import java.util.*;
import joueur.*;

/**
  * Le Jeu represente toute une partie de Blokus 
  * il contient les joueurs/IA le plateau de jeu, la piece selectionné et d'autre indicateur comme le temps de jeu et le nombre de tours
  */
public class Jeu {

	private Collection<Joueur> joueurs;
	private Plateau plateau;
	private Piece pieceSelectionne;
	//en miliseconde
	private int temps;
	private int tour;
	private int indiceJoueurActif;
	
	/**
	  * Passe au tour suivant, increment l'attribut tour et passe au joueur suivant
	  */
	public void nouveauTour() {
		// TODO - implement Jeu.nouveauTour
		throw new UnsupportedOperationException();
	}

	/**
	 * Selectionne une piece du joueur dans l'attibut pieceSelectionne
	 * @param p la piece
	 */
	public void selectionnePiece(Piece p) {
		// TODO - implement Jeu.selectionnePiece
		throw new UnsupportedOperationException();
	}
	
	/**
	  * Vérifie que le joueur courant a encore des pieces ou que sa plus petite pieces peut etre placer, sinon il ne peut plus jouer
	  * @return true si le joueur peu continuer
	  */
	private boolean peutContinuerAJouer() {
		// TODO - implement Jeu.peutContinuerAJouer
		throw new UnsupportedOperationException();
	}

	/**
	 * Place la piece courant, s'il y en a une, sur le plateau 
	 * @param x coordonné x
	 * @param y coordonné y
	 */
	public void place(int x, int y) {
		// TODO - implement Jeu.place
		throw new UnsupportedOperationException();
	}
	
	public Plateau getPlateau() {
		return this.plateau;
	}

	public Joueur getJoueurActif() {
		// TODO - implement Jeu.getJoueurActif
		throw new UnsupportedOperationException();
	}

	public Joueur[] getJoueurs() {
		// TODO - implement Jeu.getJoueurs
		throw new UnsupportedOperationException();
	}

}