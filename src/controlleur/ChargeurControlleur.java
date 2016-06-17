package controlleur;

import ihm.*;
import inout.*;
import blokus.*;

import java.util.ArrayList;
import java.awt.event.*;
/**
*Controle les actions effectuees sur le panneau Charger partie
*@extends MouseAdapter
**/
public class ChargeurControlleur extends MouseAdapter{
	
	private ChargementPartie p;
	
	private Chargement chargeur;
	
	private ArrayList<SauvegardeInfo> sauvegardes;
	private int indiceSelect;
	
	private int tailleMax;
	
	public ChargeurControlleur(ChargementPartie pan){
		p=pan;
		chargeur = new Chargement("sauvegarde");
	}
	
	/**
	*Reagit lorsque on a clique sur le panneau FenetreOptions
	*@param e l'evenement de la souris detecte
	*/
	public void mousePressed(MouseEvent e){
		
		int x = (int)(Fenetre.WIDTH*0.205);
		int y = (int)(Fenetre.HEIGHT*0.33);
		
		int h = 40;
		
		int w1 = (int)(Fenetre.WIDTH*0.18);
		int w2 = (int)(Fenetre.WIDTH*0.2);
		int w3 = (int)(Fenetre.WIDTH*0.25);
		
		if(e.getX() > x && e.getX() <= x+w1+w2+w3){
			if(e.getY() > y && e.getY() <= y+h*8){
				
				int i = (e.getY()-y)/h;
				System.out.println("Charge partie numero "+i);
				
				if(i>=0 && i < tailleMax) {
					indiceSelect = i;
					p.select(i);
				}
			}
		}
		
		
	}
	/**
	*Explre les fichiers pour trouver les sauvegardes de parties
	**/
	public void chercheSauvegarde() {
		
		sauvegardes = chargeur.getFichiersSauvegardes();
		indiceSelect = 0;

		tailleMax = Math.min(sauvegardes.size(),8);
		
		String[] js = new String[tailleMax];
		String[] ds = new String[tailleMax];
		String[] ts = new String[tailleMax];
		
		for(int i=0; i<tailleMax; i++) {
			
			js[i] = sauvegardes.get(i).getJoueurs();
			ds[i] = sauvegardes.get(i).getDate();
			ts[i] = sauvegardes.get(i).getTours();
			
			System.out.println(sauvegardes.get(i));
		}
		
		p.setSauvegardes(js,ds,ts);
		
	}
	/**
	*Charge la partie selectioinne
	*@throws BlokusException
	**/
	public Jeu chargePartie() {
		if(sauvegardes == null) {throw new BlokusException("Chargement impossible aucune sauvegarde charger");}
		
		if(!sauvegardes.isEmpty()) {
			return chargeur.chargePartie(sauvegardes.get(indiceSelect));
		}
		
		return null;
	}
	
}