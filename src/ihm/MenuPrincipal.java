package ihm;

import controlleur.*;

import java.awt.event.*;

/**
*Classe heritant d'AbstractFenetre qui est le JPanel du Menu Principal
*@extends AbstractFenetre , la classe mere de tous les JPanels du jeu
*/
public class MenuPrincipal extends AbstractFenetre {
	
	/**
	*Constructeur de la classe MenuPrincipal
	*@Param m, le moteur du jeu
	*/
	public MenuPrincipal(Moteur m) {
		super(m);
	}
	
	/**
	*Lorsque l'on arrive sur cet ecran
	*/
	public void entree() {}
	
	/**
	*Lorsque l'on sors de cet ecran
	*/
	public void sortie() {}
	
	/**
	 * Detecte les clics effectues sur cet ecran
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {}

}