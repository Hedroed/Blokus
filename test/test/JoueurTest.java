package test;

import joueur.*;
import blokus.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class JoueurTest{

	@Test()
	public void testConstructor(){
		
		Joueur j = new Joueur("Robert",2);
		assertNotNull(j);
		
		assertFalse(j.isIA());
	}
	
	@Test()
	public void testJouerPiece(){
		
		Joueur j = new Joueur("Robert",2);
		ArrayList<Piece> pieces = j.getPieces();
		int taille = pieces.size();
		Piece p = pieces.get(20);
		j.setPeutJouer(true);
		
		j.jouerPiece(p);
		
		assertTrue(pieces.size() == taille-1);
		assertTrue(j.getScore()==5);
		
	}
}