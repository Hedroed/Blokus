package ihm;

import controlleur.*;

import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

/**
*Le panneau de pause en jeu
*
*/

public class PausePanneau extends AbstractPanneau{


	private Image background;
	
	private Image[] imgBoutons;
	private Image[] imgBoutonsH;
	
	private JButton[] boutons;
	
	/**
	  * Constructeur
	  * @param c le Controlleur
	  */
	public PausePanneau(Controlleur c){
		super(c);
		
		background = Fenetre.loadImage("pause/background.png");
		
		imgBoutons = new Image[5];
		imgBoutons[0] = Fenetre.loadImage("pause/reprendre.png");
		imgBoutons[1] = Fenetre.loadImage("pause/sauvegarder.png");
		imgBoutons[2] = Fenetre.loadImage("pause/options.png");
		imgBoutons[3] = Fenetre.loadImage("pause/menuPrincipal.png");
		imgBoutons[4] = Fenetre.loadImage("pause/quitter.png");
		
		imgBoutonsH = new Image[5];
		imgBoutonsH[0] = Fenetre.loadImage("pause/reprendreHover.png");
		imgBoutonsH[1] = Fenetre.loadImage("pause/sauvegarderHover.png");
		imgBoutonsH[2] = Fenetre.loadImage("pause/optionsHover.png");
		imgBoutonsH[3] = Fenetre.loadImage("pause/menuPrincipalHover.png");
		imgBoutonsH[4] = Fenetre.loadImage("pause/quitterHover.png");
		
		boutons = new JButton[5];
		
		addMouseListener(new MouseAdapter() {});
		
		setLayout(null);
		setOpaque(false);
		creeBoutons();
	}
	
	
	
	public void calculePositions() {
		
		if(boutons != null) {
			
			fond = background.getScaledInstance(Fenetre.WIDTH,Fenetre.HEIGHT, Image.SCALE_SMOOTH);
			
			System.out.println("calculePositions menu");
			
			double x = (Fenetre.WIDTH-500)/2;
			double y = (int)(Fenetre.HEIGHT*0.160);
			double heigth = (int)(Fenetre.HEIGHT*0.16);
			double scaleH = Fenetre.HEIGHT/1080d;
			double scaleW = Fenetre.WIDTH/1920d;
			
			for(int i=0; i<imgBoutons.length;i++) {
				
				int w = (int)(imgBoutons[i].getWidth(null)*scaleW);
				int h = (int)(imgBoutons[i].getHeight(null)*scaleH);
				
				Rectangle rec = new Rectangle((int)x+((500-w)/2),(int)y,w,h);
				boutons[i].setBounds(rec);
				Image iconN = imgBoutons[i].getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				Image iconO = imgBoutonsH[i].getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				boutons[i].setIcon(new ImageIcon(iconN));
				boutons[i].setRolloverIcon(new ImageIcon(iconO));
				
				y+=heigth;
			}
		}
	}
	
	
	private void creeBoutons() {
		if(boutons != null) {
			
			String[] actions = {"fermeMenu","sauvegarde",Fenetre.OPTIONS,Fenetre.MENU,"quitter"};
			
			for(int i=0; i<imgBoutons.length; i++) {				
				JButton b = new JButton();
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setFocusable(false);
				b.setBorderPainted(false);
				b.addActionListener(control);
				b.setActionCommand(actions[i]);
				
				boutons[i]=b;
				this.add(b);
			}
			
		}
	}
	
	public void paintComponent(Graphics g) {
		
		g.drawImage(fond,0,0,null);
	}
	
	public void sortie(){}
	public void entree(){}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setSize(1280,720);
		PausePanneau p = new PausePanneau(null);
		f.add(p);
		f.setVisible(true);
		Fenetre.WIDTH=1600;
		Fenetre.HEIGHT=900;
		p.calculePositions();
		
		
	}
}