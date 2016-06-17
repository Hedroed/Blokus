package blokus;

/**
  * Definit un bloc dans le jeu 
  */
public class Bloc {

	private int couleur;
	private int posX;
	private int posY;
	
	public static final int NONE = 0;
	public static final int BLEU = 1;
	public static final int ROUGE = 2;
	public static final int VERT = 3;
	public static final int JAUNE = 4;
	
	/**
	  * Constructor
	  * @param couleur la couleur du bloc
	  * @param x ca position en x sur le plateau
	  * @param y ca position en y sur le plateau
	  */
	public Bloc(int couleur, int x, int y) {
		
		if(!estCouleur(couleur)) {throw new IllegalArgumentException("Cette couleur n'existe pas");}
		
		this.couleur = couleur;
		this.posX = x;
		this.posY = y;
		
	}
	/**
	*Constructor
	*@param couleur la couleur du bloc
	**/
	public Bloc(int couleur) {
		
		this(couleur,0,0);
		
	}
	/**
	*Determine si deux blocs sont egaux
	*@param autre le bloc a comparer
	*@return vrai si les blocs sont egaux, faux sinon
	**/
	public boolean equals(Bloc autre) {
		
		return this.couleur == autre.couleur;
		
	}
	
	/**
	*Determine si le bloc appartient a un joueur
	*@param c la couleur du bloc
	*@return vrai si oui, faux sinon
	**/
	public static boolean estCouleur(int c) {
		return c >= 0 && c <= 4;
	}
	
	// accesseur / modifieur
	public int getPosX() {
		return this.posX;
	}

	public void setPosX(int x) {
		this.posX = x;
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosY(int y) {
		this.posY = y;
	}

	public int getCouleur() {
		return this.couleur;
	}

	public void setCouleur(int c) {
		this.couleur = c;
	}

}