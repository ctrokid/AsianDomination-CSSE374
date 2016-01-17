package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import impl.ClassField;
import impl.ClassMethod;
import impl.TargetClass;

public class TestTargetClass {
	private TargetClass _class;
	
	@Before
	public void setup() {
		_class = new TargetClass("problem.driver.Main");
	}
	
	@Test
	public void TestGetName() {
		String expected = "problem.driver.Main";
		assertEquals(expected, _class.getClassName());
	}
	
	@Test
	public void TestAddClassMethod() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ClassMethod method = new ClassMethod("helloWorld", "int, double", "+", "double");
		_class.addClassMethod(method);
		
		assertEquals(1, _class.getMethods().size());
		assertTrue(_class.getMethods().contains(method));
	}
	
	@Test
	public void TestGetClassMethods() {
		ClassMethod method = new ClassMethod("helloWorld", null, "#", "double");
		ClassMethod method2 = new ClassMethod("helloWorld", "sup", "#", "double");

		_class.addClassMethod(method);
		_class.addClassMethod(method2);
		
		assertEquals(2, _class.getMethods().size());
	}
	
	@Test
	public void TestAddClassField() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ClassField field = new ClassField("name", "+", "\\<String\\>", "double");
		_class.addClassField(field);
		
		assertEquals(1, _class.getFields().size());
		assertTrue(_class.getFields().contains(field));
	}
	
	@Test
	public void TestGetClassFields() {
		ClassField field = new ClassField("name", "+", "\\<String\\>", "double");
		ClassField field2 = new ClassField("name3", "+", "\\<String\\>", "double");

		_class.addClassField(field);
		_class.addClassField(field2);
		
		assertEquals(2, _class.getFields().size());
	}
	
	@After
	public void tearDown() {
		_class = null;
	}
}
