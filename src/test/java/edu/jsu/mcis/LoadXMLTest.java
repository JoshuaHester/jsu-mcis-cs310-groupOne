package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class LoadXMLTest{
	private String xmlFile = "C:/Users/Joshua/Documents/GitHub/jsu-mcis-cs310-groupOne/testPrograms/test.xml";
	
	@Before
	public void setFileName() {
		String xmlFile = "C:/Users/Joshua/Documents/GitHub/jsu-mcis-cs310-groupOne/testPrograms/test.xml";
	}
	
	@Test
	public void testLoadXMLFile() {
		LoadXML loader = new LoadXML(xmlFile);
		assertEquals(5,loader.getnumargs());
	}
	
	
	
}
