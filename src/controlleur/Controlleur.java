package controlleur;

import java.awt.event.*;
import javax.swing.*;

import joueur.*;
import IA.*;
import blokus.*;
import ihm.*;
import inout.*;

/**
  *Renvoie sur la page souhaitee en fonction du clic sur un bouton
  *@implements ActionListener
  */
public class Controlleur implements ActionListener{
	
	private Fenetre f;
	
	private Sauvegarde sauve;
	private Moteur partieEnCour;
	
	private SelecteurControlleur controlSelect;
	private ChargeurControlleur controlCharge;
	
	private Son music;
	private Son effet;
	/**
	*Constructeur de la classe Controlleur
	*@param f la fenetre a controller
	**/
	public Controlleur(Fenetre f){
		this.f = f;
		
		this.sauve = new Sauvegarde("sauvegarde");
		
		this.music = new Son(true);
		this.effet = new Son(false);
		chargeSons();
		
		music.setVolume(-70f);
		effet.setVolume(-70f);
		
		music.play("menu");
	}
	/**
	*Agit lorsque l'on a appuye sur un bouton
	*@param arg0 l'ActionEvent detecte
	**/
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println(arg0.paramString());
		
		JButton o=(JButton)arg0.getSource();
		
		String s=o.getActionCommand();
		
		effet.play("bouton");
		
		if(s.equals(Fenetre.MENU)){
			f.setFenetreActive(Fenetre.MENU);
			
			System.out.println("play menu menu");
			music.play("menu");
			
			if(partieEnCour != null) {
				partieEnCour.stopPartie();
				partieEnCour = null;
			}
		}
		else if(s.equals(Fenetre.SELECTEUR)){
			
			music.play("menu");
			
			f.setFenetreActive(Fenetre.SELECTEUR);
		}
		else if(s.equals(Fenetre.PARTIE)){
			f.setFenetreActive(Fenetre.PARTIE);
		}
		else if(s.equals(Fenetre.OPTIONS)){
			f.setFenetreActive(Fenetre.OPTIONS);
		}
		else if(s.equals(Fenetre.CHARGEUR)){
			
			if(controlCharge == null) {throw new BlokusException("ChargeurControlleur == null");}
			
			controlCharge.chercheSauvegarde();
			
			f.setFenetreActive(Fenetre.CHARGEUR);
		}
		else if(s.equals("quitter")) {
			f.quitter();
		}
		else if(s.equals("retourOptions")){
			
			f.nouvelleResolution();
			
			System.out.println("partie en cour :"+partieEnCour);
			
			//maj sons
			
			if(partieEnCour == null) {
				f.setFenetreActive(Fenetre.MENU);
			}
			else {
				f.setFenetreActive(Fenetre.PARTIE);
			}
		}
		else if(s.equals("charger")){
			
			if(controlCharge == null) {throw new BlokusException("ChargeurControlleur == null");}
			
			Jeu jeu = controlCharge.chargePartie();
			
			if(jeu != null) {
				partieEnCour = new Moteur(jeu,f.getFenetrePartie(),this);
				
				f.getFenetrePartie().nouvellePartie(partieEnCour,jeu.getJoueurs(),jeu.getPlateau());
				
				music.play("jeu");
				
				f.setFenetreActive(Fenetre.PARTIE);
				
				partieEnCour.lancerPartie();
			}
			else {
				controlCharge.chercheSauvegarde();
			}
		}
		else if(s.equals("nouvelle")) {
			
			if(controlSelect == null) {throw new BlokusException("SelecteurControlleur == null");}
			
			System.out.println("Controlleur : nouvelle partie !!! ");
			
			Joueur[] lesJoueurs = controlSelect.creeJoueurs();
			
			Jeu jeu = new Jeu(lesJoueurs);
			
			partieEnCour = new Moteur(jeu,f.getFenetrePartie(),this);
			
			f.getFenetrePartie().nouvellePartie(partieEnCour,lesJoueurs,jeu.getPlateau());
			
			music.play("jeu");
			
			f.setFenetreActive(Fenetre.PARTIE);
			
			partieEnCour.lancerPartie();
			
		}
		else if(s.equals("sauvegarde")){
			if(partieEnCour != null) {
				sauve.sauvePartie(partieEnCour.getJeu());
			}
			else {
				System.out.println("Sauvegarde Erreur : aucune partie a sauvegarder");
			}
		}
		
		if(s.equals("affMenu")){
			f.setPausePanneauVisible(true);
		}
		else if(s.equals("affScore")){
			
			finDePartie();
		}
		else {
			f.getGlassPane().setVisible(false);
		}

	}
	/**
	*Accesseur du SelecteurControlleur
	*@return le SelecteurControlleur
	**/
	public SelecteurControlleur getSelecteurControlleur() {
		return controlSelect;
	}
	/**
	*Modifie le SelecteurControlleur
	*@param c le nouveau SelecteurControlleur
	**/
	public void setSelecteurControlleur(SelecteurControlleur c) {
		controlSelect = c;
	}
	/**
	*Accesseur du ChargeurControlleur
	*@return le ChargeurControlleur
	**/
	public ChargeurControlleur getChargeurControlleur() {
		return controlCharge;
	}
	/**
	*Modifie le ChargeurControlleur
	*@param c le nouveau ChargeurControlleur
	**/
	public void setChargeurControlleur(ChargeurControlleur c) {
		controlCharge = c;
	}
	/**
	*Detecte la fin d'une partie
	**/
	public void finDePartie() {
		if(partieEnCour != null) {
			effet.play("victoire");
		
			Joueur[] js = partieEnCour.getJeu().getJoueurs();
			String[] noms = new String[js.length];
			int[] scores = new int[js.length];
			for(int i=0; i<js.length; i++) {
				scores[i] = js[i].getScore();
				noms[i] = js[i].getNom();
			}
			
			f.getScorePanneau().setNoms(noms);
			f.getScorePanneau().setScores(scores);
			f.getScorePanneau().triTableau();
			f.setScorePanneauVisible(true);
			
			partieEnCour = null;
		}
	}
	/**
	*Charge les ressources de sons
	**/
	private void chargeSons() {
		
		music.add("jeu","sons/ambiant.wav");
		music.add("menu","sons/menu.wav");
		
		effet.add("victoire","sons/victoire.wav");
		effet.add("rotation","sons/rotation.wav");
		effet.add("bouton","sons/bouton.wav");

	}
	/**
	*Accesseur de l'effet sonore
	*@return l'effet
	**/
	public Son getEffet() {
		return effet;
	}
	/**
	*Accesseur de la musique
	*@return la musique
	**/
	public Son getMusic() {
		return music;
	}
	
}