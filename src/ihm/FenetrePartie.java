package ihm;

import controlleur.*;
import blokus.*;
import joueur.*;

import java.util.*;
import java.awt.event.*;

public class FenetrePartie extends AbstractFenetre {

	private MenuPartie menu;
	private Collection<Couleur> plateau;
	private Collection<Piece> pieces;
	private boolean menuAffiche;
	private Piece pieceSelectionne;
	
	public FenetrePartie(Moteur m) {
		super(m);
	}
	
	/**
	 * 
	 * @param tabs
	 */
	public void setPlateau(Couleur[] tabs) {
		// TODO - implement FenetrePartie.setPlateau
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tabs
	 */
	public void setPieces(Piece[] tabs) {
		// TODO - implement FenetrePartie.setPieces
		throw new UnsupportedOperationException();
	}
	
	public void entree() {}

	public void sortie() {}

	/**
	 * 
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {}

}