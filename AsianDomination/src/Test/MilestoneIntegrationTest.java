package Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import Utils.DotClassUtils.RelationshipType;
import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassDeclaration;
import impl.ClassField;
import impl.ClassMethod;
import impl.RelationshipManager;
import impl.TargetClass;
import visitor.IVisitor;
import visitor.TargetClassOutputStream;

public class MilestoneIntegrationTest {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;
	private IRelationshipManager relationshipManager;
	
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
		
		relationshipManager = new RelationshipManager(new String[] {"SubAge", "Age"});
		relationshipManager.addRelationshipEdge("SubAge", "Age", RelationshipType.INHERITANCE);
		
		relationshipManager.accept(outStreamVisitor);
		
		String written = bytesOut.toString();
		assertTrue(written.contains("SubAge -> Age"));
		assertTrue(written.contains("SubAge||- getSomething() : double\\l"));
		assertTrue(written.contains("Age|- age : int\\l|+ getAge() : int\\l"));
	}
	
	@Test
	public void testTwoClassesWithInterface() {
		ITargetClass clazz1 = new TargetClass();
		
		IClassDeclaration decl = new ClassDeclaration("Age", "", "object", new String[] { "IRunnable" });
		
		clazz1.addPart(decl);
		clazz1.accept(outStreamVisitor);
		
		relationshipManager = new RelationshipManager(new String[] {"Age", "IRunnable"});
		relationshipManager.addRelationshipEdge("Age", "IRunnable", RelationshipType.IMPLEMENTATION);
		
		relationshipManager.accept(outStreamVisitor);
		
		String written = bytesOut.toString();
		assertTrue(written.contains("Age -> IRunnable"));
	}
}
