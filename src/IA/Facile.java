package IA;

import blokus.*;
import joueur.*;

import java.util.ArrayList;
import java.awt.Point;
import java.util.Collections;

/**
  * Niveau de difficulté de l'IA Facile (niveau 1 sur 4)
  * @implements Difficulte
  */
public class Facile implements Difficulte {
	
	/**
	*Trouve une action a réaliser par l'IA si elle peut jouer.
	*@param plateau le plateau sur lequel on place les pièces
	*@param pieces la liste des pieces encore presentes dans le jeu de l'IA
	*@param c la couleur du joueur
	*@return une IAAction contenant le mouvement a faire.
	*/
	
	public IAAction placePiece(Plateau plateau, ArrayList<Piece> pieces, int c) {
		
		plateau.trouveEnterPossible(c);
		ArrayList<Point> entres = plateau.getEntres();
		
		int id = 0;
		ArrayList<Integer> random = new ArrayList<Integer>(pieces.size());
		for(int i=0; i<pieces.size(); i++) {
			random.add(i);
		}
		Collections.shuffle(random);
		
		for(Point p : entres) {
			for(Integer i : random) {
				
				Piece unePiece = pieces.get(i);
				ArrayList<Point> points = unePiece.getPointEntre2();
				
				for(Point pieceEntre : points) {
					
					if(plateau.peutPlacerPiece(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()))) {
						return new IAAction(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()));
					}
				}
			}
		}
		
		return null;
	}

}