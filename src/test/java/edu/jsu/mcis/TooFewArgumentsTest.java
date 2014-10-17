package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class TooFewArgumentsTest{
	
	@Test
	public void testTooFewyArguments() {
		boolean thrown = false;
		ArgumentParser argp = new ArgumentParser();
		String s = "VolCal 7 5";
		
		try {
			throw new TooFewArguments(s);
		} catch (TooFewArguments e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
}