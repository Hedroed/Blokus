package ihm;

import blokus.*;

import java.io.*;

/**
  * Class qui charge un fichier de sauvegarde et créé un object Jeu avec
  */
public class Chargement {
	
	File dossier;
	
	/**
	 * Constructeur, charge le fichier
	 * @param chemin un chemin pour acceder au dossier de sauvegarde
	 */
	public Chargement(String chemin) {
		// TODO - implement Chargement.Chargement
		throw new UnsupportedOperationException();
	}
	
	/**
	  * Retourne la liste des fichier de sauvegarde present dans le repertoire
	  * @return un tableau de fichier
	  */
	public File[] getFichiers() {
		// TODO - implement Chargement.getFichiers
		throw new UnsupportedOperationException();
	}

	/**
	 * A partir d'un fichier de sauvegarde, crée un objet Jeu
	 * @param f le fichier texte
	 * @return l'objet Jeu
	 */
	public Jeu chargePartie(File f) {
		// TODO - implement Chargement.chargePartie
		throw new UnsupportedOperationException();
	}

}