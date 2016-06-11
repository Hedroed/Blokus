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
	
	private JButton[] boutons;
	
	private Image[] items;
	private Image[] itemsH;
	
	private Image background;
	
	/**
	*Constructeur de la classe MenuPrincipal
	*@Param m, le moteur du jeu
	*/
	public MenuPrincipal(Controlleur c) {
		super(c);
		
		background = Fenetre.loadImage("menuPrincipal/background.jpg");
		
		items = new Image[4];
		items[0] = Fenetre.loadImage("menuPrincipal/jouer.png");
		items[1] = Fenetre.loadImage("menuPrincipal/charger.png");
		items[2] = Fenetre.loadImage("menuPrincipal/options.png");
		items[3] = Fenetre.loadImage("menuPrincipal/quitter.png");
		
		itemsH = new Image[4];
		itemsH[0] = Fenetre.loadImage("menuPrincipal/jouerHover.png");
		itemsH[1] = Fenetre.loadImage("menuPrincipal/chargerHover.png");
		itemsH[2] = Fenetre.loadImage("menuPrincipal/optionsHover.png");
		itemsH[3] = Fenetre.loadImage("menuPrincipal/quitterHover.png");
		
		boutons = new JButton[4];
		
		setLayout(null);
		creeBoutons();
	}
	
	/**
	  * This think do its job
	  */
	public void calculePositions() {
		
		if(boutons != null) {
			
			fond = background.getScaledInstance(Fenetre.WIDTH,Fenetre.HEIGHT, Image.SCALE_SMOOTH);
			
			System.out.println("calculePositions menu");
			
			double x = (Fenetre.WIDTH-500)/2;
			double y = (int)(Fenetre.HEIGHT*0.355);
			double heigth = (int)(Fenetre.HEIGHT*0.16);
			double scaleH = Fenetre.HEIGHT/1080d;
			double scaleW = Fenetre.WIDTH/1920d;
			
			for(int i=0; i<items.length;i++) {
				
				int w = (int)(items[i].getWidth(null)*scaleW);
				int h = (int)(items[i].getHeight(null)*scaleH);
				
				Rectangle rec = new Rectangle((int)x+((500-w)/2),(int)y,w,h);
				boutons[i].setBounds(rec);
				Image iconN = items[i].getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				Image iconO = itemsH[i].getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
				boutons[i].setIcon(new ImageIcon(iconN));
				boutons[i].setRolloverIcon(new ImageIcon(iconO));
				
				y+=heigth;
			}
		}
	}
	
	/**
	  * This think do its job
	  */
	private void creeBoutons() {
		if(boutons != null) {
			
			String[] actions = {Fenetre.SELECTEUR,Fenetre.CHARGEUR,Fenetre.OPTIONS,"quitter"};
			
			for(int i=0; i<items.length; i++) {				
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
	
	/**
	  * This think do its job
	  */
	public void paintComponent(Graphics g) {
		
		// System.out.println("taille screen :"+getWidth()+"::"+getHeight());
		
		g.drawImage(fond,0,0,null);
		
		// if(itemsRect != null) {
			
			// System.out.println("draw items");
			
			// for(int i=0; i<items.length;i++) {
				
				// g.drawImage(items[i],itemsRect[i].x,itemsRect[i].y,itemsRect[i].width,itemsRect[i].height,null);
				
			// }
			
		// }
		
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