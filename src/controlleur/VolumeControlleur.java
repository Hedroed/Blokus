package controlleur;

import ihm.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.net.URISyntaxException;
/**
*Controle les interactions avec le panneau FenetreOptions
*@extends MouseAdapter
**/
public class VolumeControlleur extends MouseAdapter{
	
	private FenetreOptions p;
	
	private Controlleur control;
	
	private int sonMusique;
	private int sonEffet;
	
	private File fichier;
	
	/**
	*Constructeur de VolmeControlleur
	*@param pan le panneau ou les actions sont detetctees
	*@param c le Controlleur
	**/
	public VolumeControlleur(FenetreOptions pan, Controlleur c){
		p=pan;
		

		fichier = new File("volume");
		if(!fichier.exists()) {
			try {
				fichier.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			sonMusique = 50;
			sonEffet = 50;
			sauvegardeVolume();
		}
		else {
			chargeVolume();
		}
		
		
		control = c;
		control.getMusic().setVolume((float)(sonMusique*60/100-60));
		control.getEffet().setVolume((float)(sonEffet*60/100-60));
	}
	
	/**
	*Reagit lorsque on a clique sur le panneau FenetreOptions
	*@param e l'evenement de la souris detecte
	*/
	public void mousePressed(MouseEvent e){
		if(e.getX() >= p.getCoordBar() && e.getX() <= p.getCoordBar()+p.getTailleBar()){
			if(e.getY() >= (Fenetre.HEIGHT*0.360) && e.getY() <= (int)(Fenetre.HEIGHT*0.430)){
				sonMusique = (e.getX()-20-p.getCoordBar())/(p.getTailleBar()/100);
				p.setMusique(sonMusique);
				control.getMusic().setVolume((float)(sonMusique*60/100-60));
			}
			if(e.getY() >= (Fenetre.HEIGHT*0.595) && e.getY() <= (int)(Fenetre.HEIGHT*0.655)){
				sonEffet = (e.getX()-20-p.getCoordBar())/(p.getTailleBar()/100);
				p.setSon(sonEffet);
				control.getEffet().setVolume((float)(sonEffet*60/100-60));
			}
		}
		// System.out.println((e.getX()-p.getCoordBar())/(p.getTailleBar()/100));
		
		
	}
	
	/**
	* Reagit lorsque on relache le bouton de la souris
	* Sauvegarde le volume dans un fichier
	* @param e l'evenement de la souris detecte
	*/
	public void mouseReleased(MouseEvent e){
		
		control.getEffet().play("bouton");
		sauvegardeVolume();
		
	}
	
	/**
	*Reagit si le curseur a ete presse et glisse
	*@param e l'evenement detecte
	*/
	public void mouseDragged(MouseEvent e){
		mousePressed(e);
	}
	/**
	*Accesseur du volume de la musique
	*@return le volume de la musique
	**/
	public int getVolumeMusic() {
		return sonMusique;
	}
	/**
	*Accesseur du volume des effets sonores
	*@return le volume des effets sonores
	**/
	public int getVolumeEffet() {
		return sonEffet;
	}
	/**
	*CHerche la valeur apres ":" d'un String
	*@param s le String
	*@return la valeur
	**/
	private int getOptionValue(String s) {
		int i = s.indexOf(':');
		s = s.substring(i+1);
		return Integer.parseInt(s);
	}
	/**
	*Trouve la valeur du volume du son enregistre dans un fichier.
	**/
	private void chargeVolume() {
		
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(fichier));
			
			String s = in.readLine();
			s = s.substring(s.indexOf(':')+1);
			sonMusique = Integer.parseInt(s);
			
			s = in.readLine();
			s = s.substring(s.indexOf(':')+1);
			sonEffet = Integer.parseInt(s);
			
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	*Sauvegarde la valeur du son dans un fichier
	**/
	private void sauvegardeVolume() {
		
		BufferedWriter out = null;
		
		try {
			out = new BufferedWriter(new FileWriter(fichier));
			out.write("Volume musique:"+sonMusique+"\n");
			out.write("volume effet:"+sonEffet+"\n");
			out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
