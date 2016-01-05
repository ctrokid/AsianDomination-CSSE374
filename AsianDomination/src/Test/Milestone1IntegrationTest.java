package Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import impl.ClassDeclaration;
import impl.ClassField;
import impl.ClassMethod;
import impl.TargetClass;
import visitor.IVisitor;
import visitor.TargetClassOutputStream;

public class Milestone1IntegrationTest {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;
	
	@Before
	public void setUp() {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new TargetClassOutputStream(bytesOut);
	}
	
	@Test
	public void testTwoClassesWithInheritance() {
		ITargetClass clazz1 = new TargetClass();
		ITargetClass clazz2 = new TargetClass();
		
		IClassDeclaration decl = new ClassDeclaration("Age", "", "object", new String[] { });
		IClassField field = new ClassField("age", "-", "", "int");
		IClassMethod method = new ClassMethod("getAge", "", "+", "int");
		
		clazz1.addPart(decl);
		clazz1.addPart(field);
		clazz1.addPart(method);
		
		decl = new ClassDeclaration("SubAge", "", "Age", new String[] { });
		method = new ClassMethod("getSomething", "", "-", "double");
		
		clazz2.addPart(decl);
		clazz2.addPart(method);
		
		clazz1.accept(outStreamVisitor);
		clazz2.accept(outStreamVisitor);
		
		outStreamVisitor.visitCollection(new ITargetClass[] {clazz1, clazz2});
		
		String written = bytesOut.toString();
		assertTrue(written.contains("SubAge -> Age"));
	}
}
