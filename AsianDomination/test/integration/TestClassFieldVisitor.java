package integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import api.ITargetClass;
import asm.visitor.ClassFieldVisitor;
import fake.FakeProjectModel;

public class TestClassFieldVisitor {
	private IProjectModel model;
	private String classPath;
	private ClassFieldVisitor _visitor;
	
	@Before
	public void setup() {
		model = new FakeProjectModel();
		
		classPath = "test/examples/Animal";
		model.addClass(classPath);
		
		_visitor = new ClassFieldVisitor(Opcodes.ASM5, model.getTargetClassByName(classPath), model.getRelationshipManager());
	}
	
	@Test
	public void TestSimplePublicStringField() {
		_visitor.visitField(Opcodes.ACC_PUBLIC, "map", "String", null, null);
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		assertEquals(1, clazz.getFields().size());
		assertEquals(1, model.getRelationshipManager().getClassRelationships(classPath).size());
	}
	
	@Test
	public void TestProtectedSignatureField() {
		_visitor.visitField(Opcodes.ACC_PROTECTED, "list", "Collection", "Ljava/util/Collection<Lapi/IMethodStatement;>;", null);
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		assertEquals(1, clazz.getFields().size());
		assertEquals(2, model.getRelationshipManager().getClassRelationships(classPath).size());
	}
	
	@After
	public void tearDown() {
		_visitor = null;
		model = null;
	}
}
