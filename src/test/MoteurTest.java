package test;

import controlleur.*;

import org.junit.*;
import static org.junit.Assert.*;

public class MoteurTest {
  
	@Test
	public void testMoteur() {
	
		Moteur m = new Moteur();
		
		assertNotNull(m);
	}
	
  
}