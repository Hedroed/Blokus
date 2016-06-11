package ihm;

import controlleur.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
  * Fenetre de chargement de partie sauvegardée
  * Elle utilise une classe Chargement pour cela
  */
public class ChargementPartie extends AbstractPanneau {

	private Chargement chargeur;
	
	private JButton retourBouton;
	private JButton jouerBouton;
	
	private Image retour;
	private Image jouer;
	private Image retourOver;
	private Image jouerOver;
	private Image background;
	
	
	
	/**
	  * Constructeur
	  * @param m le moteur du jeu
	  */
	public ChargementPartie(Controlleur c) {
		super(c);
		
		background = Fenetre.loadImage("charger/background.png");
		
		retour = Fenetre.loadImage("charger/retour.png");
		jouer = Fenetre.loadImage("charger/jouer.png");
		retourOver = Fenetre.loadImage("charger/retourOver.png");
		jouerOver = Fenetre.loadImage("charger/jouerOver.png");

		setLayout(null);
		creeBoutons();
	}
	
	/**
	  * Positionne les éléments sur le panneau
	  */
	public void calculePositions() {
		
		fond = background.getScaledInstance(Fenetre.WIDTH,Fenetre.HEIGHT, Image.SCALE_SMOOTH);
		
		double scaleH = Fenetre.HEIGHT/1080d;
		double scaleW = Fenetre.WIDTH/1920d;
		double y = Fenetre.HEIGHT*0.84;
		
		System.out.println("y : "+y);
		
		int w = (int)(retour.getWidth(null)*scaleW);
		int h = (int)(retour.getHeight(null)*scaleH);
		double x = Fenetre.WIDTH*0.13;
		Rectangle rec = new Rectangle((int)x,(int)y,w,h);
		retourBouton.setBounds(rec);
		Image iconN = retour.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		Image iconO = retourOver.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		retourBouton.setRolloverIcon(new ImageIcon(iconO));
		retourBouton.setIcon(new ImageIcon(iconN));
		
		w = (int)(jouer.getWidth(null)*scaleW);
		h = (int)(jouer.getHeight(null)*scaleH);
		x = (Fenetre.WIDTH*0.87)-w;
		rec = new Rectangle((int)x,(int)y,w,h);
		jouerBouton.setBounds(rec);
		iconN = jouer.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		iconO = jouerOver.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		jouerBouton.setIcon(new ImageIcon(iconN));
		jouerBouton.setRolloverIcon(new ImageIcon(iconO));
	}
	
	/**
	  * This think do its job
	  */
	private void creeBoutons() {
		
		retourBouton = new JButton();
		retourBouton.setOpaque(false);
		retourBouton.setContentAreaFilled(false);
		retourBouton.setFocusable(false);
		retourBouton.setBorderPainted(false);
		retourBouton.addActionListener(control);
		retourBouton.setActionCommand(Fenetre.MENU);
		
		this.add(retourBouton);
		
		
		jouerBouton = new JButton();
		jouerBouton.setOpaque(false);
		jouerBouton.setContentAreaFilled(false);
		jouerBouton.setFocusable(false);
		jouerBouton.setBorderPainted(false);
		jouerBouton.addActionListener(control);
		jouerBouton.setActionCommand(Fenetre.PARTIE);
		
		this.add(jouerBouton);

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
	
	public void paintComponent(Graphics g) {		
		g.drawImage(fond,0,0,null);		
	}
}