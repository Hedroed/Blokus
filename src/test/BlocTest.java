package test;

import blokus.*;

import org.junit.*;
import static org.junit.Assert.*;

public class BlocTest {
  
	@Test
	public void testGetter() {
	
		Bloc b = new Bloc(null,1,5);
		
		assertNotNull(b);
		assertNull(b.getCouleur());
		assertEquals(b.getPosX(),1);
		assertEquals(b.getPosY(),5);
	}
	
	@Test
	public void testSetter() {
	
		Bloc b = new Bloc(null,0,0);
		
		b.setPosX(5);
		b.setPosY(42);
		
		Couleur c1 = new Couleur("vert");
		b.setCouleur(c1);
		
		assertEquals(b.getCouleur(),c1);
		assertEquals(b.getPosX(),5);
		assertEquals(b.getPosY(),42);
	}
	
	
  
}