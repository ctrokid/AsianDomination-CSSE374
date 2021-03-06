package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import impl.MethodStatement;

public class TestMethodStatement {
	private MethodStatement _stmt;
	
	@Before
	public void setup() {
		_stmt = null;
	}
	
	@Test
	public void testGetCallerClass() {
		String expected = "problem.impl.Main";
		_stmt = new MethodStatement(expected, "string", "not", "()V");
		assertEquals(expected, _stmt.getCallerClass());
	}
	
	@Test
	public void testGetClassToClass() {
		String expected = "problem.impl.Pizza";
		_stmt = new MethodStatement("problem.impl.Main", expected, "not", "()V");
		assertEquals(expected, _stmt.getClassToCall());
	}
	
	@Test
	public void testGetMethodName() {
		String expected = "helloWorld";
		_stmt = new MethodStatement("problem.Main", "string", expected, "()V");
		assertEquals(expected, _stmt.getMethodName());
	}
	
	@Test
	public void testGetParameters() {
		String expected = "String, int";
		_stmt = new MethodStatement("problem.Main", "string", "helloWorld", "(Ljava/lang/String;I)V");
		assertEquals(expected, _stmt.getParameters());
	}
	
	@After
	public void tearDown() {
		_stmt = null;
	}
}
