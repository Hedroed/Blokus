package ihm;

import controlleur.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.IOException;

public class SelecteurPartie extends AbstractPanneau {
	
	private JButton retourBouton;
	private JButton jouerBouton;
	
	private Image retour;
	private Image jouer;
	private Image retourOver;
	private Image jouerOver;
	private Image background;
	private Font laPolice;
	
	private Color[] couleurs;
	private String[] niveau;
	private String[] type;
	
	private JButton[] selecteurs;
	
	private SelecteurControlleur selectControl;
	
	/**
	  * Constructeur
	  * @param c le Controlleur
	  */
	public SelecteurPartie(Controlleur c) {
		super(c);
		
		background = Fenetre.loadImage("selecteur/background.png");
		
		retour = Fenetre.loadImage("selecteur/retour.png");
		jouer = Fenetre.loadImage("selecteur/jouer.png");
		retourOver = Fenetre.loadImage("selecteur/retourOver.png");
		jouerOver = Fenetre.loadImage("selecteur/jouerOver.png");
		
		try {
			laPolice = Font.createFont(Font.TRUETYPE_FONT,ClassLoader.getSystemResourceAsStream("Electronica.ttf"));
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		laPolice = laPolice.deriveFont(60f);
		
		couleurs = new Color[4];
		couleurs[0] = new Color(0,169,227);
		couleurs[1] = new Color(246, 255, 0);
		couleurs[2] = Color.red;
		couleurs[3] = new Color(30,254,0);
		
		type = new String[] {"Joueur 1","Joueur 2","Joueur 3","Joueur 4"};
		niveau = new String[4];
		
		selecteurs = new JButton[4];
		
		selectControl = new SelecteurControlleur(this);
		control.setSelecteurControlleur(selectControl);
		
		setLayout(null);
		creeBoutons();
	}
	
	/**
	  * This think do its job
	  */
	public void paintComponent(Graphics g) {
		g.drawImage(fond,0,0,null);
		
		g.setFont(laPolice);
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		for(int i = 0; i<couleurs.length; i++) {
			
			g.setColor(couleurs[i]);
			
			Rectangle rec = selecteurs[i].getBounds();
			
			double x = rec.getX();
			double y = rec.getY();
			
			int len = 0;
			if(type[i] != null) {
				len = g.getFontMetrics().stringWidth(type[i]);
				g.drawString(type[i],(int)x+((rec.width-len)/2),(int)(y+(rec.height*0.33)));
			}
			
			if(niveau[i] != null) {
				len = g.getFontMetrics().stringWidth(niveau[i]);
				g.drawString(niveau[i],(int)x+((rec.width-len)/2),(int)(y+(rec.height*0.80)));
			}
			
			
		}
		
	}
	
	
	/**
	  * This think do its job
	  */
	public void calculePositions() {
		
		System.out.println("calculePositions selecteur");
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
		
		w = (int)(363*scaleW);
		h = (int)(243*scaleH);
		selecteurs[0].setBounds(new Rectangle((int)(Fenetre.WIDTH*0.223),(int)(Fenetre.HEIGHT*0.255),w,h));
		selecteurs[1].setBounds(new Rectangle((int)(Fenetre.WIDTH*0.587),(int)(Fenetre.HEIGHT*0.255),w,h));
		selecteurs[2].setBounds(new Rectangle((int)(Fenetre.WIDTH*0.223),(int)(Fenetre.HEIGHT*0.562),w,h));
		selecteurs[3].setBounds(new Rectangle((int)(Fenetre.WIDTH*0.587),(int)(Fenetre.HEIGHT*0.562),w,h));
		
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
		jouerBouton.setActionCommand("nouvelle");
		
		this.add(jouerBouton);
	
		for(int i=0; i<selecteurs.length; i++) {
			JButton b = new JButton();
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setFocusable(false);
			b.setBorderPainted(false);
			b.addActionListener(selectControl);
			b.setActionCommand(i+"");
			
			selecteurs[i] = b;
			this.add(b);
			
		}
		
	}
	
	/**
	  * This think do its job
	  */
	public void setNoms(int i,String t, String n) {
		if(i >= 0 && i < 4) {
			type[i] = t;
			niveau[i] = n;
		}
		repaint();
	}
	

	
	
	public void entree() {}

	public void sortie() {}

}