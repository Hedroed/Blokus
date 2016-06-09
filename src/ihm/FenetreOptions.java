package ihm;

import controlleur.*;

import java.awt.event.*;


/**
*JPanel contenant toutes les options et reglages.
*/
public class FenetreOptions extends AbstractPanneau{
	
	
	/**
	*Le constructeur de la classe.
	*@Param m , le Moteur du jeu.
	*/
	public FenetreOptions(Moteur m) {
		super(m);
	}
	
	/**
	*Lorsque l'on entre dans cet ecran
	*
	*/
	public void entree() {}
	
	/**
	*Lorsque l'on quitte cet ecran
	*/
	public void sortie() {}

	/**
	 * Detecte les clics effectues sur ce JPanel
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {}
	
}