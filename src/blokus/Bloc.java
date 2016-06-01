package blokus;

/**
  * Defini un bloc dans le jeu 
  */
public class Bloc {

	private String couleur;
	private int posX;
	private int posY;
	
	/**
	  * Constructor
	  * @param couleur la couleur du bloc
	  * @param x ca position en x sur le plateau
	  * @param y ca position en y sur le plateau
	  */
	public Bloc(String couleur, int x, int y) {
		
		if(!Couleur.estCouleur(couleur)) {throw new IllegalArgumentException("Cette couleur n'existe pas");}
		
		this.couleur = couleur;
		this.posX = x;
		this.posY = y;
		
	}
	
	public Bloc(String couleur) {
		
		this(couleur,0,0);
		
	}
	
	public boolean equals(Bloc autre) {
		
		return this.couleur.equals(autre.couleur);
		
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

	public String getCouleur() {
		return this.couleur;
	}

	public void setCouleur(String c) {
		this.couleur = c;
	}

}