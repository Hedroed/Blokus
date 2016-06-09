package ihm;

import controlleur.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
*Classe heritant d'AbstractFenetre qui est le JPanel du Menu Principal
*@extends AbstractFenetre , la classe mere de tous les JPanels du jeu
*/
public class MenuPrincipal extends AbstractPanneau {
	
	private Image[] items;
	
	/**
	*Constructeur de la classe MenuPrincipal
	*@Param m, le moteur du jeu
	*/
	public MenuPrincipal(Moteur m) {
		super(m);
		
		setFond("menuPrincipal/background.jpg");
		
		items = new Image[4];
		items[0] = Fenetre.loadImage("menuPrincipal/jouer.png");
		items[1] = Fenetre.loadImage("menuPrincipal/charger.png");
		items[2] = Fenetre.loadImage("menuPrincipal/options.png");
		items[3] = Fenetre.loadImage("menuPrincipal/quitter.png");
	}
	
	/**
	  * This think do its job
	  */
	public void paintComponent(Graphics g) {
		
		g.drawImage(fond,0,0,getWidth(),getHeight(),null);
		
		int x = (getWidth()-500)/2;
		int y = (int)(getHeight()*0.38);
		for(int i=0; i<items.length;i++) {
			
			g.drawImage(items[i],x,y,null);
			y+=(int)(getHeight()*0.15);
			
		}
		
		
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