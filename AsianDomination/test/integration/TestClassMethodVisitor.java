package integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import api.ITargetClass;
import fake.FakeProjectModel;
import utils.DotClassUtils.RelationshipType;
import asm.visitor.ClassMethodVisitor;

public class TestClassMethodVisitor {
	private ClassMethodVisitor _visitor;
	
	@Before
	public void setup() {
		_visitor = null;
	}
	
	@Test
	public void TestMethodVisitorVisitMethod() {
		IProjectModel model = new FakeProjectModel();
		
		String classPath = "test/examples/Animal";
		model.addClass(classPath);
		
		_visitor = new ClassMethodVisitor(Opcodes.ASM5, model.getTargetClassByName(classPath), model.getRelationshipManager());
		_visitor.visitMethod(1, "helloWorld", "(Ljava/lang/String;I)V", null, null);
		_visitor.visitMethod(2, "run", "()Ljava/lang/String;", null, null);
		// should NOW add <init> methods
		_visitor.visitMethod(1, "<init>", "()V", null, null);
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		assertEquals(3, clazz.getMethods().size());
		
		boolean contains = model.getRelationshipManager().getClassRelationship(clazz.getClassName(), RelationshipType.USES, "void") == null ? false : true;
		assertTrue(contains);
		
		contains = model.getRelationshipManager().getClassRelationship(clazz.getClassName(), RelationshipType.USES, "java/lang/String") == null ? false : true;;
		assertTrue(contains);
	}
	
	@After
	public void tearDown() {
		_visitor = null;
	}
}