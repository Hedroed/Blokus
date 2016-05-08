package joueur;

import IA.*;
import blokus.*;

public class IA extends Joueur {

	private Difficulte difficulte;

	/**
	 * 
	 * @param nom
	 * @param couleur
	 * @param niveau
	 */
	public IA(String nom, Couleur couleur, String niveau) {
		super(nom, couleur);
		
	}

	/**
	 * 
	 * @param p
	 */
	public boolean placePiece(Plateau p) {
		// TODO - implement IA.placePiece
		throw new UnsupportedOperationException();
	}

}