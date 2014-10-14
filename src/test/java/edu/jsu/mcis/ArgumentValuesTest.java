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
	
	/* These tests are made to implement all data types 
	 * and store them in ArgumentValues. 
	*/
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
		v = new ArgumentValues(ArgumentValues.Types.BOOLEAN, "true");
		v.setValue("true");
		assertEquals(true,v.getValue());
	}
	@Test
	public void testStringValueArgument(){
		v = new ArgumentValues(ArgumentValues.Types.STRING, "pet");
		v.setValue("dog");
		assertEquals("dog",v.getValue());
	}
}