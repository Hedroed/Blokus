package test;

import joueur.*;
import blokus.*;
import IA.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class IATest{

	@Test()
	public void testConstructor(){
	
		IA ia= new IA("Albert",Bloc.ROUGE,DifficulteFactory.FACILE);
		assertNotNull(ia);
		assertTrue(ia.isIA());
	}
}