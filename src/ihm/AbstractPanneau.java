package ihm;

import controlleur.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

/**
  * Super class de toutes les fenetres defini les principals methodes et principaux attributs de toutes les fenetres
  */
public abstract class AbstractPanneau extends JPanel{

	private Son son;
	protected Image fond;
	private Moteur moteur;

	/**
	 * Constructeur
	 * @param m le Moteur
	 */
	public AbstractPanneau(Moteur m) {
		if(m == null) {throw new IllegalArgumentException("Moteur null");}
		
		moteur = m;
		
	}
	
	protected void setFond(String path) {
		fond = Fenetre.loadImage(path);
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