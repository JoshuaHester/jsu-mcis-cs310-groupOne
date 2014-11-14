package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class InvalidOptionalArgumentExceptionTest{
	private ArgumentParser argp;
	private String s;

	@Before
	public void setUpMyTest(){
		argp = new ArgumentParser();
		s = "height";
		argp.addArgument("length");
		argp.addArgument("width");
		argp.addArgument("height");
		argp.addOptionalArgument(Argument.DataType.STRING, "type", "box");
	}
	
	@Test
	public void testInvalidOptionalArgument() {
		boolean thrown = false;
		argp.parse("0 0 0 --type spherical");
		try {
			throw new InvalidOptionalArgumentException(s);
		} catch (InvalidOptionalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
}
