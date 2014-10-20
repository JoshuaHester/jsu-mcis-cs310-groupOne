package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class WrongDataTypeTest{
	
	@Test
	public void testWrongDataType() {
		boolean thrown = false;
		ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String a = argp.usageOutput();
		
		try {
			throw new WrongDataType(arg,s, a);
		} catch (WrongDataType e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testGetString(){
	ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String a = argp.usageOutput();
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
	ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String a = argp.usageOutput();
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
	ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String a = argp.usageOutput();
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
	ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.parse("VolCal 0 0 0");
		String a = argp.usageOutput();
		try{
			throw new WrongDataType(arg,s, a);
		} catch(WrongDataType e){
			String t = e.getType();
			String b = "float";
			assertEquals(b,t);
		}
	}
}