package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class LoadXMLTest{
	
	@Test
	public void testLoadXMLFile() {
		ArgumentParser argp = new ArgumentParser();
		argp.loadXML("test.xml");
		assertEquals("red",argp.getArgument("color").getValue());
	}
	
	
	
}
