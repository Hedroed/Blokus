package test;

import blokus.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class PieceTest {
  
	@Test
	public void testConstructor() {
	
		Piece p = new Piece(new Bloc(new Couleur("vert")));
		assertNotNull(p);
		
		try {
			Piece p1 = new Piece(null);
			assertNull(p1);
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		boolean[][] tab = new boolean[5][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p3 = new Piece(new Bloc(new Couleur("rouge")),tab);
		assertNotNull(p3);
		
		assertFalse(p3.getPosition() == p3.getPosDefaut());
		assertFalse(p3.getPosition() == tab);
	}
	
	@Test
	public void testEquals() {
	
		Piece p = new Piece(new Bloc(new Couleur("vert")));
		assertTrue(p.equals(p));
		
		Piece p2 = new Piece(new Bloc(new Couleur("rouge")));
		assertFalse(p.equals(p2));
		
		boolean[][] tab = new boolean[5][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p3 = new Piece(new Bloc(new Couleur("rouge")),tab);
		assertTrue(p3.equals(p3));
		
		tab = new boolean[5][5];
		tab[0][1] = true;
		tab[1][0] = true;
		Piece p4 = new Piece(new Bloc(new Couleur("rouge")),tab);
		assertFalse(p4.equals(p3));
	}
	
	@Test
	public void testEquals2() {
		boolean[][] tab = new boolean[5][5];
		Piece p5 = new Piece(new Bloc(new Couleur("rouge")),tab);
		
		Piece p2 = new Piece(new Bloc(new Couleur("rouge")));
		assertTrue(p5.equals(p2));
		
		tab = new boolean[5][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p3 = new Piece(new Bloc(new Couleur("rouge")),tab);
		
		tab = new boolean[5][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p1 = new Piece(new Bloc(new Couleur("rouge")),tab);
		assertTrue(p1.equals(p3));
		
	}
	
	// @Test
	// public void testRotation() {
		// assertNotNull(null);
	// }
  
}