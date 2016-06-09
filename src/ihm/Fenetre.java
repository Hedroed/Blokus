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
public class Fenetre extends JFrame {

	public static final String SELECTEUR = "0";
	public static final String MENU = "1";
	public static final String PARTIE = "2";
	public static final String CHARGEUR = "3";
	public static final String OPTIONS = "4";
	public static final String MENUPARTIE = "5";
	
	
	private int fentreAffiche;
	private ArrayList<AbstractFenetre> fenetres;
	private CardLayout layout;
	private JPanel content;
	private String currentPanel;
	
	/**
	 * Constructeur de la fenetre
	 * @param moteur, le moteur du jeu.
	 */
	public Fenetre() { //Moteur moteur
		
		this.setTitle("Blokus");
		this.setSize(711, 400);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setUndecorated(true);
		
		setContentPane(new PanneauAttente());
		
		this.setVisible(true);
		this.setBackground(new Color(0, 0, 0, 0));
		
		try {
			Thread.sleep(800);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		content = new JPanel();
		layout = new CardLayout();
		content.setLayout(layout);
		Moteur m= new Moteur();
		
		content.add(new MenuPrincipal(m),MENU);
		content.add(new ChargementPartie(m),CHARGEUR);
		content.add(new FenetreOptions(m),OPTIONS);
		content.add(new FenetrePartie(m),PARTIE);
		content.add(new SelecteurPartie(m),SELECTEUR);
		
		setContentPane(content);
		
		setVisible(false);
		setSize(getToolkit().getScreenSize());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	
	/**
	 * Rend la fenetre active
	 * @param fCode
	 */
	public void setFenetreActive(String fCode) {
		layout.show(content, fCode);//si la fenetre n'exite pas rien ne se passe
		System.out.println("show "+fCode);
		currentPanel = fCode;
		repaint();
	}
	
	/**
	 * Detecte quand un clic est effectue dans la fenetre
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