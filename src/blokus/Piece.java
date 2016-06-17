package blokus;

import java.util.*;
import java.io.Serializable;

import java.awt.Point;

/**
  * Represente une piece dans la jeu.
  * Permet de connaitre la forme de la piece
  * 
  * (Peu faire tourner la piece, changer l'attribut position)
  * @implements Serializable
  * @implements Cloneable
  */
public class Piece implements Serializable, Cloneable{

	private int couleur;
	private boolean[][] position;
	private boolean[][] posDefaut;
	private Piece parent;
	private Piece parent2;
	private int longueur;
	
	private int id;
	private static int globalId = 0;
	
	private boolean rotationInutile;
	private boolean miroirInutile;
	
	public static final int TAILLE_TABLEAU = 5;
	public static final int LARGEUR_DEFAUT = 3;
	public static final int LONGUEUR_DEFAUT = 5;
	/**
	*Constructeur de la classe Piece
	*@param couleur la couleur de la piece
	**/
	public Piece(int couleur) {
		this(couleur,new boolean[LARGEUR_DEFAUT][LONGUEUR_DEFAUT]);
	}
	/**
	*Constructeur de la classe Piece
	*@param couleur la couleur de la piece
	*@param pos les coordonnees des blocs composants la piece
	**/
	public Piece(int couleur, boolean[][] pos) {
		
		id = globalId;
		globalId++;
		
		// System.out.println("creation piece "+couleur+" pos : "+pos+" parent :"+p);
		
		if(!Bloc.estCouleur(couleur)) {throw new IllegalArgumentException("Couleur incorrecte");}
		this.couleur = couleur;
		
		if(pos == null || pos.length != LARGEUR_DEFAUT || pos[0].length != LONGUEUR_DEFAUT) {throw new IllegalArgumentException("Parametre pos incorrect");}
		
		posDefaut = pos.clone();
		
		position = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		longueur = 0;
		//copie le tableau de 5x3 dans le 5x5
		for(int i=0; i<LARGEUR_DEFAUT; i++) {
			for(int j=0; j<LONGUEUR_DEFAUT; j++) {
				
				if(pos[i][j]) {
					longueur++;
				}
				
				position[i+1][j] = pos[i][j];
				
			}
		}
	}
	
	/**
	*Determine si une piece est identique a celle ci
	*@param autre la piece a comparer
	*@return true si les pieces sont identiques, false sinon
	**/
	public boolean equals(Piece autre) {
		boolean ret = false;
		
		ret = this.couleur == autre.couleur && tabEquals(this.posDefaut,autre.posDefaut);
		
		return ret;
	}
	/**
	*Explore les profondeurs
	*@param autre la piece a emmener
	*@return false
	**/
	public boolean deepEquals(Piece autre) {
		return false;
	}
	
	/**
	*Comare deux tableaux de booleens
	*@param t1 le premier tableau
	*@param t2 le second tableau
	*@return vrai si les tableaux sont identiques, faux sinon
	*/
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
	
	/**
	  * Met la piece dans sa position de creation
	  */
	public void positionDefaut() {
		position = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		for(int i=0; i<LARGEUR_DEFAUT; i++) {
			for(int j=0; j<LONGUEUR_DEFAUT; j++) {
				
				position[i+1][j] = posDefaut[i][j];
				
			}
		}
	}
	
