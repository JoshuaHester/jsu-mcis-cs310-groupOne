package edu.jsu.mcis;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.net.URL;

public class LoadXMLTest{
	
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
	
	@Test
	public void testFlags(){
		LoadXML loader = new LoadXML("xmlFiles/flagtest.xml");
		assertEquals(false,loader.getArgument("hollow").getValue());
	
	}
	
	@Test
	public void testSizePos2() {
		LoadXML loader = new LoadXML("xmlFiles/flagtest.xml");
		assertEquals(3,loader.getNumPosArguments());
	}
	
	
}

