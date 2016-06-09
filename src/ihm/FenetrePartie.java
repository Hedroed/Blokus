package ihm;

import controlleur.*;
import blokus.*;
import joueur.*;

import java.util.*;
import java.awt.event.*;

/**
*Le JPanel qui contient le plateau du jeu.
*@extends AbstractFenetre
*/
public class FenetrePartie extends AbstractPanneau {

	private MenuPartie menu;
	private Collection<Couleur> plateau;
	private Collection<Piece> pieces;
	private boolean menuAffiche;
	private Piece pieceSelectionne;
	
	/**
	*Constructeur de la classe FenetrePartie
	*@Param m, le moteur du jeu
	*/
	public FenetrePartie(Moteur m) {
		super(m);
	}
	
	/**
	 * Met sous forme graphique le plateau du jeu.
	 * @param p , le plateau du jeu
	 */
	public void setPlateau(Plateau p) {
		// TODO - implement FenetrePartie.setPlateau
		throw new UnsupportedOperationException();
	}

	/**
	 * Met sous forme graphique les pieces installees sur le plateau
	 * @param tabs
	 */
	public void setPieces(Piece[] tabs) {
		// TODO - implement FenetrePartie.setPieces
		throw new UnsupportedOperationException();
	}
	
	/**
	*Lorsque l'on entre dans cet ecran
	*
	*/
	public void entree() {}
	
	/**
	*Lorsque l'on sors de cet ecran
	*
	*/
	public void sortie() {}

	/**
	 * Detecte les clics sur ce JPanel
	 * @param e , le MouseEvent qui en decoule
	 */
	public void mousePressed(MouseEvent e) {}

}