package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class WrongDataTypeTest{
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
		argp.parse("VolCal 0 0 0");
		arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		s = "dog";
		a = argp.usageOutput();
	}
	
	@Test
	public void testWrongDataType() {
		boolean thrown = false;
	
		try {
			throw new WrongDataType(arg,s, a);
		} catch (WrongDataType e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testGetString(){

		try{
			throw new WrongDataType(arg,s,a);
		} catch(WrongDataType e){
			String n = e.toString();
			String b = "VolCal usage: length width height argument width: invalid float value: dog";
			assertEquals(b,n);
		}
	}
	
	@Test
	public void testGetName(){

		try{
			throw new WrongDataType(arg,s,a);
		} catch(WrongDataType e){
			String n = e.getName();
			String b = "width";
			assertEquals(b,n);
		}
	}
	
	@Test
	public void testGetValue(){

		try{
			throw new WrongDataType(arg,s,a);
		} catch(WrongDataType e){
			String v = e.getValue();
			String b = "dog";
			assertEquals(b,v);
		}
	}
	
	@Test
	public void testGetType(){

		try{
			throw new WrongDataType(arg,s, a);
		} catch(WrongDataType e){
			String t = e.getType();
			String b = "float";
			assertEquals(b,t);
		}
	}
}