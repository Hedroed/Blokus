package test;

import blokus.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CouleurTest {
  
	@Test
	public void testConstructor() {
	
		Couleur c = new Couleur("lapin");
		
		assertNotNull(c);
	}
	
	@Test
	public void testToString() {
	
		Couleur c = new Couleur("vert");
		
		assertEquals(c.toString(),"vert");
	}
	
	
  
}