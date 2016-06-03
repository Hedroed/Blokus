package test;

import blokus.*;

import org.junit.*;
import static org.junit.Assert.*;

public class BlocTest {
  
	@Test
	public void testGetter() {
	
		Bloc b = new Bloc(Bloc.ROUGE,1,5);
		
		assertNotNull(b);
		assertEquals(b.getCouleur(),Bloc.ROUGE);
		assertEquals(b.getPosX(),1);
		assertEquals(b.getPosY(),5);
	}
	
	@Test
	public void testSetter() {
	
		Bloc b = new Bloc(Bloc.ROUGE,0,0);
		
		b.setPosX(5);
		b.setPosY(42);
		
		b.setCouleur(Bloc.VERT);
		
		assertEquals(b.getCouleur(),Bloc.VERT);
		assertEquals(b.getPosX(),5);
		assertEquals(b.getPosY(),42);
	}
	
	
  
}