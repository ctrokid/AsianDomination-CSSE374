package integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import api.ITargetClass;
import fake.FakeInputCommand;
import fake.FakeProjectModel;
import asm.visitor.ClassMethodVisitor;
import input.InputCommand;
import output.IDiagramOutputStream;
import output.UMLDiagramOutputStream;
import utils.DotClassUtils.RelationshipType;
import visitor.Visitor;

public class TestClassMethodVisitor {
	private String[] params;
	private String diagramPath;
	private ClassMethodVisitor _visitor;
	
	@Before
	public void setup() {
		_visitor = null;
		diagramPath = "input_output_test/testClassMethodVisitor";		
	}
	
	@Test
	public void TestMethodVisitorVisitMethod() {
		params = new String[] {
				"test/examples/Animal",
				"test/examples/Cat"
		};
		
		IDiagramOutputStream outStream = new UMLDiagramOutputStream(diagramPath, "", new Visitor());
		InputCommand cmd = new FakeInputCommand(outStream, params);
		IProjectModel model = new FakeProjectModel(cmd);
		
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