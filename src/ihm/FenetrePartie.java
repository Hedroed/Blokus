package ihm;

import controlleur.*;
import blokus.*;
import joueur.*;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
*Le JPanel qui contient le plateau du jeu.
*@extends AbstractFenetre
*/
public class FenetrePartie extends AbstractPanneau {

	private MenuPartie menu;
	private int[][] plateau;
	private ArrayList<ArrayList<PieceGraphique>> pieces;
	private boolean menuAffiche;
	private PieceGraphique pieceSelectionne;
	
	private Image background;
	private JButton pauseBouton;
	
	private Image pause;
	private Image pauseOver;
	private Font laPolice;
	
	//partie 
	private int tailleBloc = 36;
	private int taillePiece = 95;
	
	private Image bRouge, bVert, bBleu, bJaune;
	
	private Color[] couleurs;
	private String[] nomJoueurs;
	
	private int nbTour;
	
	/**
	*Constructeur de la classe FenetrePartie
	*@Param m, le moteur du jeu
	*/
	public FenetrePartie(Controlleur c) {
		super(c);
		
		background = Fenetre.loadImage("partie/background.png");
		
		pause = Fenetre.loadImage("partie/pause.png");
		pauseOver = Fenetre.loadImage("partie/pauseO.png");
		
		bRouge = Fenetre.loadImage("partie/rouge.png");
		bVert = Fenetre.loadImage("partie/vert.png");
		bJaune = Fenetre.loadImage("partie/jaune.png");
		bBleu = Fenetre.loadImage("partie/bleu.png");
		
		plateau = new int[20][20];
		nomJoueurs = new String[4];
		
		try {
			laPolice = Font.createFont(Font.TRUETYPE_FONT,ClassLoader.getSystemResourceAsStream("Electronica.ttf"));
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		laPolice = laPolice.deriveFont(40f);
		
		couleurs = new Color[4];
		couleurs[0] = new Color(0,169,227);
		couleurs[1] = new Color(246, 255, 0);
		couleurs[2] = Color.red;
		couleurs[3] = new Color(30,254,0);
		
		pieces = new ArrayList<ArrayList<PieceGraphique>>();
		pieces.add(new ArrayList<PieceGraphique>());
		pieces.add(new ArrayList<PieceGraphique>());
		pieces.add(new ArrayList<PieceGraphique>());
		pieces.add(new ArrayList<PieceGraphique>());
		
		Joueur jo1 = new Joueur("rene",Bloc.ROUGE);
		ArrayList<Piece> jps = jo1.getPieces();
		
		for(Piece p : jps) {
			pieces.get(0).add(new PieceGraphique(p.getPosition(),jo1.getCouleur()));
		}
		
		Joueur jo2 = new Joueur("jaque",Bloc.VERT);
		jps = jo2.getPieces();
		
		for(Piece p : jps) {
			pieces.get(1).add(new PieceGraphique(p.getPosition(),jo2.getCouleur()));
		}
		
		Joueur jo3 = new Joueur("jaune",Bloc.JAUNE);
		jps = jo3.getPieces();
		
		for(Piece p : jps) {
			pieces.get(2).add(new PieceGraphique(p.getPosition(),jo3.getCouleur()));
		}
		
		Joueur jo4 = new Joueur("michel",Bloc.BLEU);
		jps = jo4.getPieces();
		
		for(Piece p : jps) {
			pieces.get(3).add(new PieceGraphique(p.getPosition(),jo4.getCouleur()));
		}
		
		
		for(int i = 0; i<plateau.length; i++) {
			for(int j = 0; j<plateau.length; j++) {
				plateau[i][j] = (int)(Math.random()*5);
			}
		}
		
		nomJoueurs[0] = "Joueur 0";
		nomJoueurs[1] = "Joueur 1";
		nomJoueurs[2] = "I.A. 2";
		nomJoueurs[3] = "I.A. 3";
		
		setLayout(null);
		creeBoutons();
	}
	
	/**
	 * Met sous forme graphique le plateau du jeu.
	 * @param p , le plateau du jeu
	 */
	public void setPlateau(Plateau p) {
		// TODO - implement FenetrePartie.setPlateau
		throw new UnsupportedOperationException();
	}

	/**
	 * Met sous forme graphique les pieces installees sur le plateau
	 * @param tabs
	 */
	public void setPieces(Piece[] tabs) {
		// TODO - implement FenetrePartie.setPieces
		throw new UnsupportedOperationException();
	}
	
	/**
	  * This think do its job
	  */
	public void paintComponent(Graphics g) {
		g.drawImage(fond,0,0,null);
		
		
		//plateau
		int x = (int)(Fenetre.WIDTH*0.313);
		int y = (int)(Fenetre.HEIGHT*0.168);
		
		for(int i = 0; i<plateau.length; i++) {
			
			for(int j = 0; j<plateau.length; j++) {
				
				if(plateau[i][j] == Bloc.ROUGE) {
					g.drawImage(bRouge,x+i*tailleBloc,y+j*tailleBloc,tailleBloc,tailleBloc,null);
				}
				else if(plateau[i][j] == Bloc.VERT) {
					g.drawImage(bVert,x+i*tailleBloc,y+j*tailleBloc,tailleBloc,tailleBloc,null);
				}
				else if(plateau[i][j] == Bloc.BLEU) {
					g.drawImage(bBleu,x+i*tailleBloc,y+j*tailleBloc,tailleBloc,tailleBloc,null);
				}
				else if(plateau[i][j] == Bloc.JAUNE) {
					g.drawImage(bJaune,x+i*tailleBloc,y+j*tailleBloc,tailleBloc,tailleBloc,null);
				}
				
			}
			
		}
		
		//textes
		g.setFont(laPolice);
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setColor(couleurs[0]);
		int w = (int)(Fenetre.WIDTH*0.096);
		int h = (int)(Fenetre.HEIGHT*0.095);
		int len = g.getFontMetrics().stringWidth(nomJoueurs[0]);
		g.drawString(nomJoueurs[0],(w-len)/2,(h+10)/2);
		
		g.setColor(couleurs[1]);
		x = (int)(Fenetre.WIDTH*0.9);
		y = (int)(Fenetre.HEIGHT*0.9);
		len = g.getFontMetrics().stringWidth(nomJoueurs[1]);
		g.drawString(nomJoueurs[1],(w-len)/2,y+(h+10)/2);
		
		g.setColor(couleurs[2]);
		len = g.getFontMetrics().stringWidth(nomJoueurs[2]);
		g.drawString(nomJoueurs[2],x+(w-len)/2,(h+10)/2);
		
		g.setColor(couleurs[3]);
		len = g.getFontMetrics().stringWidth(nomJoueurs[3]);
		g.drawString(nomJoueurs[3],x+(w-len)/2,y+(h+10)/2);
		
		g.setColor(new Color(40,158,203));
		len = g.getFontMetrics().stringWidth("Tour "+nbTour);
		g.drawString("Tour "+nbTour,(Fenetre.WIDTH-len)/2,40);
		
		
		//pieces
		if(pieces != null) {
			x = (int)(Fenetre.WIDTH*0.697);
			y = (int)(Fenetre.HEIGHT*0.109);
			int y2 = (int)(Fenetre.HEIGHT*0.505);
			
			int ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(pieces.get(0).size() > ind) {
						pieces.get(0).get(ind).draw(g,(j*taillePiece),y+(i*taillePiece),taillePiece);
					}
					ind++;
				}
			}
			
			ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(pieces.get(1).size() > ind) {
						pieces.get(1).get(ind).draw(g,x+(j*taillePiece),y+(i*taillePiece),taillePiece);
					}
					ind++;
				}
			}
			
			ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(pieces.get(2).size() > ind) {
						pieces.get(2).get(ind).draw(g,(j*taillePiece),y2+(i*taillePiece),taillePiece);
					}
					ind++;
				}
			}
			
			ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(pieces.get(3).size() > ind) {
						pieces.get(3).get(ind).draw(g,x+(j*taillePiece),y2+(i*taillePiece),taillePiece);
					}
					ind++;
				}
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
		double y = Fenetre.HEIGHT*0.941;
		
		tailleBloc = (int)(36*scaleH);
		taillePiece = (int)(95*scaleH);
		
		int w = (int)(pause.getWidth(null)*scaleW);
		int h = (int)(pause.getHeight(null)*scaleH);
		double x = (int)(Fenetre.WIDTH-w)/2;
		
		Rectangle rec = new Rectangle((int)x,(int)y,w,h);
		pauseBouton.setBounds(rec);
		Image iconN = pause.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		Image iconO = pauseOver.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		pauseBouton.setRolloverIcon(new ImageIcon(iconO));
		pauseBouton.setIcon(new ImageIcon(iconN));
		
		
	}
	
	private void creeBoutons(){
		
		pauseBouton = new JButton();
		pauseBouton.setOpaque(false);
		pauseBouton.setContentAreaFilled(false);
		pauseBouton.setFocusable(false);
		pauseBouton.setBorderPainted(false);
		pauseBouton.addActionListener(control);
		pauseBouton.setActionCommand(Fenetre.MENU);
		
		this.add(pauseBouton);
	}
	
	/**
	*Lorsque l'on entre dans cet ecran
	*
	*/
	public void entree() {}
	
	/**
	*Lorsque l'on sors de cet ecran
	*
	*/
	public void sortie() {}

	/**
	 * Detecte les clics sur ce JPanel
	 * @param e , le MouseEvent qui en decoule
	 */
	public void mousePressed(MouseEvent e) {}
	

}