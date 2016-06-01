package ihm;

import controlleur.*;

import java.util.*;
import java.awt.event.*;
import javax.swing.JFrame;

/**
*Le JFrame qui contient le jeu.
*@Author LAY
*/
public class Fenetre extends JFrame {

	private Collection<AbstractFenetre> fenetres;
	public static int SELECTEUR;
	public static int MENU;
	public static int PARTIE;
	public static int CHARGEUR;
	public static int OPTIONS;
	private int fentreAffiche;

	/**
	 * Rend la fenetre active
	 * @param fCode
	 */
	public void setFenetreActive(int fCode) {
		// TODO - implement Fenetre.setFenetreActive
		throw new UnsupportedOperationException();
	}

	/**
	 * Constructeur de la fenetre
	 * @param moteur, le moteur du jeu.
	 */
	public Fenetre(Moteur moteur) {
		// TODO - implement Fenetre.Fenetre
		throw new UnsupportedOperationException();
	}

	/**
	 * Detecte quand un clic est effectue dans la fenetre
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {
		// TODO - implement Fenetre.mousePressed
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fCode
	 */
	public AbstractFenetre getFenetre(int fCode) {
		// TODO - implement Fenetre.getFenetre
		throw new UnsupportedOperationException();
	}
	
	/**
	*Methode principale
	*@Author LAY
	*/
	public static void main(String[] args) {
		System.out.println("it working");
	}

}