package edu.jsu.mcis;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.net.URL;

public class XMLToolsTest{
	private ArgumentParser p;
	
	@Before
	public void setup() {
		p = XMLTools.loadParser("xmlFiles/test.xml");
	}
	
	@Test
	public void testLoadXMLFile() {
		assertEquals(5, p.getNumArguments());
	}
	/*
	@Test
	public void testGetFirstValue() {
		assertEquals("float", p.getArgument("length").getType());
	}
	
		@Test
	public void testGetLastValue() {
		assertEquals("red", p.getArgument("color").getValue());
	}
	
	@Test
	public void testSizePos() {
		assertEquals(3,loader.getNumPosArguments());
	}
	
	@Test
	public void testSizeOpt() {
		assertEquals(2,loader.getNumOptArguments());
	}
	
	@Test
	public void testGetLists() {
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
	*/
	
}

