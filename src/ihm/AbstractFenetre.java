package ihm;

import controlleur.*;

import java.awt.event.*;

/**
  * Super class de toutes les fenetres defini les principals methodes et principaux attributs de toutes les fenetres
  */
public abstract class AbstractFenetre {

	private Son son;
	private FondEcran fond;
	private Moteur moteur;

	/**
	 * Constructeur
	 * @param m le Moteur
	 */
	public AbstractFenetre(Moteur m) {
		// TODO - implement AbstractFenetre.AbstractFenetre
		throw new UnsupportedOperationException();
	}
	
	/**
	  * Methode permettant de faire une transition/animation quand la fenetre apparait
	  */
	public abstract void entree();
	
	/**
	  * Methode permettant de faire une transition/animation quand la fenetre disparait
	  */
	public abstract void sortie();

	/**
	 * Methode appeler par le moteur quand un event de souris est genener, permet de dire a la fenetre ou est ce que l'utilisateur a cliqué
	 * @param e l'event
	 */
	public abstract void mousePressed(MouseEvent e);

}