package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import impl.ClassField;

public class TestClassField {
	private ClassField _field;
	
	@Before
	public void setup() {
		_field = null;
	}
	
	@Test
	public void TestGetFieldName() {
		String expected = "_manager";
		_field = new ClassField(expected, "-", "null", "String");
		assertEquals(expected, _field.getFieldName());
	}
	
	@Test
	public void TestGetAccessLevel() {
		String expected = "#";
		_field = new ClassField("name", expected, "null", "String");
		assertEquals(expected, _field.getAccessLevel());
	}
	
	@Test
	public void TestGetSignature() {
		String expected = "\\<String\\>";
		_field = new ClassField("name", "+", expected, "String");
		assertEquals(expected, _field.getSignature());		
	}
	
	@Test
	public void TestGetType() {
		String expected = "double";
		_field = new ClassField("name", "+", "\\<String\\>", expected);
		assertEquals(expected, _field.getType());
	}
	
	@After
	public void tearDown() {
		_field = null;
	}
}
