package blokus;

import java.util.*;
import joueur.*;

import java.awt.Point;

/**
  * Le Jeu represente toute une partie de Blokus 
  * il contient les joueurs/IA le plateau de jeu, la piece selectionné et d'autre indicateur comme le temps de jeu et le nombre de tours
  */
public class Jeu {

	private Joueur[] joueurs;
	private Plateau plateau;
	private Piece pieceSelectionne;
	//en miliseconde
	private int temps;
	private int tour;
	private int indiceJoueurActif;
	
	private int joueurEnMatch;
	
	public Jeu(Joueur[] js) {
		if(js == null) {throw new IllegalArgumentException("joueurs null");}
		joueurs = js;
		plateau = new Plateau();
		
		joueurEnMatch = 4; 
		
		tour = 0;
		indiceJoueurActif = 0;
		temps = 0;
	}
	
	public Jeu(Joueur[] js, int[][] plat, int temps) {
		if(js == null) {throw new IllegalArgumentException("joueurs null");}
		joueurs = js;
		
		if(plat == null) {throw new IllegalArgumentException("plateau null");}
		plateau = new Plateau(plat);
		
		joueurEnMatch = 0;
		for(int i=0; i<joueurs.length; i++) {
			if(joueurs[i].peutJouer()) {
				joueurEnMatch++;
			}
		}
		
		tour = 0;
		indiceJoueurActif = 0;
		this.temps = temps;
	}
	
	/**
	  * Passe au tour suivant, increment l'attribut tour et passe au joueur suivant
	  */
	public void nouveauTour() {
		//check si le joueur peut continuer a joueur, sinon le retire de la partie
		
		for(int i=0; i<joueurs.length; i++) {
			
			if(joueurs[i].peutJouer() && !peutContinuerAJouer(joueurs[i])) {
				if(joueurEnMatch == 1) {
					System.out.println("Fin de partie !!! "+joueurs[i]+" gagne avec une score de "+joueurs[i].getScore());
				}
				else {
					joueurs[i].setPeutJouer(false);
					System.out.println("Joueur "+joueurs[i]+" a perdu avec une score de "+joueurs[i].getScore());
					joueurEnMatch--;
				}
			}
			
		}
		
		//passe au joueur suivant qui peut jouer
		indiceJoueurActif++;
		if(indiceJoueurActif >= 4) {
			indiceJoueurActif = 0;
			tour++;
		}
		while(!getJoueurActif().peutJouer()) {
			indiceJoueurActif++;
			if(indiceJoueurActif >= 4) {
				indiceJoueurActif = 0;
				tour++;
			}
		}
		
	}

	/**
	 * Selectionne une piece du joueur dans l'attibut pieceSelectionne
	 * @param p la piece
	 */
	public void selectionnePiece(Piece p) {
		if(p.getCouleur() == getJoueurActif().getCouleur()) {
			pieceSelectionne = p;
		}
		else {
			throw new BlokusException("Piece de mauvaise couleur");
		}
	}
	
	/**
	  * Vérifie que le joueur courant a encore des pieces ou que sa plus petite pieces peut etre placer, sinon il ne peut plus jouer
	  * @return true si le joueur peu continuer
	  */
	private boolean peutContinuerAJouer(Joueur j) {
		if(j.getPieces().isEmpty()) {return false;}
		
		ArrayList<Piece> jPiece = j.getPieces();
		
		if(jPiece.isEmpty()) {throw new BlokusException("Le joueur n'a plus de piece");}
		
		ArrayList<Piece> parents = new ArrayList<Piece>();
		
		for(Piece p : jPiece) {
			parentPieceRect(p,parents,jPiece);
		}
		
		//check les pieces parents
		plateau.trouveEnterPossible(j.getCouleur());
		ArrayList<Point> entres = plateau.getEntres();
		
		boolean ret = true;
		
		for(Point uneEntree : entres) {
			
			for(Piece unePiece : parents) {
				
				ArrayList<Point> points = unePiece.getPointEntre2();
				boolean peutPlacer = false;
				
				for(Point pieceEntre : points) {
					
					if(plateau.peutPlacerPiece(unePiece,(int)(uneEntree.getX()-pieceEntre.getX()),(int)(uneEntree.getY()-pieceEntre.getY()))) {
						peutPlacer = true;
					}
					
				}
				
				if(!peutPlacer) {
					ret = false;
				}
			}
		}

		return ret;
	}
	
	private void parentPieceRect(Piece p, ArrayList<Piece> parents, ArrayList<Piece> pieces) {
		
		Piece pa1 = p.getParent();
		Piece pa2 = p.getParent2();
		
		boolean existe1 = true;
		boolean existe2 = true;
		
		if(pieces.indexOf(p) != -1) {
			if(pa1 != null) {
				if(pieces.indexOf(pa1) != -1) { //si pa1 est dans pieces
					parentPieceRect(pa1,parents,pieces);
				}
				else {
					existe1 = false;
				}
			}
			if(pa2 != null) {
				if(pieces.indexOf(pa2) != -1) {
					parentPieceRect(pa2,parents,pieces);
				}
				else {
					existe2 = false;
				}
			}
			
			
			if((existe1 && !existe2) || (!existe1 && existe2)) {
				if(parents.indexOf(p) == -1) {
					parents.add(p);
				}
			}
			if(pa1 == null && pa2 == null) {
				if(parents.indexOf(p) == -1) {
					parents.add(p);
				}
			}
			
		}
	}
	
	
	/**
	 * Place la piece courant, s'il y en a une, sur le plateau 
	 * @param x coordonné x
	 * @param y coordonné y
	 */
	public void place(int x, int y) {
		if(pieceSelectionne != null) {
			if(plateau.peutPlacerPiece(pieceSelectionne,x,y)) {
				plateau.placePiece(pieceSelectionne,x,y);
			}
			else {
				throw new BlokusException("Ne peut pas placer la piece");
			}
		}
		else {
			throw new BlokusException("Aucune piece selectionner");
		}
	}
	
	public Plateau getPlateau() {
		return this.plateau;
	}

	public Joueur getJoueurActif() {
		return joueurs[indiceJoueurActif];
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}
	
	public int getTemps() {
		return temps;
	}
	
	public void setTemps(int t) {
		this.temps = t;
	}
	
	public int getTour() {
		return tour;
	}
	
	public static void main(String[] args) {
		
		Joueur[] lesJoueurs = new Joueur[4];
		
		lesJoueurs[0] = new Joueur("Patrik",Bloc.BLEU);
		lesJoueurs[1] = new Joueur("Kevin",Bloc.JAUNE);
		lesJoueurs[2] = new Joueur("Michel",Bloc.ROUGE);
		lesJoueurs[3] = new Joueur("René",Bloc.VERT);
		
		Jeu jeu = new Jeu(lesJoueurs);
		
		jeu.peutContinuerAJouer(lesJoueurs[0]);
		
		// ArrayList<Piece> jPiece = lesJoueurs[0].getPieces();
		// ArrayList<Piece> parents = new ArrayList<Piece>();
		
		// jeu.parentPieceRect(jPiece.get(1),parents,jPiece);
		// System.out.println("taille : "+parents.size());
		
		// System.out.println(jPiece.indexOf(null));
		
	}

}




