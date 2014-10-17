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
}