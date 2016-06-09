package test;

import blokus.*;

import java.util.*;
import java.awt.Point;

import org.junit.*;
import static org.junit.Assert.*;

public class PlateauTest{
	
	@Test()
	public void testConstructor(){
		
		Plateau p = new Plateau();
		assertNotNull(p);
	}
	
	@Test()
	public void testTrouveEnterPossible(){
		Plateau p = new Plateau();
		
		p.trouveEnterPossible(1);
		ArrayList<Point> entres= p.getEntres();
		
		assertNotNull(entres.get(0));
	}
	
	@Test()
	public void testPlacePiece(){
		Plateau p = new Plateau();
		boolean[][] tab = new boolean[3][5];
		tab[0][0] = true;
		tab[1][0] = true;
		Piece piece= new Piece(Bloc.JAUNE,tab);
		
		assertTrue(p.peutPlacerPiece(piece,0,0));
		assertFalse(p.peutPlacerPiece(piece,5,0));
		
		p.placePiece(piece,0,0);
		
		assertFalse(p.peutPlacerPiece(piece,0,0));
		
	}
	
	
}