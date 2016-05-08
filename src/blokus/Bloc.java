package blokus;

public class Bloc {

	private Couleur couleur;
	private int posX;
	private int posY;

	public int getPosX() {
		return this.posX;
	}

	/**
	 * 
	 * @param x
	 */
	public void setPosX(int x) {
		this.posX = x;
	}

	public int getPosY() {
		return this.posY;
	}

	/**
	 * 
	 * @param y
	 */
	public void setPosY(int y) {
		this.posY = y;
	}

	public Couleur getCouleur() {
		return this.couleur;
	}

	/**
	 * 
	 * @param c
	 */
	public void setCouleur(Couleur c) {
		this.couleur = c;
	}

}