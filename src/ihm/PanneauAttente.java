package ihm;

import java.awt.*;
import javax.swing.*;

/**
  * Panneau qui apparait en premier lors du lancement de l'application
  * Disparait lorsque toutes les fenetres sont chargées
  */
public class PanneauAttente extends JPanel {
	
	private Image background;
	
	/**
	*Constructeur de la classe PanneauAttente
	*/
	public PanneauAttente() {
		background = Fenetre.loadImage("background/chargement.png");
		setOpaque(false);
	}
	
	/**
	  * Paint le panneau sur l'ecran
	  */
	public void paintComponent(Graphics g) {

		g.drawImage(background,0,0,getWidth(),getHeight(),null);
	}

}