
import blokus.*;
import joueur.*;
import IA.*;
import inout.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BlokusEnLigne {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Jeu jeu = null;
		
		System.out.println("Qu'est ce que tu vas faire ?");
		String line = sc.nextLine();
		while(jeu == null) {
			
			if(Pattern.matches("charge [0-9]*", line)) {
				String[] param = line.split(" ");
				
				int nb = Integer.parseInt(param[1]);
				
				Chargement charge = new Chargement("sauvegarde");
				ArrayList<SauvegardeInfo> saveInfos = charge.getFichiersSauvegardes();
				jeu = charge.chargePartie(saveInfos.get(nb));
				
			}
			else if(line.equals("nouveau")) {
				Joueur[] lesJoueurs = new Joueur[4];
		
				lesJoueurs[0] = new IA("Patrik",Bloc.ROUGE,DifficulteFactory.FACILE);
				lesJoueurs[1] = new IA("Kevin",Bloc.VERT,DifficulteFactory.FACILE);
				lesJoueurs[2] = new IA("Michel",Bloc.JAUNE,DifficulteFactory.FACILE);
				lesJoueurs[3] = new Joueur("René",Bloc.BLEU);
				
				jeu = new Jeu(lesJoueurs);
			}
			else {
				line = sc.nextLine();
			}
			
		}
		
		
		Joueur j = null;
		Plateau plat = null;
		while(true) {
			
			System.out.println("Tour : "+jeu.getTour());
			
			j = jeu.getJoueurActif();
			System.out.println("Au joueur : "+j);
			
			plat = jeu.getPlateau();
			plat.trouveEnterPossible(j.getCouleur());
			System.out.println(plat);
			
			// System.out.println("vide");
			// sc.nextLine();
			
			if(j.isIA()) {
				
				IAAction action = ((IA)j).placePiece(plat);
				
				if(action != null) {
					
					if(plat.peutPlacerPiece(action.getPiece(),action.getX(),action.getY())) {
						plat.placePiece(action.getPiece(),action.getX(),action.getY());
						j.jouerPiece(action.getPiece());
						if(jeu.nouveauTour()) {
							afficheScores(jeu.getJoueurs());
						}
					}
					
				}
				else{
					System.out.println("L'ia "+j.getNom()+" ne peut plus jouer");
					j.setPeutJouer(false);
					if(jeu.nouveauTour()) {
						afficheScores(jeu.getJoueurs());
					}
				}
				
			}
			else {
			
				ArrayList<Piece> ps = j.getPieces();
				
				int i=0;
				for(Piece p : ps) {
					System.out.println(i+" :");
					System.out.println(p);
					i++;
				}
				
				System.out.println("Qu'est ce que tu vas faire ?");
				line = sc.nextLine();
				
				if(Pattern.matches("-*[0-9]{0,2} -*[0-9]{0,2} [0-9]{0,3}", line)) {
					
					String[] param = line.split(" ");
				
					int coo1 = Integer.parseInt(param[0]);
					int coo2 = Integer.parseInt(param[1]);
					
					int pieceId = Integer.parseInt(param[2]);
					
					if(pieceId < 0 || pieceId >= 21) {
						continue;
					}
					
					Piece pSelect = ps.get(pieceId);
					
					boolean b = plat.peutPlacerPiece(pSelect,coo1,coo2);
					
					System.out.println("Peut etre placer :"+b);
					
					if(b) {
						plat.placePiece(pSelect,coo1,coo2);
						j.jouerPiece(pSelect);
						if(jeu.nouveauTour()) {
							afficheScores(jeu.getJoueurs());
						}
					}
				}
				else if(Pattern.matches("rotation [0-9]{0,3} [1-4]", line)) {
					
					String[] param = line.split(" ");
				
					int id = Integer.parseInt(param[1]);
					
					if(id < 0 || id >= ps.size()) {
						continue;
					}
					Piece pSelect = ps.get(id);
					
					int rotId = Integer.parseInt(param[2]);
					
					if(rotId == 1) {
						pSelect.pivoterDroite();
					}
					else if(rotId == 2) {
						pSelect.pivoterGauche();
					}
					else if(rotId == 3) {
						pSelect.miroirHorizontale();
					}
					else if(rotId == 4) {
						pSelect.miroirVerticale();
					}
					
				}
				else if(line.equals("save")) {
					
					
					Sauvegarde save = new Sauvegarde("sauvegarde");
		
					save.sauvePartie(jeu);
					
					System.exit(0);
					
				}
				else {
					System.out.println("incorrect");
				}
			
			}
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	public static void afficheScores(Joueur[] js) {
		
		System.out.println();
		
		for(int i=0; i<js.length; i++) {
			System.out.println(js[i]+" avec un score de "+js[i].getScore()+" nb piece :"+js[i].getPieces().size());
			
		}
		
		System.exit(0);
	}
	
	
	
}