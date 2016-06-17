/**
*Ce package contient la partie Controle du programme
**/
package controlleur;

import ihm.*;
import joueur.*;
import blokus.*;
import IA.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
  * Controlleur
  * Reçoit tout les events generer par l'IHM
  * Permet de lancer une partie
  * @extends MouseAdapter
  * @implements Runnable
  */
public class Moteur extends MouseAdapter implements Runnable{

	private FenetrePartie fenetre;
	private Jeu jeu;
	public static int volume;
	private Rectangle[] zoneJoueurs;
	private Rectangle zonePlateau;
	
	private Controlleur control;
	
	private boolean joueurAJoue;
	private Thread boucleJeu;
	
	//pour thread boucle de jeu
	private boolean aStopper;
	
	/**
	*Constructeur du Moteur
	*@param j le jeu
	*@param fenetre la FenetrePartie contenant les Panneaux
	*@param c le Controlleur
	**/
	public Moteur(Jeu j, FenetrePartie fenetre, Controlleur c) {
		jeu=j;
		this.fenetre=fenetre;
		control = c;
	}
	
	/**
	*Positionne les zones de controle de chaque joueur, la zone ou les pieces du jeu seront disponibles.
	**/
	public void calculePositions(){
		int x = (int)(Fenetre.WIDTH*0.697);
		int y = (int)(Fenetre.HEIGHT*0.109);
		int y2 = (int)(Fenetre.HEIGHT*0.505);
		
		Joueur[] joueurs = jeu.getJoueurs();
		zoneJoueurs= new Rectangle[joueurs.length];
		zonePlateau= new Rectangle((int)(Fenetre.WIDTH*0.313),(int)(Fenetre.HEIGHT*0.168),(int)(Fenetre.WIDTH*0.375),(int)(Fenetre.HEIGHT*0.667));
		
		for(int i=0; i<joueurs.length; i++){
		
			if(joueurs[i].getCouleur()==Bloc.BLEU){
				zoneJoueurs[i] = new Rectangle(0,y2,(int)(Fenetre.WIDTH*0.313),(int)(Fenetre.HEIGHT*0.38));
			}
			else if(joueurs[i].getCouleur()==Bloc.JAUNE){
				zoneJoueurs[i] = new Rectangle(0,y,(int)(Fenetre.WIDTH*0.313),(int)(Fenetre.HEIGHT*0.38));
			}
			else if(joueurs[i].getCouleur()==Bloc.ROUGE){
				zoneJoueurs[i] = new Rectangle(x,y,(int)(Fenetre.WIDTH*0.313),(int)(Fenetre.HEIGHT*0.38));
			}
			else if(joueurs[i].getCouleur()==Bloc.VERT){
				zoneJoueurs[i] = new Rectangle(x,y2,(int)(Fenetre.WIDTH*0.313),(int)(Fenetre.HEIGHT*0.38));
			}
		
		}
	}
	
	/**
	 * Initalise la partie
	 */
	public void lancerPartie() {
		
		aStopper = false;
		boucleJeu = new Thread(this,"Boucle de Jeu");
		System.out.println("lancement boucle de jeu");
		boucleJeu.start();
		
	}
	
	/**
	  * Stoppe la boucle de jeu
	  * @throws BlokusException
	  */
	public void stopPartie() {
		if(boucleJeu != null) {
			boucleJeu.interrupt();
			aStopper = true;
		}
		else {
			throw new BlokusException("Aucune partie en cour");
		}
	}
	
	
	
