package blokus;

import java.util.*;
import joueur.*;

import java.awt.Point;

/**
  * Le Plateau contient un tableau de tout les blocks placés sur le plateau de jeu.
  * Il permet de placer une piece sur le plateau ou de savoir si on peu en placer.
  */
public class Plateau {
	
	private final int TAILLE_PLATEAU = 20;
	
	private int[][] plateau;
	private ArrayList<Point> entres;
	
	public Plateau() {
		
		plateau = new int[TAILLE_PLATEAU][TAILLE_PLATEAU];
		entres = new ArrayList<Point>();
	}
	
	/**
	 * Determine pour un joueur tout les coordonée ou il peut placer un block pour ensuite l'affiché 
	 * et ainsi permettre au joueur de trouver plus facilement ou placer ces pieces
	 * crée un tableau de Point dans l'attribut entre
	 * @param j le joueur
	 */
	public void trouveEnterPossible(int c) {
		entres.clear();
		
		if(Bloc.estCouleur(c)) {
			
			for(int i=0; i<TAILLE_PLATEAU; i++) {//fait nimorte qui fkjsnjkfszjhfbshj
				
				for(int j=0; j<TAILLE_PLATEAU; j++) {
					
					if(plateau[i][j] == Bloc.NONE) {
						
						if(blocEgale(i-1,j-1,c)) {
							
							entres.add(new Point(i,j));
							
						} 
						else if(blocEgale(i+1,j-1,c)) {
							
							entres.add(new Point(i,j));
							
						}
						else if(blocEgale(i-1,j+1,c)) {
						
							entres.add(new Point(i,j));
						
						}
						else if(blocEgale(i+1,j+1,c)) {
							entres.add(new Point(i,j));
						}
					}

				}

			}
			
		}
		
		System.out.println("taille entres "+entres.size());
	}

	/**
	 * Place une piece dans le plateau a une certaine coordonée en fonction de la rotation de la piece
	 * il vérifie avant s'il peut placer cette piece
	 * @param p la piece
	 * @param x coordonée x
	 * @param y coordonée y
	 */
	public void placePiece(Piece p, int x, int y) {
		int[][] pPosition = p.getBlocPosition();
		
		for(int i = 0; i < Piece.TAILLE_TABLEAU; i++) {
			for(int j = 0; j < Piece.TAILLE_TABLEAU; j++) {
				if(pPosition[i][j] != Bloc.NONE) {
					if(x+i >=0 && x+i < TAILLE_PLATEAU && y+j >=0 && y+j <TAILLE_PLATEAU) {
						plateau[x+i][y+j] = pPosition[i][j];
					}
				}
			}
		}
		
	}

	/**
	 * Vérifie si une piece peu etre placer a des coordonée
	 * @param p la piece
	 * @param x coordonée x
	 * @param y coordonée y
	 * @return true si la piece peu etre placée
	 */
	public boolean peutPlacerPiece(Piece p, int x, int y) {
		boolean touche = false;
		boolean connection = false;
		
		int[][] pPosition = p.getBlocPosition();
		
		int i = 0;
		while(!touche && i < Piece.TAILLE_TABLEAU) {
			int j = 0;
			while(!touche && j < Piece.TAILLE_TABLEAU) {
			
				if(pPosition[i][j] != Bloc.NONE) {
					if(x+i >=0 && x+i < TAILLE_PLATEAU && y+j >=0 && y+j <TAILLE_PLATEAU) {
						
						touche = touche || plateau[x+i][y+j] != Bloc.NONE;
						touche = touche || blocEgale(i,j,x+i-1,y+j,pPosition);
						touche = touche || blocEgale(i,j,x+i+1,y+j,pPosition);
						touche = touche || blocEgale(i,j,x+i,y+j-1,pPosition);
						touche = touche || blocEgale(i,j,x+i,y+j+1,pPosition);
						connection = connection || blocEgale(i,j,x+i-1,y+j-1,pPosition);
						connection = connection || blocEgale(i,j,x+i-1,y+j+1,pPosition);
						connection = connection || blocEgale(i,j,x+i+1,y+j-1,pPosition);
						connection = connection || blocEgale(i,j,x+i+1,y+j+1,pPosition);
						
					}
				}
				j++;
			}
			i++;
		}
		
		return !touche && connection;
	}

