package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class InvalidDataTypeExceptionTest{
	private ArgumentParser argp;
	private ArgumentValues arg;
	private String s;
	private String a;

	@Before
	public void setUpMyTest(){
		argp = new ArgumentParser();
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("0 0 0");
		arg= new ArgumentValues(DataType.FLOAT,"width");
		s = "dog";
	}
	
	@Test
	public void testWrongDataType() {
		boolean thrown = false;
	
		try {
			throw new InvalidDataTypeException(arg,s);
		} catch (InvalidDataTypeException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testGetString(){

		try{
			throw new InvalidDataTypeException(arg,s);
		} catch(InvalidDataTypeException e){
			String n = e.toString();
			String b = "InvalidDataTypeException argument width: invalid float value: dog";
			assertEquals(b,n);
		}
	}
	
	@Test
	public void testGetName(){

		try{
			throw new InvalidDataTypeException(arg,s);
		} catch(InvalidDataTypeException e){
			String n = e.getName();
			String b = "width";
			assertEquals(b,n);
		}
	}
	
	@Test
	public void testGetValue(){

		try{
			throw new InvalidDataTypeException(arg,s);
		} catch(InvalidDataTypeException e){
			String v = e.getValue();
			String b = "dog";
			assertEquals(b,v);
		}
	}
	
	@Test
	public void testGetType(){

		try{
			throw new InvalidDataTypeException(arg,s);
		} catch(InvalidDataTypeException e){
			String t = e.getType();
			String b = "float";
			assertEquals(b,t);
		}
	}
}
