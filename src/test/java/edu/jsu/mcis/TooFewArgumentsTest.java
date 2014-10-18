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
	
		@Test
	public void testGetString(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		try{
			argp.parse("VolCal 7 5");
		} catch(TooFewArguments e){
			String a = e.toString();
			String b = "The following arguments are required: height";
			assertEquals(b,a);
		}
	}
	
	@Test
	public void testGetName(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		try{
			argp.parse("VolCal 7 5");
		} catch(TooFewArguments e){
			String a = e.getInfo();
			String b = "height";
			assertEquals(b,a);
		}
	}
}