package test;

import blokus.*;
import joueur.*;
import IA.*;

import org.junit.*;
import static org.junit.Assert.*;

public class JeuTest {
	
	@Test()
	public void testConstructeur() {
	
		Joueur[] lesJoueurs = new Joueur[4];
		
		lesJoueurs[0] = new IA("Patrik",Bloc.ROUGE,DifficulteFactory.FACILE);
		lesJoueurs[1] = new IA("Kevin",Bloc.VERT,DifficulteFactory.FACILE);
		lesJoueurs[2] = new Joueur("Michel",Bloc.JAUNE);
		lesJoueurs[3] = new Joueur("René",Bloc.BLEU);
		
		Jeu jeu = new Jeu(lesJoueurs);
		
		assertNotNull(jeu);
	}
	
	@Test()
	public void testNouveauTour() {
		
		Joueur[] lesJoueurs = new Joueur[4];
		
		lesJoueurs[0] = new IA("Patrik",Bloc.ROUGE,DifficulteFactory.FACILE);
		lesJoueurs[1] = new IA("Kevin",Bloc.VERT,DifficulteFactory.FACILE);
		lesJoueurs[2] = new Joueur("Michel",Bloc.JAUNE);
		lesJoueurs[3] = new Joueur("René",Bloc.BLEU);
		
		Jeu jeu = new Jeu(lesJoueurs);
		
		Joueur j1 = jeu.getJoueurActif();
		
		assertFalse(jeu.nouveauTour());
		
		Joueur j2 = jeu.getJoueurActif();
		
		assertFalse(j1 == j2);
	}
	
	
	
}