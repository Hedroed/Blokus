package test;

import blokus.*;

import org.junit.*;
import static org.junit.Assert.*;

public class BlocTest {
  
	@Test
	public void testGetter() {
	
		Bloc b = new Bloc(Couleur.ROUGE,1,5);
		
		assertNotNull(b);
		assertEquals(b.getCouleur(),Couleur.ROUGE);
		assertEquals(b.getPosX(),1);
		assertEquals(b.getPosY(),5);
	}
	
	@Test
	public void testSetter() {
	
		Bloc b = new Bloc(Couleur.ROUGE,0,0);
		
		b.setPosX(5);
		b.setPosY(42);
		
		b.setCouleur(Couleur.VERT);
		
		assertEquals(b.getCouleur(),Couleur.VERT);
		assertEquals(b.getPosX(),5);
		assertEquals(b.getPosY(),42);
	}
	
	
  
}