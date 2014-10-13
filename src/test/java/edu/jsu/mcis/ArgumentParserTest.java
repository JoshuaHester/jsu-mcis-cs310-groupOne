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

	@Test 
	// If their are more values than arguments, exit the program.
	public void testTooManyArguments(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		argp.parse("VolCal 7 5 2 43");
		assertEquals("43",argp.getUnknownValue());
	}
	
	@Test
	// If their are more arguments than values, exit the program
	public void testTooLessArguments(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		argp.parse("VolCal 7 5");
		assertEquals("height",argp.getUnknownArg());
	}

	@Test 
	public void testTooManyArgumentsTwo(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		argp.parse("VolCal 7 5 2 43 57");
		assertEquals("43 57",argp.getUnknownValue());
	}
	
	@Test
	public void testTooLessArgumentsTwo(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		argp.parse("VolCal 7");
		assertEquals("width height",argp.getUnknownArg());
	}
	
		@Test 
	public void testTooManyArgumentsBool(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		argp.parse("VolCal 7 5 2 43");
		assertEquals(true,argp.checkTooManyArg());
	}
	
	@Test
	public void testTooLessArgumentsBool(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		argp.parse("VolCal 7 5");
		assertEquals(true,argp.checkTooFewArg());
	}
	
	@Test
	public void testMissingArgumentsMessage(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 7 5");
		String s=argp.missingArguments();
		String a="VolCal/n positional arguments:/n length/n width/n height/n error: the following arguments are required: height";
		assertEquals(a,s);
	}
	
	@Test
	public void testtooManyArgumentsMessage(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 7 5 2 43");
		String s=argp.tooManyArguments();
		String a="VolCal/n positional arguments:/n length/n width/n height/n error: unrecognised arguments: 43";
		assertEquals(a,s);
	}
	
	@Test
	public void testAddIntArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(ArgumentValues.Types.INT, "length");
		parser.parse("SomeProgramName 5");
		assertEquals(5, parser.getArgument("length").getValue());
	}
	
	@Test
	public void testAddFloatArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(ArgumentValues.Types.FLOAT, "length");
		parser.parse("SomeProgramName 5.5");
		assertEquals(5.5, parser.getArgument("length").getValue());
	}
	
	@Test
	public void testAddBooleanArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(ArgumentValues.Types.BOOLEAN, "Rainy");
		parser.parse("SomeProgramName true");
		assertEquals(true, parser.getArgument("Rainy").getValue());
	}
	
	@Test
	public void testAddStringArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(ArgumentValues.Types.STRING, "pet");
		parser.parse("SomeProgramName dog");
		assertEquals("dog", parser.getArgument("pet").getValue());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}