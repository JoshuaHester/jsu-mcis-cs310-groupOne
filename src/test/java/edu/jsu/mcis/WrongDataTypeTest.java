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
		
		try {
			throw new WrongDataType(arg,s);
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
		try{
			throw new WrongDataType(arg,s);
		} catch(WrongDataType e){
			String a = e.toString();
			String b = "argument width: invalid float value: dog";
			assertEquals(b,a);
		}
	}
	
	@Test
	public void testGetName(){
	ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		try{
			throw new WrongDataType(arg,s);
		} catch(WrongDataType e){
			String a = e.getName();
			String b = "width";
			assertEquals(b,a);
		}
	}
	
	@Test
	public void testGetValue(){
	ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		try{
			throw new WrongDataType(arg,s);
		} catch(WrongDataType e){
			String a = e.getValue();
			String b = "dog";
			assertEquals(b,a);
		}
	}
	
	@Test
	public void testGetType(){
	ArgumentParser argp = new ArgumentParser();
		String s = "dog";
		ArgumentValues arg= new ArgumentValues(ArgumentValues.Types.FLOAT,"width");
		try{
			throw new WrongDataType(arg,s);
		} catch(WrongDataType e){
			String a = e.getType();
			String b = "float";
			assertEquals(b,a);
		}
	}
}