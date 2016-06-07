
import blokus.*;
import joueur.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BlokusEnLigne {
	
	public static void main(String[] args) {
		
		Joueur[] lesJoueurs = new Joueur[4];
		
		lesJoueurs[0] = new Joueur("Patrik",Bloc.BLEU);
		lesJoueurs[1] = new Joueur("Kevin",Bloc.JAUNE);
		lesJoueurs[2] = new Joueur("Michel",Bloc.ROUGE);
		lesJoueurs[3] = new Joueur("René",Bloc.VERT);
		
		Jeu jeu = new Jeu(lesJoueurs);
		
		Scanner sc = new Scanner(System.in);
		
		Joueur j = null;
		Plateau plat = null;
		while(true) {
			
			System.out.println("Tour : "+jeu.getTour());
			
			j = jeu.getJoueurActif();
			System.out.println("Au joueur : "+j);
			
			plat = jeu.getPlateau();
			plat.trouveEnterPossible(j.getCouleur());
			System.out.println(plat);
			
			ArrayList<Piece> ps = j.getPieces();
			
			int i=0;
			for(Piece p : ps) {
				System.out.println(i+" :");
				System.out.println(p);
				i++;
			}
			
			System.out.println("Qu'est ce que tu vas faire ?");
			String line = sc.nextLine();
			
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
					jeu.nouveauTour();
				}
			}
			else if(Pattern.matches("rotation [0-9]{0,3} [1-4]", line)) {
				
				String[] param = line.split(" ");
			
				int id = Integer.parseInt(param[1]);
				
				if(id < 0 || id >= 21) {
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
			else {
				System.out.println("incorrect");
			}
			
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
}