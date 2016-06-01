package blokus;

import java.util.*;

/**
  * Represente une piece dans la jeu.
  * Permet de connaitre la forme de la piece
  * 
  * (Peu faire tourné la piece, changer l'attribut position)
  */
public class Piece {

	private Bloc blocType;
	private boolean[][] position;
	private boolean[][] posDefaut;
	
	public static final int TAILLE_TABLEAU = 5;
	
	public Piece(Bloc type) {
		if(type == null) {throw new IllegalArgumentException("Parametre null");}
		blocType = type;
		
		position = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		posDefaut = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
	}
	
	public Piece(Bloc type, boolean[][] pos) {
		this(type);
		
		position = pos.clone();
		posDefaut = pos.clone();
		
	}
	
	public boolean equals(Piece autre) {
		boolean ret = false;
		
		ret = this.blocType.equals(autre.blocType) && tabEquals(this.posDefaut,autre.posDefaut);
		
		return ret;
	}
	
	private boolean tabEquals(boolean[][] t1, boolean[][] t2) {
		boolean ret = false;
		
		if(t1 != null && t2 != null) {
			
			//cas ou les reference sont les memes pas besoin de calculer
			if(t1 == t2) {return true;}
			
			ret = t1.length == TAILLE_TABLEAU && t1[0].length == TAILLE_TABLEAU;
			
			ret = ret && t2.length == TAILLE_TABLEAU && t2[0].length == TAILLE_TABLEAU;
			
			if(ret) {
				for(int i=0; i<TAILLE_TABLEAU; i++) {
					for(int j=0; j<TAILLE_TABLEAU; j++) {
						
						ret = ret && t1[i][j] == t2[i][j];
						
					}
				}
			}
		}
		
		return ret;
	}
	
	public void poviterDroite() {
		
	}
	
	public void poviterGauche() {
		
	}
	
	public void mirroirVerticale() {
		
	}
	
	public void mirroirHorizontale() {
		
	}
	
	//setter getter
	public boolean[][] getPosition() {
		return position;
	}
	
	public boolean[][] getPosDefaut() {
		return posDefaut;
	}
	
	public String toString() {
		String ret = "";
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				if(position[i][j]) {
					ret = ret + " 0 ";
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
		
		boolean[][] tab = new boolean[5][5];
		tab[0][0] = true;
		tab[1][0] = true;
		tab[2][0] = true;
		Piece p3 = new Piece(new Bloc(new Couleur("rouge")),tab);
		
		
		System.out.println(p3);
		
		
		
		
	}
}