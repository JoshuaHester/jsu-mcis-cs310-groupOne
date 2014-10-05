package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;


public class ArgumentValuesTest{

	private ArgumentValues v;

	/*
	@Test
	public void testSetArgument(){
	v = new ArgumentValues("Some Argument");
	assertEquals("Some Argument",v.argument);
	}
	*/
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
	
	
}