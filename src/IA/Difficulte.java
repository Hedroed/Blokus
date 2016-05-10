package IA;

import blokus.*;
import joueur.*;

import java.util.ArrayList;

/**
  * Represente la difficulté du joueur IA 
  */
public interface Difficulte {

	/**
	 * Place une piece du joueur associé dans le plateau de jeu
	 * @param j le plateau de jeu 
	 * @param p la liste des pieces du joueur
	 * @param c la couleur du joueur
	 */
	public void placePiece(Jeu j, ArrayList<Piece> p, Couleur c);

}