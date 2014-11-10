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
		parser.parse("red");
		assertEquals("red", parser.getArgument("color").getValue());
	}
	
	@Test
	public void testNonExistantArgumentShouldBeEmptyString() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument("pet");
		parser.parse("dog");
		assertEquals("", parser.getArgument("animal").getValue());
	}
	
	@Test
	public void testGetUsage(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("0 0 0");
		String s=argp.getUsage();
		String a="edu.jsu.mcis.ArgumentParserTest\n positional arguments:\n length\n width\n height";
		assertEquals(a,s);
	}
	
	@Test
	public void testGetUsageWithDescription(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument("length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument("width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument("height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.parse("0 0 0");
		String s=argp.getUsage();
		String a="edu.jsu.mcis.ArgumentParserTest\n positional arguments:\n length The length of the object\n width The width of the object\n height The height of the object";
		assertEquals(a,s);
	}
	
	@Test
	public void testDashHFunction(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.setHelpFlagExits(false);
		argp.parse("-h");
		String s=argp.getUsage();
		String a="edu.jsu.mcis.ArgumentParserTest\n positional arguments:\n length\n width\n height";
		assertEquals(a,s);
	}
	
	@Test 
	public void testTooManyArguments(){
		ArgumentParser argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		try{
			argp.parse("7 5 2 43");
			assert false;
		} catch (TooManyArgumentsException e){
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
			argp.parse("7 5 2 43 8");
			assert false;
		} catch (TooManyArgumentsException e){
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
			argp.parse("7 5");
			assert false;
		} catch (TooFewArgumentsException e){
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
			argp.parse("7");
			assert false;
		} catch (TooFewArgumentsException e){
		assert true; 
		}
	}

	@Test
	public void testAddIntArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(Argument.DataType.INT, "length");
		parser.parse("5");
		assertEquals(5, parser.getArgument("length").getValue());
	}
	
	@Test
	public void testAddFloatArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(Argument.DataType.FLOAT, "length");
		parser.parse("5.5");
		assertEquals(5.5f, parser.getArgument("length").getValue());
	}
	
	@Test
	public void testAddBooleanArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(Argument.DataType.BOOLEAN, "Rainy");
		parser.parse("true");
		assertEquals(true, parser.getArgument("Rainy").getValue());
	}
	
	@Test
	public void testAddStringArgument() {
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(Argument.DataType.STRING, "pet");
		parser.parse("dog");
		assertEquals("dog", parser.getArgument("pet").getValue());
	}
	
	@Test
	public void testTypeUsageWithDef(){
		ArgumentParser argp= new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument(Argument.DataType.FLOAT, "height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.setHelpFlagExits(false);
		argp.parse("-h");
		String s=argp.getUsage();
		String a="edu.jsu.mcis.ArgumentParserTest\n positional arguments:\n length The length of the object\n width The width of the object\n height The height of the object";
		assertEquals(a,s);
	}
	
	@Test
	public void testIncorrectBooleanDataType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(Argument.DataType.BOOLEAN, "pet");
		try{
			parser.parse("50");
			assert false;
		} catch (InvalidDataTypeException e){
			assert true;
		}
	}
	
	@Test
	public void testIncorrectFloatDataType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(Argument.DataType.FLOAT, "pet");
		try{
			parser.parse("dog");
			assert false;
		} catch (InvalidDataTypeException e){
			assert true;
		}
	}
	
	@Test
	public void testIncorrectIntDataType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument(Argument.DataType.INT, "pet");
		try{
			parser.parse("true");
			assert false;
		} catch (InvalidDataTypeException e){
			assert true;
		}
	}
	
	@Test
	public void testNameBeforeType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument("height", Argument.DataType.FLOAT);
		parser.parse("3.14");
		assertEquals(3.14f, parser.getArgument("height").getValue());
		assertEquals("float", parser.getArgument("height").getType());
	}
	
	@Test
	public void testNameBeforeTypeWithDesc(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument("height", Argument.DataType.FLOAT);
		parser.getArgument("height").setDescription("Lorem ipsum dolor sit amet");
		parser.parse("3.14");
		assertEquals(3.14f, parser.getArgument("height").getValue());
		assertEquals("float", parser.getArgument("height").getType());
	}
	
	@Test
	public void testOptNameBeforeType(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument("height", Argument.DataType.FLOAT);
		parser.getArgument("height").setDescription("Lorem ipsum dolor sit amet");
		parser.addOptionalArgument("type", Argument.DataType.INT);
		parser.parse("3.14 --type 5");
		assertEquals(5, parser.getArgument("type").getValue());
		assertEquals("int", parser.getArgument("type").getType());
	}
	
	@Test
	public void testOptNameBeforeTypeWithDesc(){
		ArgumentParser parser=new ArgumentParser();
		parser.addArgument("height", Argument.DataType.FLOAT);
		parser.getArgument("height").setDescription("Lorem ipsum dolor sit amet");
		parser.addOptionalArgument("type", Argument.DataType.INT, "1");
		parser.getArgument("type").setDescription("Lorem ipsum dolor sit amet");
		parser.parse("3 --type 5");
		assertEquals(5, parser.getArgument("type").getValue());
		assertEquals("int", parser.getArgument("type").getType());
	}

	@Test 
	public void testParseOptionalArgumentsOne(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type");
		argp.parse("7 4 3 --type sphere");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
	}

	@Test 
	public void testParseOptionalArgumentsTwo(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type");
		argp.addOptionalArgument(Argument.DataType.STRING, "color");
		argp.parse("7 4 3 --type sphere --color red");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}
		
	@Test 
	public void testParseOptionalArgumentsAnyOrder(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type");
		argp.addOptionalArgument(Argument.DataType.STRING, "color");
		argp.parse("7 --type sphere 4 --color red 3");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}

	@Test 
	public void testParseOptionalArgumentsWithDescriptions(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument(Argument.DataType.FLOAT, "height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.addOptionalArgument(Argument.DataType.STRING, "type", "0");
		argp.getArgument("type").setDescription("The type of object");
		argp.addOptionalArgument(Argument.DataType.STRING, "color", "0");
		argp.getArgument("color").setDescription("The color of the object");
		argp.parse("7 4 3 --type sphere --color red");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseTwoOptionalBooleanArguments(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.BOOLEAN, "type");
		argp.addOptionalArgument(Argument.DataType.BOOLEAN, "color");
		argp.parse("7 4 5 --type --color");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(5.0f, argp.getArgument("height").getValue());
		assertEquals(true, argp.getArgument("type").getValue());
		assertEquals(true, argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseBooleanOptionalArgumentsOne(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.BOOLEAN, "type");
		argp.parse("7 4 3 --type");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(true, argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testParseBooleanOptionalArgumentsAnyOrder(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.BOOLEAN, "type");
		argp.addOptionalArgument(Argument.DataType.BOOLEAN, "color");
		argp.parse("7 --type 4 3");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(true, argp.getArgument("type").getValue());
		assertEquals(false, argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseBooleanOptionalArgumentsWithDescriptions(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument(Argument.DataType.FLOAT, "height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.addOptionalArgument(Argument.DataType.BOOLEAN, "type", "false");
		argp.getArgument("type").setDescription("The type of object");
		argp.addOptionalArgument(Argument.DataType.BOOLEAN, "color", "false");
		argp.getArgument("color").setDescription("The color of the object");
		argp.parse("7 4 3 --type");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(true, argp.getArgument("type").getValue());
		assertEquals(false, argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseIntOptionalArgumentsWithDescriptions(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument(Argument.DataType.FLOAT, "height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.addOptionalArgument(Argument.DataType.INT, "type", "0");
		argp.getArgument("type").setDescription("The type of object");
		argp.addOptionalArgument(Argument.DataType.INT, "color", "0");
		argp.getArgument("color").setDescription("The color of the object");
		argp.parse("7 4 3 --type 1 --color 2");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(1, argp.getArgument("type").getValue());
		assertEquals(2, argp.getArgument("color").getValue());
	}

	@Test 
	public void testParseIntOptionalArgumentsOne(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.INT, "type");
		argp.parse("7 4 3 --type 2");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(2, argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testParseIntOptionalArgumentsAnyOrder(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.INT, "type");
		argp.addOptionalArgument(Argument.DataType.INT, "color");
		argp.parse("7 --type 4 --color 3 4 3");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(4, argp.getArgument("type").getValue());
		assertEquals(3, argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseOptionalArgumentsHelp(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type");
		argp.parse("7 4 3 --type sphere");
		String s=argp.getUsage();
		String a="edu.jsu.mcis.ArgumentParserTest\n positional arguments:\n length\n width\n height\n --type";
		assertEquals(a,s);
	}
	
	@Test 
	public void testParseOptionalArgumentsHelpWithDesc(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument(Argument.DataType.FLOAT, "height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.addOptionalArgument(Argument.DataType.STRING, "type", "0");
		argp.getArgument("type").setDescription("The type of object");
		argp.addOptionalArgument(Argument.DataType.STRING, "color", "0");
		argp.getArgument("color").setDescription("The color of the object");
		argp.parse("7 4 3 --type sphere --color red");
		String s=argp.getUsage();
		String a="edu.jsu.mcis.ArgumentParserTest\n positional arguments:\n length The length of the object\n width The width of the object\n height The height of the object\n --type The type of object\n --color The color of the object";
		assertEquals(a,s);
	}
	
	
	@Test 
	public void testOptionalArgumentWithDefaultValueUnchanged(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.parse("Chevy");
		assertEquals("box",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testOptionalArgumentWithDefaultValueChanged(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.parse("Chevy --type car");
		assertEquals("car",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testOptionalArgumentWithDefaultValueUnchangedWithDesc(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.parse("Chevy");
		assertEquals("box",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testOptionalArgumentWithDefaultValueChangedWithDesc(){
		ArgumentParser argp=new ArgumentParser();
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.parse("Chevy --type car");
		assertEquals("car",argp.getArgument("type").getValue());
	}
	
	//@Test 
	public void testXML(){
		ArgumentParser argp=new ArgumentParser();
		String xmlFile = "C:/Users/Joshua/Documents/GitHub/jsu-mcis-cs310-groupOne/testPrograms/test.xml";
		argp.loadXML(xmlFile);
		assertEquals("box",argp.getArgument("type").getValue());
	}
	
	
}
