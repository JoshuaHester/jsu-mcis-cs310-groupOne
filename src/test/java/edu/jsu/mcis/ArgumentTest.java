package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;


public class ArgumentTest{

	private Argument v;
	
	@Test
		public void testGetArgument(){
		v = new Argument("Some Argument");
		assertEquals("Some Argument",v.getName());
	}
	
	@Test
	public void testSetValue(){
		v = new Argument("Some Argument");
		v.setValue("7");
		assertEquals("7",v.getValue());
	}
	
	@Test
	public void testGetValue(){
		v = new Argument("Some Argument");
		assertEquals("",v.getValue());
	}
	
	@Test
	public void testMakeArgumentWithDescription(){
		v = new Argument("pet");
		v.setValue("dog");
		v.setDescription("Best Friend");
		assertEquals("dog",v.getValue());
		assertEquals("Best Friend",v.getDescription());
		assertEquals("pet",v.getName());
	}
	
	@Test
	public void testIntValueArgument(){
		v = new Argument(Argument.DataType.INT, "length");
		v.setValue("5");
		assertEquals(5,v.getValue());
	}
	
	@Test
	public void testFloatValueArgument(){
		v = new Argument(Argument.DataType.FLOAT, "approximate");
		v.setValue("3.2");
		assertEquals(3.2f,v.getValue());
	}
	
	@Test
	public void testBooleanValueArgument(){
		v = new Argument(Argument.DataType.BOOLEAN, "car");
		v.setValue("true");
		assertEquals(true,v.getValue());
	}
	
	@Test
	public void testBooleanValueArgumentFalse(){
		v = new Argument(Argument.DataType.BOOLEAN, "car");
		v.setValue("false");
		assertEquals(false,v.getValue());
	}
	
	@Test
	public void testStringValueArgument(){
		v = new Argument(Argument.DataType.STRING, "pet");
		v.setValue("dog");
		assertEquals("dog",v.getValue());
	}
	
	@Test
	public void testStringTypeArgument(){
		v = new Argument(Argument.DataType.STRING, "pet");
		assertEquals("String",v.getType());
	}
	
	@Test
	public void testIntTypeArgument(){
		v = new Argument(Argument.DataType.INT, "pet");
		assertEquals("int",v.getType());
	}
	
	@Test
	public void testFloatTypeArgument(){
		v = new Argument(Argument.DataType.FLOAT, "pet");
		assertEquals("float",v.getType());
	}
	
	@Test
	public void testBoolTypeArgument(){
		v = new Argument(Argument.DataType.BOOLEAN, "pet");
		assertEquals("boolean",v.getType());
	}
}
