package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;


public class ArgumentValuesTest{

	@Test
	public void testSetValues(){
		ArugmentValues v = new ArgumentValues();
		
		assertEquals("VolumeCalculator",v.setProgramName("VolumeCalculator"));
		assertEquals("2",v.setLength("2"));
		assertEquals("4",v.setWidth("4"));
		assertEquals("7",v.setHeight("7"));
	}

}