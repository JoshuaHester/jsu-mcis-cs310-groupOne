package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class XMLExceptionTest{
	@Test
	public void testGetFile(){
	String a;
	try {throw new XMLException("Corolla.java", "This is the message.");
	
	} catch (XMLException e) {
		a=e.getFile();
		
	}
	
	assertEquals(a,"Corolla.java");

	}
	


	@Test
	public void XMLExceptionStringTest(){
		String b;
		try {throw new XMLException("Corolla.java", "This is the message.");
		} catch (XMLException e) {
			b=e.getMessage();	
		}
		
		assertEquals(b,"This is the message.");
	}
}