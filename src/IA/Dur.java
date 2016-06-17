package IA;

import blokus.*;
import joueur.*;

import java.util.ArrayList;
import java.awt.Point;

/**
  * Niveau de difficulté de l'IA dur (niveau 3 sur 4)
  * @implements Difficulte
  */
public class Dur implements Difficulte {
	
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
		
		entres=trieMeilleurPoint(entres,plateau.TAILLE_PLATEAU/2);
		int i=pieces.size();
		while(i>0) {
			for(Point p : entres) {
			
				
				Piece unePiece = pieces.get(i-1);
				ArrayList<Point> points = unePiece.getPointEntre2();
				
				
				
				
				for(Point pieceEntre : points) {
					int j=0;
					if(!unePiece.getMiroirInutile()){
						j=0;
						while(j<4){
							if(plateau.peutPlacerPiece(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()))) {
								return new IAAction(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()));
							}
							else{
								unePiece.pivoterDroite();
							}
							j++;
						}
						unePiece.miroirHorizontale();
						
						j=0;
						while(j<4){
							if(plateau.peutPlacerPiece(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()))) {
								return new IAAction(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()));
							}
							else{
								unePiece.pivoterDroite();
							}
							j++;
						}
					}
					else{
						if(!unePiece.getRotationInutile()){
								j=0;
							while(j<4){
								if(plateau.peutPlacerPiece(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()))) {
									return new IAAction(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()));
								}
								else{
									unePiece.pivoterDroite();
								}
								j++;
							}
							
						}
						else{
							if(plateau.peutPlacerPiece(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()))) {
								return new IAAction(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()));
							}
						}
					}
					
					
				}
			}
			i--;
		}
		
		return null;
	}
	
	
	/**
	*Trie la liste des points d'entree en fonction de leur distance par rapport au centre du plateau.
	*@param points la liste des points d'entree
	*@param c la couleur du joueur
	*@return l'ArrayList des points ordonnee en fonction de la distance au centre du plateau
	**/
	
	public ArrayList<Point> trieMeilleurPoint(ArrayList<Point> points, int c){
		
		
		ArrayList<Point> ret = new ArrayList<Point>();
		int i=0;
		
		Point centre = new Point(c,c);
		Point bestPoint=points.get(0);
		
		while(i<points.size()){
			bestPoint=points.get(0);
			for(int j=0;j<points.size()-i;j++) {
				Point lePoint=points.get(j);
				
				if(lePoint.distance(centre) < bestPoint.distance(centre)) {
					bestPoint=lePoint;
				}
			}
			ret.add(bestPoint);
			points.set(points.indexOf(bestPoint),points.get(points.size()-1-i));
			i++;
		}
		return ret;
	}
	
}

