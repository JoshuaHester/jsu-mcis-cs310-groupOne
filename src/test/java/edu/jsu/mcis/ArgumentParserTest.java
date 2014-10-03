package edu.jsu.mcis;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest{

	@Test
	public void testAddArgument(){
		ArgumentParser parser = new ArgumentParser();
		assertEquals(0, parser.getNumArguments());
		parser.addArgument("color");
		assertEquals(1, parser.getNumArguments());
	}
	
	@Test
	public void testParseSingleArgument() {
		ArgumentParser parser = new ArgumentParser();
		parser.addArgument("color");
		parser.parse("SomeProgramName red");
		assertEquals("red", parser.getArgument("color").getValue());
	}
	
	/*@Test
	public void getUsageTest(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("Volcal");
		String s=argp.getUsage();
		String a = "Vol/n positional arguments:/n length/n width/n height/n";
		
		assertEquals(a,s);
	}*/
	
	
	
	
	
	
	
	
}