package edu.jsu.mcis;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;

public class InvalidArgumentExceptionTest{

	@Test
	public void testGetBadArgument() {
	String value;
		try {
			throw new InvalidArgumentException("cat");
		} catch (InvalidArgumentException e) {
			value = e.getValue();
		}
		
		assertEquals("cat",value);
	}
	
	
	
	
	
	
	
}