	private boolean blocEgale(int x1, int y1, int x2, int y2,int[][] tab2) {
		boolean ret = false;
		
		if(x2 >=0 && x2 < TAILLE_PLATEAU && y2 >=0 && y2 <TAILLE_PLATEAU) {
			
			ret =  tab2[x1][y1] == plateau[x2][y2];
			
		}
		if((x2 == -1 && y2 == -1) || (x2 == -1 && y2 == TAILLE_PLATEAU) || (x2 == TAILLE_PLATEAU && y2 == -1) || (x2 == TAILLE_PLATEAU && y2 == TAILLE_PLATEAU)) {
			System.out.println("-1 -1");
			ret=true;
		}
		
		// System.out.println("check entre "+x1+" "+y1+" et "+x2+" "+y2+" trouve "+ret);
		
		return ret;
	}
	
	private boolean blocEgale(int x1, int y1, int x2, int y2) {
		boolean ret = false;
		
		if(x2 >=0 && x2 < TAILLE_PLATEAU && y2 >=0 && y2 <TAILLE_PLATEAU) {
			
			ret =  plateau[x1][y1] == plateau[x2][y2];
			
		}
		if((x2 == -1 && y2 == -1) || (x2 == -1 && y2 == TAILLE_PLATEAU) || (x2 == TAILLE_PLATEAU && y2 == -1) || (x2 == TAILLE_PLATEAU && y2 == TAILLE_PLATEAU)) {
			ret=true;
		}
		
		// System.out.println("check entre "+x1+" "+y1+" et "+x2+" "+y2+" trouve "+ret);
		
		return ret;
	}
	
	private boolean blocEgale(int x, int y, int val) {
		boolean ret = false;
		
		if(x >=0 && x < TAILLE_PLATEAU && y >=0 && y <TAILLE_PLATEAU) {
			
			ret =  plateau[x][y] == val;
			
		}
		if((x == -1 && y == -1) || (x == -1 && y == TAILLE_PLATEAU) || (x == TAILLE_PLATEAU && y == -1) || (x == TAILLE_PLATEAU && y == TAILLE_PLATEAU)) {
			ret=true;
		}
		
		// System.out.println("check entre "+x1+" "+y1+" et "+x2+" "+y2+" trouve "+ret);
		
		return ret;
	}
	
	/**
	 * Vérifier si une piece peu etre placer n'importe ou sur le damier
	 * @param p la piece
	 * @return true si la piece peu etre placer quelque pars
	 */
	public void peutJouerPiece(Piece p) {
		// TODO - implement Plateau.peutJouerPiece
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		String ret = "";
		
		int indiceEntre = 0;
		
		if(!entres.isEmpty())
		System.out.println("next point "+entres.get(indiceEntre));
		
		for(int i=0; i<TAILLE_PLATEAU; i++) {
			for(int j=0; j<TAILLE_PLATEAU; j++) {
				
				if(!entres.isEmpty()) {
					
					if(i < entres.size() && i == entres.get(indiceEntre).getX() && j == entres.get(indiceEntre).getY()) {
						ret = ret+" O";
						indiceEntre++;
					}
					else {
						ret = ret+" "+plateau[i][j];
					}
					
				}
				else {
					ret = ret+" "+plateau[i][j];
				}

			}
			ret = ret + "\n";
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		
		Plateau momPlat = new Plateau();
		
		boolean[][] tab = new boolean[3][5];
		tab[0][1] = true;
		tab[0][2] = true;
		tab[0][3] = true;
		tab[0][4] = true;
		tab[0][0] = true;
		Piece p3 = new Piece(Bloc.ROUGE,tab);
		
		System.out.println(momPlat);
		System.out.println(p3);
		
		System.out.println(momPlat.peutPlacerPiece(p3,-1,0));
		
		momPlat.placePiece(p3,-1,0);
		
		System.out.println(momPlat);
		
		tab = new boolean[3][5];
		tab[0][1] = true;
		tab[1][1] = true;
		tab[1][2] = true;
		tab[1][3] = true;
		tab[0][3] = true;
		Piece p4 = new Piece(Bloc.ROUGE,tab);
		p4.pivoterGauche();
		System.out.println(p4);
		
		System.out.println(momPlat.peutPlacerPiece(p4,0,4));
		System.out.println(momPlat.peutPlacerPiece(p4,0,2));
		System.out.println(momPlat.peutPlacerPiece(p4,5,5));
		
		momPlat.placePiece(p4,0,4);
		
		System.out.println(momPlat);
		
		System.out.println(momPlat.peutPlacerPiece(p4,3,2));
		momPlat.placePiece(p4,3,2);
		
		System.out.println(momPlat);
		
		momPlat.trouveEnterPossible(Bloc.ROUGE);
		System.out.println(momPlat);
	}
	
}






