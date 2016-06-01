package test;

import blokus.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CouleurTest {
  
	@Test
	public void testEstCouleur() {
		
		assertTrue(Couleur.estCouleur(Couleur.ROUGE));
		
		assertFalse(Couleur.estCouleur("cheval"));
	}
  
}