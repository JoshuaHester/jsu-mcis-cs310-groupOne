package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class WrongDataTypeTest{
	
	@Test
	public void testWrongDataType() {
		boolean thrown = false;
		ArgumentParser argp = new ArgumentParser();
		String s = "VolCal 7 5 dog";
		
		try {
			throw new WrongDataType(s);
		} catch (WrongDataType e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
}