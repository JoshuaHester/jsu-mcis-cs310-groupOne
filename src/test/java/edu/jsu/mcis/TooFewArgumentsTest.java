package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class TooFewArgumentsTest{
	
	@Test
	public void testTooFewyArguments() {
		boolean thrown = false;
		ArgumentParser argp = new ArgumentParser();
		String s = "height";
		
		try {
			throw new TooFewArguments(s);
		} catch (TooFewArguments e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
}