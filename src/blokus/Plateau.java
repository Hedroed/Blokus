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
	
	/**
	  * Constructeur, initialise les attributs
	  */
	public Plateau() {
		
		plateau = new int[TAILLE_PLATEAU][TAILLE_PLATEAU];
		entres = new ArrayList<Point>();
	}
	
	/**
	  * Constructeur, initialise les attributs
	  *@param plat le plateau
	  */
	public Plateau(int[][] plat) {
		plateau = plat;
		entres = new ArrayList<Point>();
	}
	
	/**
	 * Determine pour un joueur tout les coordonée ou il peut placer un block pour ensuite l'affiché 
	 * et ainsi permettre au joueur de trouver plus facilement ou placer ces pieces
	 * crée un tableau de Point dans l'attribut entre
	 * @param c la couleur du joueur
	 */
	public void trouveEnterPossible(int c) {
		entres.clear();
		
		if(Bloc.estCouleur(c)) {
			
			for(int i=0; i<TAILLE_PLATEAU; i++) {
				
				for(int j=0; j<TAILLE_PLATEAU; j++) {
					
					if(plateau[i][j] == Bloc.NONE) {
						
						boolean touche = blocEgale(i-1,j,c);
						touche = touche || blocEgale(i+1,j,c);
						touche = touche || blocEgale(i,j-1,c);
						touche = touche || blocEgale(i,j+1,c);
						boolean connection = blocEgale(i-1,j-1,c);
						connection = connection || blocEgale(i-1,j+1,c);
						connection = connection || blocEgale(i+1,j-1,c);
						connection = connection || blocEgale(i+1,j+1,c);
						
						if(!touche && connection) {
							entres.add(new Point(i,j));
						}
					}

				}

			}
			// return entres;
		}
		// return null;
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
		int pieceCouleur = p.getCouleur();
		
		int i = 0;
		while(!touche && i < Piece.TAILLE_TABLEAU) {
			int j = 0;
			while(!touche && j < Piece.TAILLE_TABLEAU) {
			
				if(pPosition[i][j] != Bloc.NONE) {
					if(x+i >=0 && x+i < TAILLE_PLATEAU && y+j >=0 && y+j <TAILLE_PLATEAU) {
						
						touche = touche || plateau[x+i][y+j] != Bloc.NONE;
						touche = touche || blocEgale(x+i-1,y+j,pieceCouleur);
						touche = touche || blocEgale(x+i+1,y+j,pieceCouleur);
						touche = touche || blocEgale(x+i,y+j-1,pieceCouleur);
						touche = touche || blocEgale(x+i,y+j+1,pieceCouleur);
						connection = connection || blocEgale(x+i-1,y+j-1,pieceCouleur);
						connection = connection || blocEgale(x+i-1,y+j+1,pieceCouleur);
						connection = connection || blocEgale(x+i+1,y+j-1,pieceCouleur);
						connection = connection || blocEgale(x+i+1,y+j+1,pieceCouleur);
						
					}
					else {
						touche = true;
					}
				}
				j++;
			}
			i++;
		}
		
		// System.out.println("touche :"+touche+" connection :"+connection);
		
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
	/**
	*Retourne Vrai ou faux selon le contenu de la case saisie en parametre
	*@param x la coordonnee x du bloc cherche
	*@param y la coordonnee y du bloc cherche
	*@param val le symbole du joueur, la couleur, 1-4
	*@return un booleen vrai si la case saisie contient un bloc de couleur saisie, sinon faux
	*/
	private boolean blocEgale(int x, int y, int val) {
		boolean ret = false;
		
		if(x >=0 && x < TAILLE_PLATEAU && y >=0 && y <TAILLE_PLATEAU) {
			
			ret = plateau[x][y] == val;
			
		}
		if((x == -1 && y == -1 && val == Bloc.JAUNE) || (x == -1 && y == TAILLE_PLATEAU && val == Bloc.ROUGE) 
			|| (x == TAILLE_PLATEAU && y == -1 && val == Bloc.BLEU) || (x == TAILLE_PLATEAU && y == TAILLE_PLATEAU && val == Bloc.VERT)) {
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
	
	//setter getter
	public ArrayList<Point> getEntres() {
		return entres;
	}
	
	public String toString() {
		String ret = "";
		
		int indiceEntre = 0;
		
		for(int i=0; i<TAILLE_PLATEAU; i++) {
			for(int j=0; j<TAILLE_PLATEAU; j++) {
				
				if(!entres.isEmpty()) {
					
					if(indiceEntre < entres.size() && i == entres.get(indiceEntre).getX() && j == entres.get(indiceEntre).getY()) {
						ret = ret+" +";
						indiceEntre++;
					}
					else {
						if(plateau[i][j] == 0) {
							ret = ret+" -";
						}
						else {
							ret = ret+" "+plateau[i][j];
						}
						
					}
					
				}
				else {
					if(plateau[i][j] == 0) {
						ret = ret+" -";
					}
					else {
						ret = ret+" "+plateau[i][j];
					}
				}

			}
			ret = ret + "\n";
		}
		
		return ret;
	}
	
	public int[][] getPlateau() {
		return plateau.clone();
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
		
		System.out.println(momPlat.peutPlacerPiece(p3,-1,15));
		
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
		
		p4.pivoterGauche();
		System.out.println(momPlat.peutPlacerPiece(p4,2,1));
		momPlat.placePiece(p4,2,1);
		
		System.out.println(momPlat);
		
		momPlat.trouveEnterPossible(Bloc.VERT);
		System.out.println(momPlat);
		
		tab = new boolean[3][5];
		tab[0][1] = true;
		tab[1][1] = true;
		tab[2][1] = true;
		tab[2][2] = true;
		tab[2][3] = true;
		Piece p5 = new Piece(Bloc.VERT,tab);
		System.out.println(p5);
		System.out.println("verte : "+momPlat.peutPlacerPiece(p5,16,16));
		
	}
	
}






