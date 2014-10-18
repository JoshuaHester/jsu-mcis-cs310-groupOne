package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;


public class ArgumentValuesTest{

	private ArgumentValues v;
	@Test
		public void testGetArgument(){
		v = new ArgumentValues("Some Argument");
		assertEquals("Some Argument",v.getName());
	}
	
	@Test
	public void testSetValue(){
		v = new ArgumentValues("Some Argument");
		v.setValue("7");
		assertEquals("7",v.getValue());
	}
	
	@Test
	public void testGetValue(){
		v = new ArgumentValues("Some Argument");
		assertEquals("",v.getValue());
	}
	
	@Test
	public void testMakeArgumentWithDescription(){
		v = new ArgumentValues("pet", "Best Friend");
		v.setValue("dog");
		assertEquals("dog",v.getValue());
		assertEquals("Best Friend",v.getDescription());
		assertEquals("pet",v.getName());
	}
	
	@Test
	public void testIntValueArgument(){
		v = new ArgumentValues(ArgumentValues.Types.INT, "length");
		v.setValue("5");
		assertEquals(5,v.getValue());
	}
	@Test
	public void testFloatValueArgument(){
		v = new ArgumentValues(ArgumentValues.Types.FLOAT, "approximate");
		v.setValue("3.2");
		assertEquals(3.2f,v.getValue());
	}
	@Test
	public void testBooleanValueArgument(){
		v = new ArgumentValues(ArgumentValues.Types.BOOLEAN, "car");
		v.setValue("true");
		assertEquals(true,v.getValue());
	}
	@Test
	public void testBooleanValueArgumentFalse(){
		v = new ArgumentValues(ArgumentValues.Types.BOOLEAN, "car");
		v.setValue("false");
		assertEquals(false,v.getValue());
	}
	
	@Test
	public void testStringValueArgument(){
		v = new ArgumentValues(ArgumentValues.Types.STRING, "pet");
		v.setValue("dog");
		assertEquals("dog",v.getValue());
	}
	
	@Test
	public void testStringTypeArgument(){
		v = new ArgumentValues(ArgumentValues.Types.STRING, "pet");
		assertEquals("String",v.getType());
	}
	
	@Test
	public void testIntTypeArgument(){
		v = new ArgumentValues(ArgumentValues.Types.INT, "pet");
		assertEquals("int",v.getType());
	}
	
	@Test
	public void testFloatTypeArgument(){
		v = new ArgumentValues(ArgumentValues.Types.FLOAT, "pet");
		assertEquals("float",v.getType());
	}
	
	@Test
	public void testBoolTypeArgument(){
		v = new ArgumentValues(ArgumentValues.Types.BOOLEAN, "pet");
		assertEquals("boolean",v.getType());
	}
	
}