	/**
	*Tourne la piece vers la Gauche
	*/
	public void pivoterGauche() {
		
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[j][TAILLE_TABLEAU-1-i];
				
			}
		}
		
		position = newPosition;
	}
	
	/**
	*Tourne la piece vers la droite
	*/
	public void pivoterDroite() {
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[TAILLE_TABLEAU-1-j][i];
				
			}
		}
		
		position = newPosition;
	}
	
	/**
	*Inverse le tableau vers le bas
	*/
	public void miroirHorizontale() {
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[TAILLE_TABLEAU-1-i][j];
				
			}
		}
		position = newPosition;
	}
	
	/**
	*Inverse le tableau lateralement
	*/
	public void miroirVerticale() {
		boolean[][] newPosition = new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		for(int i=0; i<TAILLE_TABLEAU; i++) {
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				newPosition[i][j] = position[i][TAILLE_TABLEAU-1-j];
				
			}
		}
		position = newPosition;
	}
	
	/**
	*Retourne les point d'entree dans lesquels une piece peut etre placee
	*@return l'ArrayList des points d'entree
	*/
	public ArrayList<Point> getPointEntre() {
		
		ArrayList<Point> ret = new ArrayList<Point>();
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
				
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				if(!position[i][j]) {
					
					boolean touche = blocAt(i-1,j);
					touche = touche || blocAt(i+1,j);
					touche = touche || blocAt(i,j-1);
					touche = touche || blocAt(i,j+1);
					boolean connection = blocAt(i-1,j-1);
					connection = connection || blocAt(i-1,j+1);
					connection = connection || blocAt(i+1,j-1);
					connection = connection || blocAt(i+1,j+1);
					
					if(!touche && connection) {
						ret.add(new Point(i,j));
					}
				}

			}
			
		}
		return ret;
	}
	/**
	*Accesseur de la liste des points d'entree
	*@return la liste des points d'entree
	**/
	public ArrayList<Point> getPointEntre2() {
		
		ArrayList<Point> ret = new ArrayList<Point>();
		
		for(int i=0; i<TAILLE_TABLEAU; i++) {
				
			for(int j=0; j<TAILLE_TABLEAU; j++) {
				
				if(position[i][j]) {
					ret.add(new Point(i,j));
				}

			}
			
		}
		return ret;
	}
	
	/**
	*Retourne le bloc au coordonnees donnees
	*@param x la coordonnee x
	*@param y la coordonnee y
	*@return vrai si il y a un bloc a la coordonne donnee, faux sinon
	*/
	private boolean blocAt(int x, int y) {
		if(x >= 0 && x < TAILLE_TABLEAU && y >= 0 && y < TAILLE_TABLEAU) {
			return position[x][y];
		}
		else {
			return false;
		}
	}
	
	//setter getter
	/**
	*Accesseur des positions
	*@return les positions
	**/
	public boolean[][] getPosition() {
		return position;
	}
	/**
	*Accesseur du tableau de blocs de la piece
	*@return le tableau de la piece
	**/
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
	/**
	*@return le tableau de coordonnes de la piece par defaut
	**/
	public boolean[][] getPosDefaut() {
		return posDefaut;
	}
	/**
	*@return la couleur
	**/
	public int getCouleur() {
		return this.couleur;
	}
	/**
	*@return les parents de la piece parente
	**/
	public Piece getParent() {
		return parent;
	}
	/**
	*Modifie les parents de la piece
	*@param p la nouvelle piece parente
	**/
	public void setParent(Piece p) {
		this.parent = p;
	}
	/**
	*@return les parents de la piece parente2
	**/
	public Piece getParent2() {
		return parent2;
	}
	/**
	*Modifie les parents de la piece
	*@param p la nouvelle piece parente2
	**/
	public void setParent2(Piece p) {
		this.parent2 = p;
	}
	/**
	*Determine si il est utile de faire le miroir sur une piece
	*@return miroir inutile
	**/
	public boolean getMiroirInutile() {
		return miroirInutile;
	}
	/**
	*Modifie miroirInutile
	*@param b la nouvelle valeur de miroirInutile
	**/
	public void setMiroirInutile(boolean b) {
		this.miroirInutile = b;
	}
	/**
	*Determine si il est utile de faire tourner une piece
	*@return rotationInutile
	**/
	public boolean getRotationInutile() {
		return rotationInutile;
	}
	/**
	*Modifie rotationInutile
	*@param b la nouvelle valeur de rotationInutile
	**/
	public void setRotationInutile(boolean b) {
		this.rotationInutile = b;
	}
	/**
	*@return l'id
	**/
	public int getId() {
		return id;
	}
	/**
	*@return la longueur
	**/
	public int getLongueur() {
		return longueur;
	}
	/**
	*Met les infos de l'objet sous la forme d'un String
	*@return un String contenant les infos de l'objet
	**/
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
			ret = ret+"\n";
		}
		
		return ret+ "  id:"+id;
	}
	/**
	*Lance une sequence de test en mode texte
	*@param args les args
	**/
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
		
		tab = new boolean[3][5];
		tab[0][2] = true;
		tab[1][2] = true;
		tab[2][2] = true;
		tab[1][1] = true;
		tab[1][3] = true;
		Piece p4 = new Piece(Bloc.VERT,tab);
		
		System.out.println(p4);
		
		ArrayList<Point> ps = p4.getPointEntre();
		
		for(Point p : ps) {
			System.out.println(p);
		}
	}
	/**
	*Realise une copie de l'objet
	*@return la copie de l'objet
	**/
	public Piece clone(){
		Piece ret= new Piece(couleur);
		ret.posDefaut=new boolean[LARGEUR_DEFAUT][LONGUEUR_DEFAUT];
		ret.position=new boolean[TAILLE_TABLEAU][TAILLE_TABLEAU];
		
		for(int i=0; i<TAILLE_TABLEAU;i++){
			for(int j=0; j<TAILLE_TABLEAU;j++){
				ret.position[i][j]=position[i][j];
			}
		}
		for(int i=0; i<LARGEUR_DEFAUT;i++){
			for(int j=0; j<LONGUEUR_DEFAUT;j++){
				ret.posDefaut[i][j]=posDefaut[i][j];
			}
		}
		ret.longueur=longueur;
		return ret;
	}
}