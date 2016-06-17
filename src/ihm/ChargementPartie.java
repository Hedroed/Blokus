package ihm;

import controlleur.*;
import inout.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

/**
  * Fenetre de chargement de partie sauvegardée
  * Elle utilise une classe Chargement pour cela
  */
public class ChargementPartie extends AbstractPanneau {
	
	private JButton retourBouton;
	private JButton jouerBouton;
	
	private Image retour;
	private Image jouer;
	private Image retourOver;
	private Image jouerOver;
	private Image background;
	
	private ArrayList<SauvegardeInfo> sauvegardes;
	
	private String[] joueurs;
	private String[] dates;
	private String[] tours;
	
	private Font laPolice;
	
	private ChargeurControlleur chargeControl;
	
	private int indiceSelect;
	
	/**
	  * Constructeur
	  * @param c le Controlleur
	  */
	public ChargementPartie(Controlleur c) {
		super(c);
		
		background = Fenetre.loadImage("charger/background.png");
		
		retour = Fenetre.loadImage("charger/retour.png");
		jouer = Fenetre.loadImage("charger/jouer.png");
		retourOver = Fenetre.loadImage("charger/retourOver.png");
		jouerOver = Fenetre.loadImage("charger/jouerOver.png");
		
		try {
			laPolice = Font.createFont(Font.TRUETYPE_FONT,ClassLoader.getSystemResourceAsStream("Electronica.ttf"));
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		laPolice = laPolice.deriveFont(30f);
		
		chargeControl = new ChargeurControlleur(this);
		control.setChargeurControlleur(chargeControl);
		addMouseListener(chargeControl);
		
		setLayout(null);
		creeBoutons();
	}
	
	public void paintComponent(Graphics g) {		
		g.drawImage(fond,0,0,null);
		
		if(joueurs != null && dates != null && tours != null) {
			g.setFont(laPolice);
			
			// System.out.println("draw texte sauvegardes");
			
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			int x = (int)(Fenetre.WIDTH*0.205);
			int y = (int)(Fenetre.HEIGHT*0.37);
			
			int h = 40;
			
			int w1 = (int)(Fenetre.WIDTH*0.18);
			int w2 = (int)(Fenetre.WIDTH*0.2);
			int w3 = (int)(Fenetre.WIDTH*0.25);
			
			for(int i=0; i<joueurs.length; i++) {
				
				if(indiceSelect == i) {
					g.setColor(new Color(30,254,0));
				}
				else {
					g.setColor(new Color(246, 255, 0));
				}
				
				int len = g.getFontMetrics().stringWidth(joueurs[i]);
				g.drawString(joueurs[i],x+(w1-len)/2,y+i*h);
				
				len = g.getFontMetrics().stringWidth(dates[i]);
				g.drawString(dates[i],x+w1+(w2-len)/2,y+i*h);
				
				len = g.getFontMetrics().stringWidth(tours[i]);
				g.drawString(tours[i],x+w1+w2+(w3-len)/2,y+i*h);
				
			}
		}
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
	  *Cree les boutons du panneau
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
		jouerBouton.setActionCommand("charger");
		
		this.add(jouerBouton);

	}
	
	public void select(int i) {
		if(i >= 0 && i < 8) {
			indiceSelect = i;
		}
		repaint();
	}
	
	public void setSauvegardes(String[] js, String[] ds, String[] ts) {
		indiceSelect = 0;
		
		if(js.length == ds.length && ds.length == ts.length) {
			
			joueurs = js;
			dates = ds;
			tours = ts;
			
		}
		else {
			throw new IllegalArgumentException("Longueurs inegales");
		}
		repaint();
	}
	
	
	
	/**
	  * Appelé quand on quitte la fenetre
	  */
	public void entree() {}

	/**
	  * Appelé quand on entre dans la fenetre
	  */
	public void sortie() {}
	
	
}