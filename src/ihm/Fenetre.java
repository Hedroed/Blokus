package ihm;

import controlleur.*;

import java.util.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class Fenetre extends JFrame {

	private Collection<AbstractFenetre> fenetres;
	public static int SELECTEUR;
	public static int MENU;
	public static int PARTIE;
	public static int CHARGEUR;
	public static int OPTIONS;
	private int fentreAffiche;

	/**
	 * 
	 * @param fCode
	 */
	public void setFenetreActive(int fCode) {
		// TODO - implement Fenetre.setFenetreActive
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param moteur
	 */
	public Fenetre(Moteur moteur) {
		// TODO - implement Fenetre.Fenetre
		throw new UnsupportedOperationException();
	}

	/**
	 * 
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
	
	public static void main(String[] args) {
		System.out.println("it working");
	}

}