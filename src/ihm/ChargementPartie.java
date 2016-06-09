package ihm;

import controlleur.*;

import java.awt.event.*;

/**
  * Fenetre de chargement de partie sauvegardée
  * Elle utilise une classe Chargement pour cela
  */
public class ChargementPartie extends AbstractPanneau {

	private Chargement chargeur;
	
	/**
	  * Constructeur
	  * @param m le moteur du jeu
	  */
	public ChargementPartie(Moteur m) {
		super(m);
	}
	
	/**
	  * Appelé quand on quitte la fenetre
	  */
	public void entree() {}

	/**
	  * Appelé quand on entre dans la fenetre
	  */
	public void sortie() {}

	/**
	 * Réagit en fonction de l'event de souris
	 * @param e l'event
	 */
	public void mousePressed(MouseEvent e) {}

}