package edu.jsu.mcis;
import java.util.*;
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
		assertEquals(5,loader.getNumArgs());
	}
	
	@Test
	public void testGetFirstValue() {
		LoadXML loader = new LoadXML(xmlFile);
		assertEquals("float",loader.getArgument("length").getType());
	}
	
		@Test
	public void testGetLastValue() {
		LoadXML loader = new LoadXML(xmlFile);
		assertEquals("red",loader.getArgument("color").getValue());
	}
	
	@Test
	public void testSizePos() {
		LoadXML loader = new LoadXML(xmlFile);
		assertEquals(3,loader.getNumPosArguments());
	}
	
	@Test
	public void testSizeOpt() {
		LoadXML loader = new LoadXML(xmlFile);
		assertEquals(2,loader.getNumOptArguments());
	}
	
	@Test
	public void testGetLists() {
		
		LoadXML loader = new LoadXML(xmlFile);
		
		
		assertEquals(2,loader.getOptArgs().size());
	}
	
	
}
