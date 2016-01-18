package integration;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import api.IClassField;
import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassField;
import impl.ClassMethod;
import impl.RelationshipManager;
import impl.TargetClass;
import utils.DotClassUtils.RelationshipType;
import visitor.IVisitor;
import visitor.UMLOutputStream;

public class MilestoneIntegrationTest {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;
	private IRelationshipManager relationshipManager;
	
	@Before
	public void setUp() {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new UMLOutputStream(bytesOut);
	}
	
	@Test
	public void testTwoClassesWithInheritance() {
		ITargetClass clazz1 = new TargetClass("test/examples/Animal");
		ITargetClass clazz2 = new TargetClass("test/examples/Cat");
		
		IClassField field = new ClassField("age", "-", "", "int");
		IClassMethod method = new ClassMethod("getAge", "", "+", "int");
		
		clazz1.addClassField(field);
		clazz1.addClassMethod(method);
		
		method = new ClassMethod("getSomething", "", "-", "double");
		
		clazz2.addClassMethod(method);
		
		clazz1.accept(outStreamVisitor);
		clazz2.accept(outStreamVisitor);
		
		relationshipManager = new RelationshipManager();
		relationshipManager.addRelationshipEdge("SubAge", "Age", RelationshipType.INHERITANCE);
		
		relationshipManager.accept(outStreamVisitor);
		
		String written = bytesOut.toString();
		assertTrue(written.contains("SubAge -> Age"));
		assertTrue(written.contains("SubAge||- getSomething() : double\\l"));
		assertTrue(written.contains("Age|- age : int\\l|+ getAge() : int\\l"));
	}
	
	@Test
	public void testTwoClassesWithInterface() {
		ITargetClass clazz1 = new TargetClass("test/examples/Animal");
		
		clazz1.accept(outStreamVisitor);
		
		relationshipManager = new RelationshipManager();
		relationshipManager.addRelationshipEdge("Age", "IRunnable", RelationshipType.IMPLEMENTATION);
		
		relationshipManager.accept(outStreamVisitor);
		
		String written = bytesOut.toString();
		assertTrue(written.contains("Age -> IRunnable"));
	}
}
