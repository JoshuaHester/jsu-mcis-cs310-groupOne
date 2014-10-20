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
		String a="VolCal\n positional arguments:\n length\n width\n height";
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
		String a="VolCal\n positional arguments:\n length The length of the object\n width The width of the object\n height The height of the object";
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
	public void testTooManyArguments(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		try{
			argp.parse("VolCal 7 5 2 43");
			assert false;
		} catch (TooManyArguments e){
		assert true; 
		}
	}
	
	@Test
	public void testTooManyArgumentsTwo(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		try{
			argp.parse("VolCal 7 5 2 43 8");
			assert false;
		} catch (TooManyArguments e){
		assert true; 
		}
	}
	
	@Test
	public void testTooFewArguments(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		try{
			argp.parse("VolCal 7 5");
			assert false;
		} catch (TooFewArguments e){
		assert true; 
		}
	}
	
	@Test
	public void testTooFewArgumentsTwo(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height"); 
		try{
			argp.parse("VolCal 7");
			assert false;
		} catch (TooFewArguments e){
		assert true; 
		}
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
		assertEquals(5.5f, parser.getArgument("length").getValue());
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
	
	@Test
	public void testTypeUsageWithDescription(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument(ArgumentValues.Types.FLOAT, "length", "The length of the object");
		argp.addArgument(ArgumentValues.Types.FLOAT, "width", "The width of the object");
		argp.addArgument(ArgumentValues.Types.FLOAT, "height", "The height of the object");
		argp.parse("VolCal -h");
		String s=argp.getUsage();
		String a="VolCal\n positional arguments:\n length The length of the object\n width The width of the object\n height The height of the object";
		assertEquals(a,s);
	}
	
	@Test
	public void testIncorrectBooleanDataType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(ArgumentValues.Types.BOOLEAN, "pet");
		try{
			parser.parse("SomeProgramName 50");
			assert false;
		} catch (WrongDataType e){
			assert true;
		}
	}
	
	@Test
	public void testIncorrectFloatDataType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(ArgumentValues.Types.FLOAT, "pet");
		try{
			parser.parse("SomeProgramName dog");
			assert false;
		} catch (WrongDataType e){
			assert true;
		}
	}
	
	@Test
	public void testIncorrectIntDataType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(ArgumentValues.Types.INT, "pet");
		try{
			parser.parse("SomeProgramName true");
			assert false;
		} catch (WrongDataType e){
			assert true;
		}
	}
	

	@Test
	public void testUsageOutput(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String a="VolCal usage: length width height";
		assertEquals(argp.usageOutput(),a);

	}


	@Test 
	public void testParseOptionalArgumentsOne(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(ArgumentValues.Types.FLOAT, "length");
		argp.addArgument(ArgumentValues.Types.FLOAT, "width");
		argp.addArgument(ArgumentValues.Types.FLOAT, "height"); 
		argp.addOptionalArgument(ArgumentValues.Types.STRING, "type");
		argp.parse("VolCal 7 4 3 --type sphere");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
	}

	@Test 
	public void testParseOptionalArgumentsTwo(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(ArgumentValues.Types.FLOAT, "length");
		argp.addArgument(ArgumentValues.Types.FLOAT, "width");
		argp.addArgument(ArgumentValues.Types.FLOAT, "height"); 
		argp.addOptionalArgument(ArgumentValues.Types.STRING, "type");
		argp.addOptionalArgument(ArgumentValues.Types.STRING, "color");
		argp.parse("VolCal 7 4 3 --type sphere --color red");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}
		
	@Test 
	public void testParseOptionalArgumentsAnyOrder(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(ArgumentValues.Types.FLOAT, "length");
		argp.addArgument(ArgumentValues.Types.FLOAT, "width");
		argp.addArgument(ArgumentValues.Types.FLOAT, "height"); 
		argp.addOptionalArgument(ArgumentValues.Types.STRING, "type");
		argp.addOptionalArgument(ArgumentValues.Types.STRING, "color");
		argp.parse("VolCal 7 --type sphere 4 --color red 3");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}

	@Test 
	public void testParseOptionalArgumentsWithDescriptions(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(ArgumentValues.Types.FLOAT, "length", "The length of the object");
		argp.addArgument(ArgumentValues.Types.FLOAT, "width", "The width of the object");
		argp.addArgument(ArgumentValues.Types.FLOAT, "height", "The height of the object");
		argp.addOptionalArgument(ArgumentValues.Types.STRING, "type", "The type of object");
		argp.addOptionalArgument(ArgumentValues.Types.STRING, "color", "The color of the object");
		argp.parse("VolCal 7 4 3 --type sphere --color red");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}
	
}