package edu.jsu.mcis;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest{
	private ArgumentParser argp;
	
	@Before
	public void setUpMyTest(){
		argp = new ArgumentParser();
	}

	@Test
	public void testAddArgument(){
		
		assertEquals(0, argp.getNumArguments());
		argp.addArgument("color");
		assertEquals(1, argp.getNumArguments());
	}
	
	@Test
	public void testParseSingleArgument() {
		
		argp.addArgument("color");
		argp.parse("red");
		assertEquals("red", argp.getArgument("color").getValue());
	}
	
	@Test
	public void testNonExistantArgumentShouldThrowException() {

		argp.addArgument("pet");
		argp.parse("dog");
		try{
			argp.getArgument("duck");
			assert false;	
		}catch(InvalidArgumentException e){
			assert true;
		}
	}
	
	@Test
	public void testGetUsage(){
		
		argp.setProgramName("VolCal");
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("0 0 0");
		String s=argp.getUsage();
		String a="VolCal length width height\n\n length: \n width: \n height: ";
		assertEquals(a,s);
	}
	
	@Test
	public void testGetUsageWithDescription(){
		argp.setProgramName("VolCal");
		argp.addArgument("length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument("width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument("height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.parse("0 0 0");
		String s=argp.getUsage();
		String a="VolCal length width height\n\n length:  The length of the object.\n width:  The width of the object.\n height:  The height of the object.";
		assertEquals(a,s);
	}
	
	@Test
	public void testDashHFunction(){
		argp.setProgramName("VolCal");
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.setHelpFlagExits(false);
		argp.parse("-h");
		String s=argp.getUsage();
		String a="VolCal length width height\n\n length: \n width: \n height: ";
		assertEquals(a,s);
	}
	
	@Test 
	public void testTooManyArguments(){
		
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
		
		argp.addArgument(Argument.DataType.INT, "length");
		argp.parse("5");
		assertEquals(5, argp.getArgument("length").getValue());
	}
	
	@Test
	public void testAddFloatArgument() {
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.parse("5.5");
		assertEquals(5.5f, argp.getArgument("length").getValue());
	}
	
	@Test
	public void testAddBooleanArgument() {
		
		argp.addArgument(Argument.DataType.BOOLEAN, "Rainy");
		argp.parse("true");
		assertEquals(true, argp.getArgument("Rainy").getValue());
	}
	
	@Test
	public void testAddStringArgument() {
		
		argp.addArgument(Argument.DataType.STRING, "pet");
		argp.parse("dog");
		assertEquals("dog", argp.getArgument("pet").getValue());
	}
	
	@Test
	public void testTypeUsageWithDef(){
		argp.setProgramName("VolCal");
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.getArgument("length").setDescription("The length of the object");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.getArgument("width").setDescription("The width of the object");
		argp.addArgument(Argument.DataType.FLOAT, "height");
		argp.getArgument("height").setDescription("The height of the object");
		argp.setHelpFlagExits(false);
		argp.parse("-h");
		String s=argp.getUsage();
		String a="VolCal length width height\n\n length:  The length of the object.\n width:  The width of the object.\n height:  The height of the object.";
		assertEquals(a,s);
	}
	
	@Test
	public void testIncorrectBooleanDataType(){
		
		argp.addArgument(Argument.DataType.BOOLEAN, "pet");
		try{
			argp.parse("50");
			assert false;
		} catch (InvalidDataTypeException e){
			assert true;
		}
	}
	
	@Test
	public void testIncorrectFloatDataType(){
		
		argp.addArgument(Argument.DataType.FLOAT, "pet");
		try{
			argp.parse("dog");
			assert false;
		} catch (InvalidDataTypeException e){
			assert true;
		}
	}
	
	@Test
	public void testIncorrectIntDataType(){
		
		argp.addArgument(Argument.DataType.INT, "pet");
		try{
			argp.parse("true");
			assert false;
		} catch (InvalidDataTypeException e){
			assert true;
		}
	}
	
	@Test
	public void testNameBeforeType(){
		
		argp.addArgument("height", Argument.DataType.FLOAT);
		argp.parse("3.14");
		assertEquals(3.14f, argp.getArgument("height").getValue());
		assertEquals("float", argp.getArgument("height").getType());
	}
	
	@Test
	public void testNameBeforeTypeWithDesc(){
		
		argp.addArgument("height", Argument.DataType.FLOAT);
		argp.getArgument("height").setDescription("Lorem ipsum dolor sit amet");
		argp.parse("3.14");
		assertEquals(3.14f, argp.getArgument("height").getValue());
		assertEquals("float", argp.getArgument("height").getType());
	}
	
	@Test
	public void testOptNameBeforeType(){
		
		argp.addArgument("height", Argument.DataType.FLOAT);
		argp.getArgument("height").setDescription("Lorem ipsum dolor sit amet");
		argp.addOptionalArgument("type", Argument.DataType.INT, "0");
		argp.parse("3.14 --type 5");
		assertEquals(5, argp.getArgument("type").getValue());
		assertEquals("int", argp.getArgument("type").getType());
	}
	
	@Test
	public void testOptNameBeforeTypeWithDesc(){
		
		argp.addArgument("height", Argument.DataType.FLOAT);
		argp.getArgument("height").setDescription("Lorem ipsum dolor sit amet");
		argp.addOptionalArgument("type", Argument.DataType.INT, "1");
		argp.getArgument("type").setDescription("Lorem ipsum dolor sit amet");
		argp.parse("3 --type 5");
		assertEquals(5, argp.getArgument("type").getValue());
		assertEquals("int", argp.getArgument("type").getType());
	}

	@Test 
	public void testParseOptionalArgumentsOne(){
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type", "box");
		argp.parse("7 4 3 --type sphere");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
	}

	@Test 
	public void testParseOptionalArgumentsTwo(){
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type", "sphere");
		argp.addOptionalArgument(Argument.DataType.STRING, "color", "blue");
		argp.parse("7 4 3 --type sphere --color red");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}
		
	@Test 
	public void testParseOptionalArgumentsAnyOrder(){
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type", "box");
		argp.addOptionalArgument(Argument.DataType.STRING, "color", "blue");
		argp.parse("7 --type sphere 4 --color red 3");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals("sphere", argp.getArgument("type").getValue());
		assertEquals("red", argp.getArgument("color").getValue());
	}

	@Test 
	public void testParseOptionalArgumentsWithDescriptions(){
		
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
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addFlag("type");
		argp.addFlag("color");
		argp.parse("7 4 5 --type --color");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(5.0f, argp.getArgument("height").getValue());
		assertEquals(true, argp.getArgument("type").getValue());
		assertEquals(true, argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseBooleanOptionalArgumentsOne(){
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addFlag("type");
		argp.parse("7 4 3 --type");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(true, argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testParseBooleanOptionalArgumentsAnyOrder(){
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addFlag("type");
		argp.addFlag("color");
		argp.parse("7 --type 4 3");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(true, argp.getArgument("type").getValue());
		assertEquals(false, argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseBooleanOptionalArgumentsWithDescriptions(){
		
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
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.INT, "type", "0");
		argp.parse("7 4 3 --type 2");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(2, argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testParseIntOptionalArgumentsAnyOrder(){
		
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.INT, "type", "0");
		argp.addOptionalArgument(Argument.DataType.INT, "color", "0");
		argp.parse("7 --type 4 --color 3 4 3");
		assertEquals(7.0f, argp.getArgument("length").getValue());
		assertEquals(4.0f, argp.getArgument("width").getValue());
		assertEquals(3.0f, argp.getArgument("height").getValue());
		assertEquals(4, argp.getArgument("type").getValue());
		assertEquals(3, argp.getArgument("color").getValue());
	}
	
	@Test 
	public void testParseOptionalArgumentsHelp(){
		
		argp.setProgramName("VolCal");
		argp.addArgument(Argument.DataType.FLOAT, "length");
		argp.addArgument(Argument.DataType.FLOAT, "width");
		argp.addArgument(Argument.DataType.FLOAT, "height"); 
		argp.addOptionalArgument(Argument.DataType.STRING, "type", "box");
		argp.parse("7 4 3 --type sphere");
		String s=argp.getUsage();
		String a="VolCal length width height\n\n length: \n width: \n height: \n --type: ";
		assertEquals(a,s);
	}
	
	@Test 
	public void testParseOptionalArgumentsHelpWithDesc(){
		argp.setProgramName("VolCal");
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
		String a="VolCal length width height\n\n length:  The length of the object.\n width:  The width of the object.\n height:  The height of the object.\n --type:  The type of object.\n --color:  The color of the object.";
		assertEquals(a,s);
		
	}
	
	
	@Test 
	public void testOptionalArgumentWithDefaultValueUnchanged(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.parse("Chevy");
		assertEquals("box",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testOptionalArgumentWithDefaultValueChanged(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.parse("Chevy --type car");
		assertEquals("car",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testOptionalArgumentWithDefaultValueUnchangedWithDesc(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.parse("Chevy");
		assertEquals("box",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testSingleLetterOptionalArgumentWithDefaultValueUnchangedWithDesc(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","slow");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.getArgument("type").setShortName("t");
		argp.parse("Chevy -t fast");
		}
	
	@Test 
	public void testOptionalArgumentWithDefaultValueChangedWithDesc(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.parse("Chevy --type car");
		assertEquals("car",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testSingleLetterOptionalArgumentWithDefaultValueChanged(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.getArgument("type").setShortName("t");
		argp.parse("Chevy -t car");
		assertEquals("car",argp.getArgument("type").getValue());
	}
	
	@Test 
	public void testXML(){
		
		argp.loadXML("xmlFiles/test.xml");
		assertEquals("box",argp.getArgument("type").getValue());
	}
	
	@Test
	public void testShortName(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.getArgument("type").setShortName("t");
		argp.parse("Chevy -t car");
		assertEquals("car",argp.getArgumentByShortName("t").getValue());		
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testShortNameExceptionThrown(){
		
		argp.addArgument(Argument.DataType.STRING,"car");
		argp.addOptionalArgument(Argument.DataType.STRING,"type","box");
		argp.getArgument("type").setDescription("lorem ipsum");
		argp.getArgument("type").setShortName("t");
		argp.parse("Chevy -b car");
	}
	
	@Test
	public void testsetRestrictedIntegerValues(){
		
		int [] restrictedValues = {1,3,5,7,9};
		argp.setRestrictedValues(restrictedValues);
		for(int i  = 0; i < restrictedValues.length; i ++){
			assertTrue(argp.checkRestrictedValues(restrictedValues[i]));
		}
		assertFalse(argp.checkRestrictedValues(2));
		
	}
	
	@Test
	public void testsetRestrictedStringValues(){
		
		String [] restrictedValues = {"a","b","c"};
		argp.setRestrictedValues(restrictedValues);
		for(int i  = 0; i < restrictedValues.length; i ++){
			assertTrue(argp.checkRestrictedValues(restrictedValues[i]));
		}
		assertFalse(argp.checkRestrictedValues("e"));
		
	}
	
	@Test
	public void testsetRestrictedFloatValues(){
		
		float [] restrictedValues = {7.2f,3.6f,2.1f,1.3f};
		argp.setRestrictedValues(restrictedValues);
		for(int i  = 0; i < restrictedValues.length; i ++){
			assertTrue(argp.checkRestrictedValues(restrictedValues[i]));
		}
		assertFalse(argp.checkRestrictedValues(5.5f));
		
	}
	
	@Test
	public void testRequiredOptionalArguments(){
		
		assertEquals(0, argp.getNumArguments());
		argp.addRequiredArgument( "type", Argument.DataType.STRING, "box");
		assertEquals(1, argp.getNumArguments());
		assertEquals("box", argp.getArgument("type").getValue());
	}
	
	@Test
	public void testAddProgramName(){
		argp.setProgramName("VolCal");
		assertEquals("VolCal", argp.getProgramName());
	}
}
