package integration;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import asm.visitor.ClassDeclarationVisitor;
import fake.FakeInputCommand;
import fake.FakeProjectModel;
import input.InputCommand;
import utils.DotClassUtils.RelationshipType;

public class TestClassDeclarationVisitor {
	private static final int VERSION = 51;
	private static final int ACCESS = 33;
	
	private IProjectModel model;
	private String classPath;
	private ClassDeclarationVisitor _visitor;
	
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
		
		_visitor = new ClassDeclarationVisitor(Opcodes.ASM5, model, classPath);
	}
	
	@Test
	public void TestSimpleDeclaration() {		
		_visitor.visit(VERSION, ACCESS, classPath, null, "java/lang/Object", new String[] {});
		
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 0);
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.INHERITANCE).size() == 1);
	}
	
	@Test
	public void TestInheritanceDeclaration() {
		_visitor.visit(VERSION, ACCESS, classPath, null, "test/example/Cat", new String[] { });
		
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 0);
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.INHERITANCE).size() == 1);
	}
	
	@Test
	public void TestInterfacesDeclaration() {
		_visitor.visit(VERSION, ACCESS, classPath, null, "Thread", new String[] { "test/Runnable", "test/IFaceApp" });
		
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 2);
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.INHERITANCE).size() == 1);
	}
	
	@Test
	public void TestInterfaceAndInheritanceDeclaration() {
		_visitor.visit(VERSION, ACCESS, classPath, null, "test/example/Cat", new String[] { "test/example/IRunnable" });
		
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 1);
		assertTrue(model.getRelationshipManager().getRelationshipEdges(RelationshipType.INHERITANCE).size() == 1);
	}
	
	@After
	public void tearDown() {
		model = null;
		_visitor = null;
	}
}
