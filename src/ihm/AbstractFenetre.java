package ihm;

import controlleur.*;

import java.awt.event.*;

public abstract class AbstractFenetre {

	private Son son;
	private FondEcran fond;
	private Moteur moteur;

	/**
	 * 
	 * @param m
	 */
	public AbstractFenetre(Moteur m) {
		// TODO - implement AbstractFenetre.AbstractFenetre
		throw new UnsupportedOperationException();
	}

	public abstract void entree();

	public abstract void sortie();

	/**
	 * 
	 * @param e
	 */
	public abstract void mousePressed(MouseEvent e);

}