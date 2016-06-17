package inout;

import blokus.*;
import joueur.*;
import IA.*;

import java.io.*;
import java.util.ArrayList;

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
		dossier = new File(chemin);
		
		if(!dossier.isDirectory()) {
			throw new IllegalArgumentException("Le chemin n'indique pas un dossier");
		}
	}
	
	/**
	  * Retourne la liste des fichier de sauvegarde present dans le repertoire
	  * @return un tableau de fichier
	  */
	public ArrayList<SauvegardeInfo> getFichiersSauvegardes() {
		ArrayList<SauvegardeInfo> ret = new ArrayList<SauvegardeInfo>();
		
		File[] fichiers = dossier.listFiles();;
		for(int i=0; i<fichiers.length; i++) {
			
			String nom = fichiers[i].getName();
			if(nom.endsWith(".sav")) {
				ret.add(creeSauvegardeInfo(fichiers[i]));
			}
			
		}
		
		return ret;
	}
	/**
	*Cree une Sauvegarde de partie
	*@param f le fichier
	*@return une SauvegardeInfo
	**/
	private SauvegardeInfo creeSauvegardeInfo(File f) {
		SauvegardeInfo ret = null;
		
		ObjectInputStream in = null;
		String j = null;
		String d = null;
		String t = null;
		try {
			in = new ObjectInputStream(new FileInputStream(f));
			
			j = in.readUTF();
			d = in.readUTF();
			t = in.readUTF();
			
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		if(j != null && d != null && t != null) {
			ret = new SauvegardeInfo(f,j,d,t);
		}
		
		return ret;
	}
	/**
	*Charge une partie
	*@param si la sauvegarde de la partie
	*@return une partie de Blokus
	**/
	public Jeu chargePartie(SauvegardeInfo si) {
		return chargePartie(si.getFile());
	}
	
	/**
	 * A partir d'un fichier de sauvegarde, crée un objet Jeu
	 * @param f le fichier texte
	 * @return l'objet Jeu
	 */
	public Jeu chargePartie(File f) {
		Jeu ret = null;
		
		if(!f.exists()) {
			return null;
		}
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(f));
			
			String tmp = in.readUTF();
			tmp = in.readUTF();
			tmp = in.readUTF();
			String[] tmps = tmp.split(" ");
			int tour = Integer.parseInt(tmps[1]);
			
			Plateau p = (Plateau)in.readObject();
			
			int nbJ = in.readInt();
			Joueur[] js = new Joueur[nbJ];
			for(int i=0; i<js.length; i++) {
				js[i] = (Joueur) in.readObject();
			}
			
			int indiceActif = in.readInt();
			
			ret = new Jeu(js,p,tour,indiceActif);
			
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		
		Chargement charge = new Chargement("sauvegarde");
		
		ArrayList<SauvegardeInfo> saveInfos = charge.getFichiersSauvegardes();
		
		for(SauvegardeInfo si : saveInfos) {
			
			System.out.println("Save : "+si.getFile().getName());
			System.out.println(si.getJoueurs()+"   "+si.getDate()+"   "+si.getTours()+"\n");
			
		}
		
		Jeu j = charge.chargePartie(saveInfos.get(3).getFile());
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}