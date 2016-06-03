package test;

import blokus.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class PieceTest {
  
	@Test()
	public void testConstructor1() {
	
		Piece p = new Piece(Bloc.VERT);
		assertNotNull(p);
		
		boolean ok = false;
		try {
			Piece p1 = new Piece(15);
		}
		catch(IllegalArgumentException e) {
			ok = true;
		}
		assertTrue(ok);
		
	}
	
	@Test()
	public void testConstructor2() {
		boolean[][] tab = new boolean[3][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p3 = new Piece(Bloc.ROUGE,tab);
		assertNotNull(p3);
		
		assertFalse(p3.getPosition() == p3.getPosDefaut());
		assertFalse(p3.getPosition() == tab);
	}
	
	@Test()
	public void testEquals() {
	
		Piece p = new Piece(Bloc.VERT);
		assertTrue(p.equals(p));
		
		Piece p2 = new Piece(Bloc.ROUGE);
		assertFalse(p.equals(p2));
		
		boolean[][] tab = new boolean[3][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p3 = new Piece(Bloc.ROUGE,tab);
		assertTrue(p3.equals(p3));
		
		tab = new boolean[3][5];
		tab[0][1] = true;
		tab[1][0] = true;
		Piece p4 = new Piece(Bloc.ROUGE,tab);
		assertFalse(p4.equals(p3));
	}
	
	@Test()
	public void testEquals2() {
		boolean[][] tab = new boolean[3][5];
		Piece p5 = new Piece(Bloc.ROUGE,tab);
		
		Piece p2 = new Piece(Bloc.ROUGE);
		assertTrue(p5.equals(p2));
		
		tab = new boolean[3][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p3 = new Piece(Bloc.ROUGE,tab);
		
		tab = new boolean[3][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece p1 = new Piece(Bloc.ROUGE,tab);
		assertTrue(p1.equals(p3));
		
	}
	
	// @Test()
	// public void testRotation() {
		// assertNotNull(null);
	// }
  
}