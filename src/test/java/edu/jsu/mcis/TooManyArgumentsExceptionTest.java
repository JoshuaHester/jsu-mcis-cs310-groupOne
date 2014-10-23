package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class TooManyArgumentsExceptionTest{
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
	public void testTooManyArguments() {
		boolean thrown = false;
		argp.parse("0 0 0");
		try {
			throw new TooManyArgumentsException(s);
		} catch (TooManyArgumentsException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testGetString(){
		try{
			argp.parse("7 5 2 43");
		} catch(TooManyArgumentsException e){
			String a = e.toString();
			String b = "TooManyArgumentsException Unrecognised arguments: 43";
			assertEquals(b,a);
		}
	}
	
	@Test
	public void testGetName(){
		try{
			argp.parse("7 5 2 43");
		} catch(TooManyArgumentsException e){
			String a = e.getUnexpectedArgument();
			String b = "43";
			assertEquals(b,a);
		}
	}
}
