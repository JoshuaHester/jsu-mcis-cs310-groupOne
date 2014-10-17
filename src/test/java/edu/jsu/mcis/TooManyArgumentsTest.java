package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class TooManyArgumentsTest{
	
	@Test
	public void testTooManyArguments() {
		boolean thrown = false;
		ArgumentParser argp = new ArgumentParser();
		String s = "43";
		
		try {
			throw new TooManyArguments(s);
		} catch (TooManyArguments e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
}
        