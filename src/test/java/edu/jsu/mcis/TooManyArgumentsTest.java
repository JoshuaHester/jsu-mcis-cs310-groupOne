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
	
	@Test
	public void testGetString(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		try{
			argp.parse("VolCal 7 5 2 43");
		} catch(TooManyArguments e){
			String a = e.toString();
			String b = "Unrecognized arguments: 43";
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
			argp.parse("VolCal 7 5 2 43");
		} catch(TooManyArguments e){
			String a = e.getInfo();
			String b = "43";
			assertEquals(b,a);
		}
	}
}
        