package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class TooFewArgumentsExceptionTest{
	private ArgumentParser argp;
	private String s;

	@Before
	public void setUpMyTest(){
		argp = new ArgumentParser();
		s = "height";
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
	}
	
	@Test
	public void testTooFewArguments() {
		boolean thrown = false;
		argp.parse("VolCal 0 0 0");
		String a = argp.usageOutput();
		
		try {
			throw new TooFewArgumentsException(s,a);
		} catch (TooFewArgumentsException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
		@Test
	public void testGetString(){
		try{
			argp.parse("VolCal 7 5");
		} catch(TooFewArgumentsException e){
			String a = e.toString();
			String b = "VolCal usage: length width height The following arguments are required: height";
			assertEquals(b,a);
		}
	}
	
	@Test
	public void testGetName(){
		try{
			argp.parse("VolCal 7 5");
		} catch(TooFewArgumentsException e){
			String a = e.getMissingArgumentName();
			String b = "height";
			assertEquals(b,a);
		}
	}
}