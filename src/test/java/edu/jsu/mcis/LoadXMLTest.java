/*
package edu.jsu.mcis;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.net.URL;

public class LoadXMLTest{
	private String xmlFile;
	/*
	@Before
	public void setFileName() { 
	
		String xmlFile = "../../../../../../../../../Documents/GitHub/jsu-mcis-cs310-groupOne/test.xml";

		URL location = Test.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
	}*/
	
	@Test
	public void testLoadXMLFile() {
		LoadXML loader = new LoadXML("xmlFiles/test.xml");  
		assertEquals(5,loader.getNumArguments());
	}
	
	@Test
	public void testGetFirstValue() {
		LoadXML loader = new LoadXML("xmlFiles/test.xml");
		assertEquals("float",loader.getArgument("length").getType());
	}
	
		@Test
	public void testGetLastValue() {
		LoadXML loader = new LoadXML("xmlFiles/test.xml");
		assertEquals("red",loader.getArgument("color").getValue());
	}
	
	@Test
	public void testSizePos() {
		LoadXML loader = new LoadXML("xmlFiles/test.xml");
		assertEquals(3,loader.getNumPosArguments());
	}
	
	@Test
	public void testSizeOpt() {
		LoadXML loader = new LoadXML("xmlFiles/test.xml");
		assertEquals(2,loader.getNumOptArguments());
	}
	
	@Test
	public void testGetLists() {
		
		LoadXML loader = new LoadXML("xmlFiles/test.xml");
		assertEquals(5,loader.getNumArguments());
	}
	
	
}
*/
