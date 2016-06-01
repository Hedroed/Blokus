package blokus;

/**
  * Defini un bloc dans le jeu 
  */
public class Bloc {

	private Couleur couleur;
	private int posX;
	private int posY;
	
	/**
	  * Constructor
	  * @param couleur la couleur du bloc
	  * @param x ca position en x sur le plateau
	  * @param y ca position en y sur le plateau
	  */
	public Bloc(Couleur couleur, int x, int y) {
		
		this.couleur = couleur;
		this.posX = x;
		this.posY = y;
		
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

	public Couleur getCouleur() {
		return this.couleur;
	}

	public void setCouleur(Couleur c) {
		this.couleur = c;
	}

}