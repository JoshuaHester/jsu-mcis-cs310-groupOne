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
	
	@Test
	public void testNonExistantArgumentShouldBeEmptyString() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument("pet");
		parser.parse("SomeProgramName dog");
		assertEquals("", parser.getArgument("animal").getValue());
	}
	
	@Test
	public void testGetUsage(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String s=argp.getUsage();
		String a="VolCal/n positional arguments:/n length/n width/n height";
		assertEquals(a,s);
	}
	
		@Test
		
	public void testGetUsageWithDescription(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length", "The length of the object");
		argp.addArgument("width", "The width of the object");
		argp.addArgument("height", "The height of the object");
		argp.parse("VolCal 0 0 0");
		String s=argp.getUsage();
		String a="VolCal/n positional arguments:/n length The length of the object/n width The width of the object/n height The height of the object";
		assertEquals(a,s);
	}
	
	@Test
	public void testDashHFunction(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("CalVol -h");
		assertEquals(true, argp.getHelpOut());
	}
	
	
	
	
	
}