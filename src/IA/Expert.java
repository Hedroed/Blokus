package IA;

import blokus.*;
import joueur.*;

import java.util.ArrayList;
import java.awt.Point;

/**
  * Niveau de difficulté de l'IA expert (niveau 4 sur 4)
  * @implements Difficulte
  */
public class Expert implements Difficulte {
	
	/**
	*Trouve une action a réaliser par l'IA si elle peut jouer.
	*@param plateau le plateau sur lequel on place les pièces
	*@param pieces la liste des pieces encore presentes dans le jeu de l'IA
	*@param c la couleur du joueur
	*@return une IAAction contenant le mouvement a faire.
	*/
	
	public IAAction placePiece(Plateau plateau, ArrayList<Piece> pieces, int c){
		// long start = System.nanoTime();
		
		ArrayList<IAAction> coups= calculeCoups(plateau,pieces,c);
		if(coups.isEmpty()){
			return null;
		}
		IAAction bestCoup=coups.get(0);
		
		int[][] grille=clonePlateau(plateau.getPlateau());
		int bestEntree=compteEntrees(bestCoup.getPiece(),bestCoup.getX(),bestCoup.getY(),grille);;
		for(IAAction coup : coups){
			grille=clonePlateau(plateau.getPlateau());
			
			
			
			int entreePiece=compteEntrees(coup.getPiece(),coup.getX(),coup.getY(),grille);
			if(entreePiece>bestEntree){
				bestEntree=entreePiece;
				bestCoup=coup;
			}
		}
		
		// System.out.println("nb coup possible "+coups.size());
		// long time = (System.nanoTime()-start)/1000000;
		// System.out.println("execution :"+time);
		return bestCoup;
	}
	
