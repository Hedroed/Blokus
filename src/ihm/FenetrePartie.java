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
	
	public static final int ENTRE = 6;

	private int[][] plateau;
	
	private boolean menuAffiche;
	private PieceGraphique pieceSelectionne;
	
	//coord piece selectionnee
	private int xPs;
	private int yPs;
	
	private Image background;
	private JButton pauseBouton;
	
	private Image pause;
	private Image pauseOver;
	private Font laPolice;
	
	//partie 
	private double tailleBloc = 36;
	private double taillePiece = 95;
	
	private Image bRouge, bVert, bBleu, bJaune;
	
	private Color couleurBleu;
	private Color couleurJaune;
	private Color couleurRouge;
	private Color couleurVerte;
	
	//composant de joueurs
	private ArrayList<PieceGraphique> piecesRouge;
	private ArrayList<PieceGraphique> piecesBleu;
	private ArrayList<PieceGraphique> piecesJaune;
	private ArrayList<PieceGraphique> piecesVerte;
	
	private String nomJoueurRouge;
	private String nomJoueurBleu;
	private String nomJoueurJaune;
	private String nomJoueurVert;
	
	private int nbTour;
	private Moteur moteur;
	
	/**
	  * Constructeur
	  * @param c le Controlleur
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
		
		try {
			laPolice = Font.createFont(Font.TRUETYPE_FONT,ClassLoader.getSystemResourceAsStream("Electronica.ttf"));
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		laPolice = laPolice.deriveFont(40f);
		
		couleurBleu = new Color(0,169,227);
		couleurJaune = new Color(246, 255, 0);
		couleurRouge = Color.red;
		couleurVerte = new Color(30,254,0);
		
		piecesRouge = new ArrayList<PieceGraphique>();
		piecesBleu = new ArrayList<PieceGraphique>();
		piecesJaune = new ArrayList<PieceGraphique>();
		piecesVerte = new ArrayList<PieceGraphique>();
		
		setLayout(null);
		creeBoutons();
	}
	
	/**
	 * Met sous forme graphique le plateau du jeu.
	 * @param p , le plateau du jeu
	 */
	public void setPlateau(int[][] plateau) {
		this.plateau=plateau;
		
	}

	/**
	 * Met sous forme graphique les pieces installees sur le plateau
	 * @param tabs liste de Piece
	 * @param couleur couleur des Pieces
	 */
	public void setPieces(ArrayList<Piece> tabs, int couleur) {
		
		if(couleur == Bloc.BLEU) {
			piecesBleu = new ArrayList<PieceGraphique>();
		
			for(Piece p : tabs) {
				piecesBleu.add(new PieceGraphique(p.getPosition(),Bloc.BLEU));
			}
			
		}
		else if(couleur == Bloc.JAUNE) {
			piecesJaune = new ArrayList<PieceGraphique>();
			
			for(Piece p : tabs) {
				piecesJaune.add(new PieceGraphique(p.getPosition(),Bloc.JAUNE));
			}
			
		}
		else if(couleur == Bloc.ROUGE) {
			piecesRouge = new ArrayList<PieceGraphique>();
		
			for(Piece p : tabs) {
				piecesRouge.add(new PieceGraphique(p.getPosition(),Bloc.ROUGE));
			}
			
		}
		else if(couleur == Bloc.VERT) {
			piecesVerte = new ArrayList<PieceGraphique>();
			
			for(Piece p : tabs) {
				piecesVerte.add(new PieceGraphique(p.getPosition(),Bloc.VERT));
			}
			
		}
		
	}
	
	/**
	  * This think do its job
	  */
	public void paintComponent(Graphics g) {
		g.drawImage(fond,0,0,null);
		
		if(moteur == null) {return;}
		
		//plateau
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int x = (int)(Fenetre.WIDTH*0.313);
		int y = (int)(Fenetre.HEIGHT*0.168);
		
		if(moteur.getJoueurActif().getCouleur() == Bloc.BLEU) {
			g.setColor(couleurBleu);
		}
		else if(moteur.getJoueurActif().getCouleur() == Bloc.JAUNE) {
			g.setColor(couleurJaune);
		}
		else if(moteur.getJoueurActif().getCouleur() == Bloc.VERT) {
			g.setColor(couleurVerte);
		}
		else if(moteur.getJoueurActif().getCouleur() == Bloc.ROUGE) {
			g.setColor(couleurRouge);
		}
		
		for(int i = 0; i<plateau.length; i++) {
			
			for(int j = 0; j<plateau.length; j++) {
				
				if(plateau[j][i] == Bloc.ROUGE) {
					g.drawImage(bRouge,(int)(x+i*tailleBloc),(int)(y+j*tailleBloc),(int)(tailleBloc),(int)(tailleBloc),null);
				}
				else if(plateau[j][i] == Bloc.VERT) {
					g.drawImage(bVert,(int)(x+i*tailleBloc),(int)(y+j*tailleBloc),(int)(tailleBloc),(int)(tailleBloc),null);
				}
				else if(plateau[j][i] == Bloc.BLEU) {
					g.drawImage(bBleu,(int)(x+i*tailleBloc),(int)(y+j*tailleBloc),(int)(tailleBloc),(int)(tailleBloc),null);
				}
				else if(plateau[j][i] == Bloc.JAUNE) {
					g.drawImage(bJaune,(int)(x+i*tailleBloc),(int)(y+j*tailleBloc),(int)(tailleBloc),(int)(tailleBloc),null);
				}
				else if(plateau[j][i] == ENTRE) {
					g.fillOval((int)(x+i*tailleBloc+2),(int)(y+j*tailleBloc+2),(int)(tailleBloc-4),(int)(tailleBloc-4));
				}
				
			}
			
		}
		
		//textes
		g.setFont(laPolice);
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		int w = (int)(Fenetre.WIDTH*0.096);
		int h = (int)(Fenetre.HEIGHT*0.095);
		int len = 0;
		x = (int)(Fenetre.WIDTH*0.9);
		y = (int)(Fenetre.HEIGHT*0.9);
		
		if(nomJoueurJaune != null) {
			g.setColor(couleurJaune);
			len = g.getFontMetrics().stringWidth(nomJoueurJaune);
			g.drawString(nomJoueurJaune,(w-len)/2,(h+10)/2);
		}
		
		if(nomJoueurBleu != null) {
			g.setColor(couleurBleu);
			len = g.getFontMetrics().stringWidth(nomJoueurBleu);
			g.drawString(nomJoueurBleu,(w-len)/2,y+(h+10)/2);
		}
		
		if(nomJoueurRouge != null) {
			g.setColor(couleurRouge);
			len = g.getFontMetrics().stringWidth(nomJoueurRouge);
			g.drawString(nomJoueurRouge,x+(w-len)/2,(h+10)/2);
		}
		
		if(nomJoueurVert != null) {
			g.setColor(couleurVerte);
			len = g.getFontMetrics().stringWidth(nomJoueurVert);
			g.drawString(nomJoueurVert,x+(w-len)/2,y+(h+10)/2);
		}
		
		g.setColor(new Color(40,158,203));
		len = g.getFontMetrics().stringWidth("Tour "+nbTour);
		g.drawString("Tour "+nbTour,(Fenetre.WIDTH-len)/2,40);
		
		
		//pieces
		x = (int)(Fenetre.WIDTH*0.697);
		y = (int)(Fenetre.HEIGHT*0.109);
		int y2 = (int)(Fenetre.HEIGHT*0.505);
		
		if(piecesJaune != null && !piecesJaune.isEmpty()) {
			int ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(piecesJaune.size() > ind) {
						piecesJaune.get(ind).draw(g,(int)(j*taillePiece),(int)(y+(i*taillePiece)),(int)(taillePiece));
					}
					ind++;
				}
			}
		}
		
		if(piecesRouge != null && !piecesRouge.isEmpty()) {
			int ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(piecesRouge.size() > ind) {
						piecesRouge.get(ind).draw(g,(int)(x+(j*taillePiece)),(int)(y+(i*taillePiece)),(int)(taillePiece));
					}
					ind++;
				}
			}
		}
		
		if(piecesBleu != null && !piecesBleu.isEmpty()) {
			int ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(piecesBleu.size() > ind) {
						piecesBleu.get(ind).draw(g,(int)(j*taillePiece),(int)(y2+(i*taillePiece)),(int)(taillePiece));
					}
					ind++;
				}
			}
		}
		
		if(piecesVerte != null && !piecesVerte.isEmpty()) {
			int ind = 0;
			for(int i=0; i<4;i++) {
				for(int j=0; j<6;j++) {
					if(piecesVerte.size() > ind) {
						piecesVerte.get(ind).draw(g,(int)(x+(j*taillePiece)),(int)(y2+(i*taillePiece)),(int)(taillePiece));
					}
					ind++;
				}
			}
		}
		
		if(pieceSelectionne!=null){
			pieceSelectionne.draw(g,xPs,yPs,(int)(tailleBloc*5));
		}
		
	}	
	
	/**
	  * Positionne les éléments sur le panneau
	  */
	public void calculePositions() {
		fond = background.getScaledInstance(Fenetre.WIDTH,Fenetre.HEIGHT, Image.SCALE_SMOOTH);
		
		laPolice = laPolice.deriveFont((float)(Fenetre.WIDTH*48/1920));
		
		double scaleH = Fenetre.HEIGHT/1080d;
		double scaleW = Fenetre.WIDTH/1920d;
		double y = Fenetre.HEIGHT*0.941;
		
		tailleBloc = 36*scaleW;
		taillePiece = 95*scaleW;
		
		int w = (int)(pause.getWidth(null)*scaleW);
		int h = (int)(pause.getHeight(null)*scaleH);
		double x = (int)(Fenetre.WIDTH-w)/2;
		
		Rectangle rec = new Rectangle((int)x,(int)y,w,h);
		pauseBouton.setBounds(rec);
		Image iconN = pause.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		Image iconO = pauseOver.getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
		pauseBouton.setRolloverIcon(new ImageIcon(iconO));
		pauseBouton.setIcon(new ImageIcon(iconN));
		if(moteur!=null){
			moteur.calculePositions();
		}
		
	}
	
	private void creeBoutons(){
		
		pauseBouton = new JButton();
		pauseBouton.setOpaque(false);
		pauseBouton.setContentAreaFilled(false);
		pauseBouton.setFocusable(false);
		pauseBouton.setBorderPainted(false);
		pauseBouton.addActionListener(control);
		pauseBouton.setActionCommand("affMenu");
		
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

	public void nouvellePartie(Moteur m, Joueur[] js, Plateau pl){
		
		pieceSelectionne=null;
		if(moteur!=null){
			removeMouseListener(moteur);
			removeMouseMotionListener(moteur);
		}
		moteur=m;
		addMouseListener(moteur);
		addMouseMotionListener(moteur);
		moteur.calculePositions();
		
		int lon = pl.getPlateau().length;
		int[][] original = pl.getPlateau();
		plateau = new int[lon][lon];
		for(int i=0;i<lon;i++) {
			for(int j=0; j<lon; j++) {
				
				plateau[i][j] = original[i][j];
				
			}
		}
		//recupere les points entres
		ArrayList<Point> pointsEntres = pl.getEntres();
		for(Point p:pointsEntres) {
			if(plateau[p.x][p.y] == Bloc.NONE) {
				plateau[p.x][p.y] = ENTRE;
			}
		}
		
		
		for(int i=0; i<js.length;i++){
			
			if(js[i].getCouleur() == Bloc.BLEU) {
				nomJoueurBleu = js[i].getNom();
			}
			else if(js[i].getCouleur() == Bloc.JAUNE) {
				nomJoueurJaune = js[i].getNom();
			}
			else if(js[i].getCouleur() == Bloc.ROUGE) {
				nomJoueurRouge = js[i].getNom();
			}
			else if(js[i].getCouleur() == Bloc.VERT) {
				nomJoueurVert = js[i].getNom();
			}
			
			setPieces(js[i].getPieces(),js[i].getCouleur());
		}
		
	}
	
	/**
	*Change la position de la piece selectionnee en fonction du curseur
	*@param x la nouvelle coordonnee x de la piece
	*@param y la nouvelle coordonnee y de la piece
	*/
	public void setPosPieceSelect(int x, int y){
		xPs=x;
		yPs=y;
	}
	
	public PieceGraphique getPieceSelectionne(){
		return pieceSelectionne;
	}
	
	public void setPieceSelectionne(PieceGraphique p){
		pieceSelectionne=p;
	}
	
	public void setTour(int t) {
		if(t >= 0) {
			nbTour = t;
		}
		else {
			throw new IllegalArgumentException("tour invalide");
		}
	}

}