	/**
	*Lorsqu'un clic de souris est realise
	*@param e le MouseEvent detecte
	**/
	public void mousePressed(MouseEvent e){
		double scaleH = Fenetre.HEIGHT/1080d;
		double scaleW = Fenetre.WIDTH/1920d;
	
		int x = (int)(Fenetre.WIDTH*0.697);
		int y = (int)(Fenetre.HEIGHT*0.109);
		int y2 = (int)(Fenetre.HEIGHT*0.505);
		
		
		int iActif = jeu.getIndiceJoueurActif();
		
		if(jeu.getPieceSelectionnee() == null){
			
			System.out.println("selection");
			
			if(zoneJoueurs[iActif].contains(e.getPoint())){
				int xpiece =(int)((e.getX()-zoneJoueurs[iActif].getX())/((int)(95*scaleW)));
				int ypiece =(int)((e.getY()-zoneJoueurs[iActif].getY())/((int)(95*scaleH)));
				
				if(ypiece*6+xpiece < jeu.getJoueurActif().getPieces().size()) {
					Piece pselect = jeu.getJoueurActif().getPieces().get(ypiece*6+xpiece);
					
					if(pselect!=null){
						
						jeu.selectionnePiece(pselect);
						fenetre.setPieceSelectionne(new PieceGraphique(pselect.getPosition(),pselect.getCouleur()));
						mouseMoved(e);
					}
					else{
						System.out.println("rien a selectionner");
					}

				}
				
			}		
		}
		else{
			Piece p= jeu.getPieceSelectionnee();
			if(e.getButton()== e.BUTTON3){
				System.out.println("miroir");
				control.getEffet().play("rotation");
				p.pivoterDroite();
				fenetre.setPieceSelectionne(new PieceGraphique(p.getPosition(),p.getCouleur()));
				fenetre.repaint();
			}
			else if(e.getButton()== e.BUTTON2){
				System.out.println("rotation");
				control.getEffet().play("rotation");
				p.miroirVerticale();
				fenetre.setPieceSelectionne(new PieceGraphique(p.getPosition(),p.getCouleur()));
				fenetre.repaint();
			}
			else{
				System.out.println("place");
				synchronized(this) {
					if(zonePlateau.contains(e.getPoint())){
						x = zonePlateau.x;
						y = zonePlateau.y;
						
						int xcase =(int)((e.getX()-x)/((int)(36*scaleW)))-2;
						int ycase =(int)((e.getY()-y)/((int)(36*scaleH)))-2;
						
						
						if(jeu.peutPlacer(ycase,xcase)) {
							jeu.place(ycase,xcase);
							
							fenetre.setPieceSelectionne(null);
							
							joueurAJoue = true;
							notify();
						}
					}
					else{
						jeu.selectionnePiece(null);
						fenetre.setPieceSelectionne(null);
						fenetre.setPieces(jeu.getJoueurActif().getPieces(),jeu.getJoueurActif().getCouleur());
						fenetre.repaint();
					}
				}
			}
		}
	}
	
	/**
	*Mouvement de souris
	*@param e le MouseEvent detecte
	*/
	public void mouseMoved(MouseEvent e){
		double scaleH = Fenetre.HEIGHT/1080d;
		double scaleW = Fenetre.WIDTH/1920d;
		
		if(fenetre.getPieceSelectionne()!=null){
			fenetre.setPosPieceSelect((int)(e.getX()-(36*5*scaleW)/2), (int)(e.getY()-(36*5*scaleH)/2));
			fenetre.repaint();
		}
	}
	/**
	*Clic maintenu de souris
	*@param e le MouseEvent detecte
	*/
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	
	/**
	  * Boucle de jeu execute dans un thread 
	  */
	public void run() {
		
		try {
			Thread.sleep(500);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		boolean fin = false;
		
		while(!fin && !aStopper) {
		
			Joueur jA = jeu.getJoueurActif();
			
			//maj fenetre
			fenetre.setTour(jeu.getTour());
			misaAJourPlateauGraphique();
			fenetre.repaint();
			
			//calcule joueur
			if(jA.isIA()) {
				
				IAAction action = ((IA)jA).placePiece(jeu.getPlateau());
				
				if(action != null) {
					jeu.selectionnePiece(action.getPiece());
					jeu.place(action.getX(),action.getY());
					jA.jouerPiece(action.getPieceOriginale());
				}
				else{
					System.out.println("L'ia "+jA.getNom()+" ne peut plus jouer");
					jA.setPeutJouer(false);
				}
				
				try {
					Thread.sleep(100);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println("ici");
			}
			else {
				synchronized(this) {
					while(!joueurAJoue && !aStopper) {
						try {
							wait();
						}
						catch(InterruptedException e) {
							e.printStackTrace();
						}
						
					}
					joueurAJoue=false;
				}
			}	
			
			//maj piece graphique joueur
			fenetre.setPieces(jA.getPieces(),jA.getCouleur());
	
			fin = jeu.nouveauTour();
		}
				
		if(fin) {
			misaAJourPlateauGraphique();
			fenetre.repaint();
			try {
				Thread.sleep(500);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			control.finDePartie();
		}
		
	}
	/**
	*Met a jour l'aspect du plateau graphique
	**/
	private void misaAJourPlateauGraphique() {
		int lon = jeu.getPlateau().getPlateau().length;
		int[][] original = jeu.getPlateau().getPlateau();
		int[][] plateau = new int[lon][lon];
		for(int i=0;i<lon;i++) {
			for(int j=0; j<lon; j++) {
				
				plateau[i][j] = original[i][j];
				
			}
		}
		//recupere les points entres
		ArrayList<Point> pointsEntres = jeu.getPlateau().getEntres();
		for(Point po:pointsEntres) {
			if(plateau[po.x][po.y] == Bloc.NONE) {
				plateau[po.x][po.y] = FenetrePartie.ENTRE;
			}
		}
		fenetre.setPlateau(plateau);
	}
	/**
	*Accesseur du JoueurActif
	*@return le JoueurActif
	**/
	public Joueur getJoueurActif() {
		return jeu.getJoueurActif();
	}
	/**
	*Accesseur du Jeu
	*@return le Jeu
	**/
	public Jeu getJeu() {
		return jeu;
	}
	/**
	*Accesseur de la Fenetre
	*@return la FenetrePartie
	**/
	public FenetrePartie getFenetre() {
		return fenetre;
	}

}