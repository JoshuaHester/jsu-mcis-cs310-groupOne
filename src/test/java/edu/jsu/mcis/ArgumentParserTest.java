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
	public void getUsageTest(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String s=argp.getUsage();
		String a="VolCal/n positional arguments:/n length/n width/n height/n ";
		assertEquals(a,s);
	}
	
	
	
	
	
	
	
	
}