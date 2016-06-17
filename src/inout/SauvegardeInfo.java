package inout;

import blokus.*;
import joueur.*;
import IA.*;

import java.io.*;
/**
*Objet regroupant les informations d'une sauvegarde
**/
public class SauvegardeInfo {
	
	private String laDate;
	private String joueurs;
	private String tours;
	
	private File fichier;
	
	/**
	*Constructor
	*@param fichier le fichier de sauvegarde
	*@param joueurs le nb de joueurs et leur type
	*@param laDate la date de la sauvegarde
	*@param tours le nb de tours de la partie
	**/
	public SauvegardeInfo(File fichier, String joueurs, String laDate, String tours) {
		
		this.laDate = laDate;
		this.joueurs = joueurs;
		this.tours = tours;
		
		this.fichier = fichier;
		
	} 
	
	public String getJoueurs() {
		return joueurs;
	}
	
	public String getDate() {
		return laDate;
	}
	
	public String getTours() {
		return tours;
	}
	
	//package only
	File getFile() {
		return fichier;
	}
	/**
	*Met les infos sur la partie dans un String
	*@return un String regroupant les infos de la partie
	**/
	public String toString() {
		return "Sauvegarde : "+joueurs+"  "+laDate+"  "+tours;
	}
	
}