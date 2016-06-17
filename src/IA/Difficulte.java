package IA;

import blokus.*;
import joueur.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
  * Represente la difficulté du joueur IA 
  * @extends Serializable
  */
public interface Difficulte extends Serializable{

	/**
	 * Place une piece du joueur associé dans le plateau de jeu
	 * @param plateau le plateau de jeu 
	 * @param pieces la liste des pieces du joueur
	 * @param c la couleur du joueur
	 */
	public IAAction placePiece(Plateau plateau, ArrayList<Piece> pieces, int c);

}