package controlleur;

import ihm.*;
import joueur.*;
import blokus.*;

/**
  * Controlleur
  * Reçoit tout les events generer par l'IHM
  * Permet de lancer une partie
  */
public class Moteur {

	private Fenetre fenetre;
	private Jeu jeu;
	public static int volume;

	/**
	 * Initalise la partie avec l'objet Jeu
	 * @param j le jeu a lancer
	 */
	public void lancerPartie(Jeu j) {
		// TODO - implement Moteur.lancerPartie
		throw new UnsupportedOperationException();
	}

	/**
	 * Selectionne une piece
	 * @param piece la piece
	 */
	public void selectionnePiece(Piece piece) {
		// TODO - implement Moteur.selectionnePiece
		throw new UnsupportedOperationException();
	}

	/**
	 * Créer une nouvelle partie en creant un nouvel objet Jeu
	 * @param joueurs le tableau de tout les joueurs (doit etre de 4 joueurs)
	 * @param taille la taille du plateau (max 23)
	 * @return un nouveau Jeu vierge
	 */
	private Jeu creePartie(Joueur[] joueurs, int taille) {
		// TODO - implement Moteur.creePartie
		throw new UnsupportedOperationException();
	}

}