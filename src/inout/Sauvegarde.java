package inout;

import blokus.*;
import joueur.*;
import IA.*;

import java.io.*;
import java.text.*;
import java.util.Date;
/**
*Objet permettant de sauvegarder les parties de Blokus
**/
public class Sauvegarde {
	
	private File dossier;
	
	/**
	 * Constructor
	 * @param chemin le chemin d'acces du fichier
	 */
	public Sauvegarde(String chemin) {
		
		dossier = new File(chemin);
		
		if(!dossier.exists()) {
			dossier.mkdir();
		}
		
		if(!dossier.isDirectory()) {
			throw new IllegalArgumentException("Le chemin n'indique pas un dossier");
		}
		
	}

	/**
	 * Sauve la partie 
	 * @param jeu la partie a sauver
	 */
	public void sauvePartie(Jeu jeu) {
		
		//string de date
		Date aujourdhui = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
		String laDate = formater.format(aujourdhui);
		
		//string de tour
		int tour = jeu.getTour();
		String leTour = "Tour "+tour;
		
		//string de joueur et des joueurs
		Joueur[] joueurs = jeu.getJoueurs();
		int nbJoueurs = 0;
		int nbIA = 0;
		for(int i=0; i<joueurs.length; i++) {
			
			if(joueurs[i].isIA()) {
				nbIA++;
			}
			else {
				nbJoueurs++;
			}
			
		}
		String lesJoueurs = "";
		if(nbJoueurs > 0) {
			if(nbJoueurs == 1) {
				lesJoueurs+= "1 Joueur";
			}
			else {
				lesJoueurs+= nbJoueurs+" Joueurs";
			}
			if(nbIA > 0) {
				lesJoueurs+=", ";
			}
		}
		if(nbIA > 0){
			
			lesJoueurs+= nbIA+" IA";
		}
		
		System.out.println("Joueurs :"+lesJoueurs);
		System.out.println("Date :"+laDate);
		System.out.println("Tour :"+leTour);
		
		
		//plateau de jeu
		Plateau plateau = jeu.getPlateau();
		
		//creation du fichier de sauvegarde
		int nb = 0;
		File f = new File(dossier,"partie"+nb+".sav");
		while(f.exists()) {
			nb++;
			f = new File(dossier,"partie"+nb+".sav");
		}
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			
			out.writeUTF(lesJoueurs);
			out.writeUTF(laDate);
			out.writeUTF(leTour);
			
			out.writeObject(plateau);
			
			out.writeInt(joueurs.length);
			for(int i=0; i<joueurs.length; i++) {
				out.writeObject(joueurs[i]);
			}
			
			out.writeInt(jeu.getIndiceJoueurActif());
			
			out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		Joueur[] lesJoueurs = new Joueur[4];
		
		lesJoueurs[0] = new IA("Patrik",Bloc.ROUGE,DifficulteFactory.FACILE);
		lesJoueurs[1] = new IA("Kevin",Bloc.VERT,DifficulteFactory.FACILE);
		lesJoueurs[2] = new IA("Michel",Bloc.JAUNE,DifficulteFactory.FACILE);
		lesJoueurs[3] = new IA("René",Bloc.BLEU,DifficulteFactory.FACILE);
		
		Jeu jeu = new Jeu(lesJoueurs);
		
		Sauvegarde save = new Sauvegarde("sauvegarde");
		
		save.sauvePartie(jeu);
		
	}

}