package blokus;

import java.util.*;

import java.awt.Point;

/**
  * Represente une piece dans la jeu.
  * Permet de connaitre la forme de la piece
  * 
  * (Peu faire tourné la piece, changer l'attribut position)
  */
public class Piece {

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
	
	public Piece(int couleur) {
		this(couleur,new boolean[LARGEUR_DEFAUT][LONGUEUR_DEFAUT]);
	}
	
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
	
	
	public boolean equals(Piece autre) {
		boolean ret = false;
		
		ret = this.couleur == autre.couleur && tabEquals(this.posDefaut,autre.posDefaut);
		
		return ret;
	}
	
	public boolean deepEquals(Piece autre) {
		return false;
	}
	
	/**
	*Comare deux tableaux de booleens
	*@param t1 le premier tableau
	*@param t2 le second tableau
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
	public boolean[][] getPosition() {
		return position;
	}
	
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
	
	public boolean[][] getPosDefaut() {
		return posDefaut;
	}
	
	public int getCouleur() {
		return this.couleur;
	}
	
	public Piece getParent() {
		return parent;
	}
	
	public void setParent(Piece p) {
		this.parent = p;
	}
	
	public Piece getParent2() {
		return parent2;
	}
	
	public void setParent2(Piece p) {
		this.parent2 = p;
	}
	
	public boolean getMiroirInutile() {
		return miroirInutile;
	}
	
	public void setMiroirInutile(boolean b) {
		this.miroirInutile = b;
	}
	
	public boolean getRotationInutile() {
		return rotationInutile;
	}
	
	public void setRotationInutile(boolean b) {
		this.rotationInutile = b;
	}
	
	public int getId() {
		return id;
	}
	
	public int getLongueur() {
		return longueur;
	}
	
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
}