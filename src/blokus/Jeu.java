package blokus;

import java.util.*;
import joueur.*;

public class Jeu {

	private Collection<Joueur> joueurs;
	private Plateau plateau;
	private Piece pieceSelectionne;
	private int temps;
	private int tour;
	private int indiceJoueurActif;

	public void nouveauTour() {
		// TODO - implement Jeu.nouveauTour
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	public void selectionnePiece(Piece p) {
		// TODO - implement Jeu.selectionnePiece
		throw new UnsupportedOperationException();
	}

	private void peutContinuerAJouer() {
		// TODO - implement Jeu.peutContinuerAJouer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void place(int x, int y) {
		// TODO - implement Jeu.place
		throw new UnsupportedOperationException();
	}

	public void operation() {
		// TODO - implement Jeu.operation
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