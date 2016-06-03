package blokus;

import java.util.*;

/**
  * Represente une piece dans la jeu.
  * Permet de connaitre la forme de la piece
  * 
  * (Peu faire tourné la piece, changer l'attribut position)
  */
public class Piece {

	private int couleur;
	private boolean[][] position;
	private boolean[][] posDefaut;
	
	public static final int TAILLE_TABLEAU = 5;
	public static final int LARGEUR_DEFAUT = 3;
	public static final int LONGUEUR_DEFAUT = 5;
	
	public Piece(int couleur) {
		if(!Bloc.estCouleur(couleur)) {throw new IllegalArgumentException("Couleur incorrect");}
		this.couleur = couleur;
		
		position = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		posDefaut = new boolean[LARGEUR_DEFAUT][LONGUEUR_DEFAUT];
		
	}
	
	public Piece(int couleur, boolean[][] pos) {
		this(couleur);
		
		if(pos == null || pos.length != LARGEUR_DEFAUT || pos[0].length != LONGUEUR_DEFAUT) {throw new IllegalArgumentException("Parametre pos incorrect");}
		
		posDefaut = pos.clone();
		
		//copie le tableau de 5x3 dans le 5x5
		for(int i=0; i<LARGEUR_DEFAUT; i++) {
			for(int j=0; j<LONGUEUR_DEFAUT; j++) {
				
				position[i+1][j] = pos[i][j];
				
			}
		}
	}
	
	public boolean equals(Piece autre) {
		boolean ret = false;
		
		ret = this.couleur == autre.couleur && tabEquals(this.posDefaut,autre.posDefaut);
		
		return ret;
	}
	
	private boolean tabEquals(boolean[][] t1, boolean[][] t2) {
		boolean ret = false;
		
		if(t1 != null && t2 != null) {
			
			//cas ou les reference sont les memes pas besoin de calculer
			if(t1 == t2) {return true;}
			
			ret = t1.length == LARGEUR_DEFAUT && t1[0].length == LONGUEUR_DEFAUT;
			
			ret = ret && t2.length == LARGEUR_DEFAUT && t2[0].length == LONGUEUR_DEFAUT;
			
			if(ret) {
				for(int i=0; i<LARGEUR_DEFAUT; i++) {
					for(int j=0; j<LONGUEUR_DEFAUT; j++) {
						
						ret = ret && t1[i][j] == t2[i][j];
						
					}
				}
			}
		}
		
		return ret;
	}
	
	public void pivoterGauche() {
		
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[j][TAILLE_TABLEAU-1-i];
				
			}
		}
		
		position = newPosition;
	}
	
	public void pivoterDroite() {
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[TAILLE_TABLEAU-1-j][i];
				
			}
		}
		
		position = newPosition;
	}
	
	public void miroirVerticale() {
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[TAILLE_TABLEAU-1-i][j];
				
			}
		}
		position = newPosition;
	}
	
	public void miroirHorizontale() {
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[i][TAILLE_TABLEAU-1-j];
				
			}
		}
		position = newPosition;
	}
	
	//setter getter
	public boolean[][] getPosition() {
		return position;
	}
	
	public int[][] getBlocPosition() {
		int[][] ret = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		for(int i = 0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				if(position[i][j]) {
					ret[i][j] = couleur;
				}
				else {
					ret[i][j] = Bloc.NONE;
				}
			}
		}
		
		return ret;
	}
	
	public boolean[][] getPosDefaut() {
		return posDefaut;
	}
	
	public String toString() {
		String ret = "";
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				if(position[i][j]) {
					ret = ret +" "+couleur+" ";
				}
				else {
					ret = ret + " - ";
				}
				
				
			}
			ret = ret + "\n";
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		
		boolean[][] tab = new boolean[3][5];
		tab[0][1] = true;
		tab[1][1] = true;
		tab[1][2] = true;
		tab[1][3] = true;
		tab[2][2] = true;
		Piece p3 = new Piece(Bloc.ROUGE,tab);
		
		System.out.println(p3);
		
		p3.pivoterGauche();
		
		System.out.println(p3);
		
		p3.pivoterDroite();
		
		System.out.println(p3);
		
		p3.miroirHorizontale();
		
		System.out.println(p3);
		
		p3.miroirVerticale();
		
		System.out.println(p3);
		
		
	}
}