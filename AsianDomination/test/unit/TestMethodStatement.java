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
		_stmt = new MethodStatement(expected, "string", "not", "()V", 1);
		assertEquals(expected, _stmt.getCallerClass());
	}
	
	@Test
	public void testGetClassToClass() {
		String expected = "problem.impl.Pizza";
		_stmt = new MethodStatement("problem.impl.Main", expected, "not", "()V", 1);
		assertEquals(expected, _stmt.getClassToCall());
	}
	
	@Test
	public void testGetMethodName() {
		String expected = "helloWorld";
		_stmt = new MethodStatement("problem.Main", "string", expected, "()V", 1);
		assertEquals(expected, _stmt.getMethodName());
	}
	
	@Test
	public void testGetParameters() {
		String expected = "String, int";
		_stmt = new MethodStatement("problem.Main", "string", "helloWorld", "(Ljava/lang/String;I)V", 1);
		assertEquals(expected, _stmt.getParameters());
	}
	
	@Test
	public void testGetSequenceLevel() {
		int expected = 2;
		_stmt = new MethodStatement("problem.Main", "string", "helloWorld", "()V", expected);
		assertEquals(expected, _stmt.getSequenceLevel());
	}
	
	@After
	public void tearDown() {
		_stmt = null;
	}
}
