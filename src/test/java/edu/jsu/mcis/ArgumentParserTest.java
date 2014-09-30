package edu.jsu.mcis;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest{

	@Test
	public void testGetProgramName(){
		ArgumentParser test = new ArgumentParser("VolumeCalculator 3 4 2");
		assertEquals("VolumeCalculator",test.getProgramName());
	}
	
	@Test
	public void testGetLength(){
		ArgumentParser test = new ArgumentParser("VolumeCalculator 3 4 2");
		assertEquals("3",test.getLength());
	}
	
	@Test
	public void testGetWidth(){
		ArgumentParser test = new ArgumentParser("VolumeCalculator 3 4 2");
		assertEquals("4",test.getWidth());
	}

	@Test
	public void testGetHeight(){
		ArgumentParser test = new ArgumentParser("VolumeCalculator 3 4 2");
		assertEquals("2",test.getHeight());
	}









}