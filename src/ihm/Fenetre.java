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
*Le JFrame qui contient le jeu.
*@Author LAY
*/
public class Fenetre extends JFrame{

	public static final String SELECTEUR = "4";
	public static final String MENU = "0";
	public static final String PARTIE = "3";
	public static final String CHARGEUR = "1";
	public static final String OPTIONS = "2";
	
	public static int WIDTH;
	public static int HEIGHT;
	
	private AbstractPanneau[] panneaux;
	
	//content pane
	private int fentreAffiche;
	private CardLayout layout;
	private JPanel content;
	private String currentPanel;
	
	//glass pane
	private JPanel glass;
	private CardLayout layoutGlass;
	private PausePanneau pausePanneau;
	private ScorePanneau scorePanneau;
	
	private boolean estPleinEcran;
	
	/**
	 * Constructeur de la fenetre
	 */
	public Fenetre() { //Moteur moteur
		
		this.setTitle("Blokus");
		this.setSize(711, 400);
		this.setMaximumSize(new Dimension(1920,1080));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setUndecorated(true);
		
		setContentPane(new PanneauAttente());
		
		this.setVisible(true);
		this.setBackground(new Color(0, 0, 0, 0));
		
		Dimension d = getToolkit().getScreenSize();
		WIDTH = d.width;
		HEIGHT = d.height;
		estPleinEcran = false;
		
		content = new JPanel();
		layout = new CardLayout();
		content.setLayout(layout);
		Controlleur c = new Controlleur(this);
		
		panneaux = new AbstractPanneau[5];
		panneaux[0] = new MenuPrincipal(c);
		panneaux[1] = new ChargementPartie(c);
		panneaux[2] = new FenetreOptions(c);
		panneaux[3] = new FenetrePartie(c);
		panneaux[4] = new SelecteurPartie(c);
		
		content.add(panneaux[0],MENU);
		content.add(panneaux[1],CHARGEUR);
		content.add(panneaux[2],OPTIONS);
		content.add(panneaux[3],PARTIE);
		content.add(panneaux[4],SELECTEUR);
		
		setContentPane(content);
		setFenetreActive(MENU);
		
	
		glass = (JPanel)getGlassPane();
		layoutGlass = new CardLayout();
		glass.setLayout(layoutGlass);
		pausePanneau = new PausePanneau(c);
		scorePanneau = new ScorePanneau(c);
		glass.add(pausePanneau,"pause");
		glass.add(scorePanneau,"score");
		
		// try {
			// Thread.sleep(100);
		// } catch(InterruptedException ex) {
			// ex.printStackTrace();
		// }
		
		setBackground(Color.white);
		
		nouvelleResolution();
		
		// addKeyListener(new ActionClavier());
	}
	
	
	/**
	 * Rend la fenetre active
	 * @param fCode
	 */
	public void setFenetreActive(String fCode) {
		System.out.println("show "+fCode);
		currentPanel = fCode;
		layout.show(content, fCode);//si la fenetre n'exite pas rien ne se passe
	}
	
	/**
	 * Affiche la fenetre de pause du glass panel
	 * @param fCode
	 */
	public void setPausePanneauVisible(boolean b) {
		layoutGlass.show(glass,"pause");
		glass.setVisible(b);
	}
	
	/**
	 * Affiche la fenetre de score du glass panel
	 * @param fCode
	 */
	public void setScorePanneauVisible(boolean b) {
		layoutGlass.show(glass,"score");
		glass.setVisible(b);
	}
	
	/**
	  * This think do its job
	  */
	public void nouvelleResolution() {
		
		FenetreOptions pan = (FenetreOptions)panneaux[2];
		
		Dimension d = pan.getResolution(); //panneaux[2] est optionsPanneau
		WIDTH = d.width;
		HEIGHT = d.height;
		
		Dimension screen = getToolkit().getScreenSize();
		if(screen.width < d.width || screen.height < d.height) {
			WIDTH = screen.width;
			HEIGHT = screen.height;
		}
		
		boolean full = pan.estPleinEcran();
		
		if(estPleinEcran && !full) {
			dispose();
			setLocation((screen.width-WIDTH)/2,(screen.height-60-HEIGHT)/2);
			setSize(WIDTH,HEIGHT+30);
			setUndecorated(false);
			setVisible(true);
			estPleinEcran = false;
		}
		else if(!estPleinEcran && full) {
			dispose();
			setUndecorated(true);
			setSize(WIDTH,HEIGHT);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);
			estPleinEcran = true;
		}
		else if(!estPleinEcran && !full) {
			if(isUndecorated()) {
				dispose();
				setUndecorated(false);
			}
			setLocation((screen.width-WIDTH)/2,(screen.height-60-HEIGHT)/2);
			setSize(WIDTH,HEIGHT+30);
			setVisible(true);
		}
		
		for(int i=0; i<panneaux.length; i++) {
			panneaux[i].calculePositions();
		}
		
		pausePanneau.calculePositions();
		scorePanneau.calculePositions();
		
	}

	/**
	 * 
	 * @param fCode
	 */
	public AbstractPanneau getFenetre(String fCode) {
		int ind = -1;
		try {
			ind = Integer.parseInt(fCode);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		System.out.println("getFenetre int:"+ind+" fCode:"+fCode);
		
		if(ind < 0 || ind >= panneaux.length) {throw new IllegalArgumentException("indice incorrect");}
		return panneaux[ind];
	}
	
	public FenetrePartie getFenetrePartie() {
		return ((FenetrePartie)panneaux[3]);
	}
	
	public PausePanneau getPausePanneau() {
		return pausePanneau;
	}
	
	public ScorePanneau getScorePanneau() {
		return scorePanneau;
	}
	
	/**
	  * Charge une image depuis les sources de l'application
	  * @param s le chemin de l'image
	  * @return l'image en object BufferedImage
	  */
	public static BufferedImage loadImage(String s) {
		
		BufferedImage ret = null;
		
		try{
			ret = ImageIO.read(ClassLoader.getSystemResourceAsStream(s));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	  * Quit l'application
	  */
	public void quitter() {
		System.out.println("Au revoir !!!");
		System.exit(0);
	}
	
	public class ActionClavier implements KeyListener{
		
		public void keyPressed(KeyEvent e) {
			setFenetreActive(MENU);
		}
		
		public void keyTyped(KeyEvent e) {}
		
		public void keyReleased(KeyEvent e) {}
		
	}
	
	/**
	*Methode principale
	*@Author LAY
	*/
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		
	}
	
	/**
	  * Create and show GUI :)
	  */
	private static void createAndShowGUI() {
		new Fenetre();
	}
	
	
}