	/**
	*Trouve toutes les actions realisables par l'IA si elle peut jouer.
	*@param plateau le plateau sur lequel on place les pièces
	*@param pieces la liste des pieces encore presentes dans le jeu de l'IA
	*@param c la couleur du joueur
	*@return une ArrayList d'IAAction realisables
	*/
	public ArrayList<IAAction> calculeCoups(Plateau plateau, ArrayList<Piece> pieces, int c) {
		
		ArrayList<IAAction> ret = new ArrayList<IAAction>();
		
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
								ret.add(new IAAction(unePiece.clone(),(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()),unePiece));
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
								ret.add(new IAAction(unePiece.clone(),(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()),unePiece));
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
									ret.add(new IAAction(unePiece.clone(),(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()),unePiece));
								}
								else{
									unePiece.pivoterDroite();
								}
								j++;
							}
							
						}
						else{
							if(plateau.peutPlacerPiece(unePiece,(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()))) {
								ret.add(new IAAction(unePiece.clone(),(int)(p.getX()-pieceEntre.getX()),(int)(p.getY()-pieceEntre.getY()),unePiece));
							}
						}
					}
					
					
				}
			}
			i--;
		}
		
		return ret;
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
	
	
	/**
	 * Determine pour un joueur tout les coordonée ou il peut placer un block pour ensuite l'affiché 
	 * et ainsi permettre au joueur de trouver plus facilement ou placer ces pieces
	 * crée un tableau de Point dans l'attribut entre
	 * @param c la couleur du joueur
	 * @param plateau, le plateau du jeu
	 * @return l'ArrayList contenant tous les points d'entree possible.
	 */
	public ArrayList<Point> trouveEnterPossible(int c, int[][] plateau) {
		ArrayList<Point> entres=new ArrayList<Point>();
		
		if(Bloc.estCouleur(c)) {
			
			for(int i=0; i<Plateau.TAILLE_PLATEAU; i++) {
				
				for(int j=0; j<Plateau.TAILLE_PLATEAU; j++) {
					
					if(plateau[i][j] == Bloc.NONE) {
						
						boolean touche = blocEgale(i-1,j,c,plateau);
						touche = touche || blocEgale(i+1,j,c,plateau);
						touche = touche || blocEgale(i,j-1,c,plateau);
						touche = touche || blocEgale(i,j+1,c,plateau);
						boolean connection = blocEgale(i-1,j-1,c,plateau);
						connection = connection || blocEgale(i-1,j+1,c,plateau);
						connection = connection || blocEgale(i+1,j-1,c,plateau);
						connection = connection || blocEgale(i+1,j+1,c,plateau);
						
						if(!touche && connection) {
							entres.add(new Point(i,j));
						}
					}

				}

			}
		}
		return entres;
	}
	
	/**
	*Determine si une case du plateau contient un bloc de valeur passee en parametre
	*@param x la coordonnee x du bloc
	*@param y la coordonnee x du bloc
	*@param val la valeur que l'on souhaite tester
	*@param plateau le plateau du jeu
	*@return vrai si la valeur passee en parametre est contenue dans le bloc de coordonnees x y, faux sinon
	**/
	private boolean blocEgale(int x, int y, int val, int[][] plateau) {
		boolean ret = false;
		
		if(x >=0 && x < Plateau.TAILLE_PLATEAU && y >=0 && y <Plateau.TAILLE_PLATEAU) {
			
			ret = plateau[x][y] == val;
			
		}
		if((x == -1 && y == -1 && val == Bloc.JAUNE) || (x == -1 && y == Plateau.TAILLE_PLATEAU && val == Bloc.ROUGE) 
			|| (x == Plateau.TAILLE_PLATEAU && y == -1 && val == Bloc.BLEU) || (x == Plateau.TAILLE_PLATEAU && y == Plateau.TAILLE_PLATEAU && val == Bloc.VERT)) {
			ret=true;
		}
		
		// System.out.println("check entre "+x1+" "+y1+" et "+x2+" "+y2+" trouve "+ret);
		
		return ret;
	}
	/**
	*Compte le nombre de point d'entree disponibles apres avoir place la piece passee en parametre au coordonnees
	* passees en parametre
	*@param piece la piece que l'on souhaite tester
	*@param x la coordonnee x
	*@param y la coordonnee y
	*@param plateau le plateau du jeu
	*@return un entier etant le nombre de points d'entree.
	**/
	private int compteEntrees(Piece piece, int x, int y, int[][] plateau){
		
		placePiece(piece,x,y,plateau);
		int ret= trouveEnterPossible(piece.getCouleur(),plateau).size();
		return ret;
	}
	
	/**
	 * Place une piece dans le plateau a une certaine coordonée en fonction de la rotation de la piece
	 * il vérifie avant s'il peut placer cette piece
	 * @param p la piece
	 * @param x coordonée x
	 * @param y coordonée y
	 * @param plateau le plateau du jeu
	 */
	public void placePiece(Piece p, int x, int y,int[][] plateau) {
		int[][] pPosition = p.getBlocPosition();
		
		for(int i = 0; i < Piece.TAILLE_TABLEAU; i++) {
			for(int j = 0; j < Piece.TAILLE_TABLEAU; j++) {
				if(pPosition[i][j] != Bloc.NONE) {
					if(x+i >=0 && x+i < Plateau.TAILLE_PLATEAU && y+j >=0 && y+j <Plateau.TAILLE_PLATEAU) {
						plateau[x+i][y+j] = pPosition[i][j];
					}
				}
			}
		}
		
	}
	
	/**
	*Fait une copie du tableau d'entiers passe en parametre
	*@param original le tableau a copier
	*@return un tableau d'entier clone du parametre
	**/
	
	public int[][] clonePlateau(int[][] original){
		int lon = original.length;
		int[][] grille = new int[lon][lon];
		for(int i=0;i<lon;i++) {
			for(int j=0; j<lon; j++) {
				
				grille[i][j] = original[i][j];
				
			}
		}
		return grille;
	}

}