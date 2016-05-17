package blokus;

import java.util.*;
import joueur.*;

import java.awt.Point;

/**
  * Le Plateau contient un tableau de tout les blocks placés sur le plateau de jeu.
  * Il permet de placer une piece sur le plateau ou de savoir si on peu en placer.
  */
public class Plateau {

	private Collection<Bloc> plateau;
	private Point[] entre;

	/**
	 * Determine pour un joueur tout les coordonée ou il peut placer un block pour ensuite l'affiché 
	 * et ainsi permettre au joueur de trouver plus facilement ou placer ces pieces
	 * crée un tableau de Point dans l'attribut entre
	 * @param j le joueur
	 */
	public void trouveEnterPossible(Joueur j) {
		// TODO - implement Plateau.trouveEnterPossible
		throw new UnsupportedOperationException();
	}

	/**
	 * Place une piece dans le plateau a une certaine coordonée en fonction de la rotation de la piece
	 * il vérifie avant s'il peut placer cette piece
	 * @param p la piece
	 * @param x coordonée x
	 * @param y coordonée y
	 */
	public void placePiece(Piece p, int x, int y) {
		// TODO - implement Plateau.placePiece
		throw new UnsupportedOperationException();
	}

	/**
	 * Vérifie si une piece peu etre placer a des coordonée
	 * @param p la piece
	 * @param x coordonée x
	 * @param y coordonée y
	 * @return true si la piece peu etre placée
	 */
	public boolean peutPlacerPiece(Piece p, int x, int y) {
		// TODO - implement Plateau.peutPlacerPiece
		throw new UnsupportedOperationException();
	}

	/**
	 * Vérifier si une piece peu etre placer n'importe ou sur le damier
	 * @param p la piece
	 * @return true si la piece peu etre placer quelque pars
	 */
	public void peutJouerPiece(Piece p) {
		// TODO - implement Plateau.peutJouerPiece
		throw new UnsupportedOperationException();
	}

}