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
	
	public static final int TAILLE_TABLEAU = 5;
	
	public Piece(Bloc type) {
		if(type == null) {throw new IllegalArgumentException("Parametre null");}
		blocType = type;
		
		position = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		
		
	}
	
}