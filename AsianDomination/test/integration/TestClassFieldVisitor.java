package integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import api.ITargetClass;
import asm.visitor.ClassFieldVisitor;
import fake.FakeInputCommand;
import fake.FakeProjectModel;
import input.InputCommand;
import utils.DotClassUtils.RelationshipType;

public class TestClassFieldVisitor {
	private IProjectModel model;
	private String classPath;
	private ClassFieldVisitor _visitor;
	
	@Before
	public void setup() {
		_visitor = null;
		String asmPath = "input_output_test/testClassMethodVisitor";
		String diagramPath = asmPath;	
		String[] params = new String[] {
				"test/examples/Animal",
				"test/examples/Cat"
		};
		
		InputCommand cmd = new FakeInputCommand(asmPath, diagramPath, params);
		model = new FakeProjectModel(cmd);
		
		classPath = "test/examples/Animal";
		model.addClass(classPath);
		
		_visitor = new ClassFieldVisitor(Opcodes.ASM5, model, classPath);
	}
	
	@Test
	public void TestSimplePublicStringField() {
		_visitor.visitField(Opcodes.ACC_PUBLIC, "map", "String", null, null);
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		assertEquals(1, clazz.getFields().size());
		assertEquals(1, clazz.getRelationEdges().get(RelationshipType.ASSOCIATION).size());
	}
	
	@Test
	public void TestProtectedSignatureField() {
		_visitor.visitField(Opcodes.ACC_PROTECTED, "list", "Collection", "Ljava/util/Collection<Lapi/IMethodStatement;>;", null);
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		assertEquals(1, clazz.getFields().size());
		assertEquals(1, clazz.getRelationEdges().get(RelationshipType.ASSOCIATION).size());
	}
	
	@After
	public void tearDown() {
		_visitor = null;
		model = null;
	}
}
