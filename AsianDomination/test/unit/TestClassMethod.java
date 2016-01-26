package unit;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IMethodStatement;
import impl.ClassMethod;
import impl.MethodStatement;

public class TestClassMethod {
	private ClassMethod _method;
	
	@Before
	public void setup() {
		_method = null;
	}
	
	@Test
	public void testGetSignature() {
		String expected = "signature";
		_method = new ClassMethod("helloWorld", expected, "+", "void");
		assertEquals(expected, _method.getSignature());
	}
	
	@Test
	public void testGetMethodName() {
		String expected = "helloWorld";
		_method = new ClassMethod(expected, "test", "+", "void");
		assertEquals(expected, _method.getMethodName());
	}
	
	@Test
	public void testGetAccessLevel() {
		String expected = "+";
		_method = new ClassMethod("helloWorld", "test", expected, "void");
		assertEquals(expected, _method.getAccessLevel());
	}
	
	@Test
	public void testGetReturnType() {
		String expected = "double";
		_method = new ClassMethod("helloWorld", "test", "+", expected);
		assertEquals(expected, _method.getReturnType());
	}
	
	@Test
	public void testAddMethodStatement() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		MethodStatement stmt = new MethodStatement("problem.Main", "string", "helloWorld", "(Ljava/lang/String;I)V");
		
		_method = new ClassMethod("helloWorld", "test", "+", "void");
		_method.addMethodStatement(stmt);
		
		Collection<IMethodStatement> stmts = _method.getMethodStatements();
		assertTrue(stmts.contains(stmt));
	}
	
	@Test
	public void testAddOverloadedMethodStatements() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		_method = new ClassMethod("helloWorld", "test", "+", "void");
		
		MethodStatement stmt = new MethodStatement("problem.Main", "string", "helloWorld", "(Ljava/lang/String;I)V");
		_method.addMethodStatement(stmt);
		
		MethodStatement stmt2 = new MethodStatement("problem.Main", "string", "helloWorld", "(Ljava/lang/String;I)V");
		_method.addMethodStatement(stmt2);
		
		assertTrue(_method.getMethodStatements().contains(stmt));
		assertTrue(_method.getMethodStatements().contains(stmt2));
	}
	
	@Test
	public void testGetMethodStatementsByNameOverloaded() {
		_method = new ClassMethod("helloWorld", "test", "+", "void");
		
		MethodStatement stmt = new MethodStatement("problem.Main", "string", "helloWorld", "(Ljava/lang/String;I)V");
		_method.addMethodStatement(stmt);
		
		MethodStatement stmt2 = new MethodStatement("problem.Main", "string", "helloWorld", "(Ljava/lang/String;I)V");
		_method.addMethodStatement(stmt2);
		
		assertTrue(_method.getMethodStatements().contains(stmt));
		assertTrue(_method.getMethodStatements().contains(stmt2));
	}
	
	@After
	public void tearDown() {
		_method = null;
	}
}
