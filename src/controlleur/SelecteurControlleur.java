package controlleur;

import java.awt.event.*;
import javax.swing.*;

import ihm.*;
import blokus.*;
import IA.*;
import joueur.*;

/**
  *Renvoie sur la page souhaitee en fonction du clic sur un bouton
  *@implements ActionListener
  */
public class SelecteurControlleur implements ActionListener{
	
	public static final int JOUEUR = 0;
	public static final int IA_1 = 1;
	public static final int IA_2 = 2;
	public static final int IA_3 = 3;
	public static final int IA_4 = 4;
	
	private SelecteurPartie p;
	private int[] joueursType;
	
	/**
	*Constructeur du SelecteurControlleur
	*@param p le SelecteurPartie
	**/
	public SelecteurControlleur(SelecteurPartie p){
		this.p = p;
		joueursType = new int[4];
	}
	/**
	*Lorsqu'un bouton est presse
	*@param e l'ActionEvent detecte
	**/
	public void actionPerformed(ActionEvent e) {
		
		if(p == null) {throw new BlokusException("Selecteur controlleur : selecteurPartie inconnue");}
		
		int i = Integer.parseInt(e.getActionCommand());
		
		
		String type = null;
		String niveau = null;
		
		if(joueursType[i] == JOUEUR) {
			joueursType[i] = IA_1;
			type = "I.A. "+(i+1);
			niveau = "Facile";
		}
		else if(joueursType[i] == IA_1) {
			joueursType[i] = IA_2;
			type = "I.A. "+(i+1);
			niveau = "Moyen";
		}
		else if(joueursType[i] == IA_2) {
			joueursType[i] = IA_3;
			type = "I.A. "+(i+1);
			niveau = "Difficile";
		}
		else if(joueursType[i] == IA_3) {
			joueursType[i] = IA_4;
			type = "I.A. "+(i+1);
			niveau = "Expert";
		}
		else if(joueursType[i] == IA_4) {
			joueursType[i] = JOUEUR;
			
			type = "Joueur "+(i+1);
		}
		
		p.setNoms(i,type,niveau);
		
	}
	/**
	*Cree les joueurs en fonction de leur type, IA ou Humain
	*@return un tableau contenant tous les joueurs
	**/
	public Joueur[] creeJoueurs() {
		Joueur[] ret = new Joueur[4];
		
		int[] couleurs = new int[] {Bloc.BLEU,Bloc.JAUNE,Bloc.ROUGE,Bloc.VERT};
		int[] niveau = new int[] {DifficulteFactory.FACILE,DifficulteFactory.MOYEN,DifficulteFactory.DUR,DifficulteFactory.EXPERT};
		
		for(int i=0; i<ret.length; i++) {
			
			if(joueursType[i] == JOUEUR) {
				ret[i] = new Joueur("Joueur "+(i+1),couleurs[i]);
			}
			else {
				ret[i] = new IA("IA "+(i+1),couleurs[i],niveau[joueursType[i]-1]);
			}
		}
		
		return ret;
	}
	
}