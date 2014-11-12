package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class InvalidFileExceptionTest{
	@Test
	public void testGetFile(){
	String a;
	try {throw new InvalidFileException("Corolla.java");
	
	} catch (InvalidFileException e) {
		a=e.getFile();
		
	}
	
	assertEquals(a,"Corolla.java");



}


