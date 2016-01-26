package integration;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.objectweb.asm.Opcodes;

import api.IClassDeclaration;
import api.IProjectModel;
import api.ITargetClass;
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
	public void TestClassDeclaration() {
		_visitor.visit(VERSION, ACCESS, classPath, null, "Thread", new String[] { "test/Runnable", "test/IFaceApp" });
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		IClassDeclaration decl = clazz.getDeclaration();
		
		assertEquals("Thread", decl.getSuperClassType());
		assertEquals(Arrays.asList("test/Runnable", "test/IFaceApp"), decl.getInterfaces());
	}
	
	@Test
	public void TestSimpleDeclaration() {		
		_visitor.visit(VERSION, ACCESS, classPath, null, "java/lang/Object", new String[] {});
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		HashMap<RelationshipType, HashSet<String>> relationships = clazz.getRelationEdges();
		
		assertEquals(relationships.get(RelationshipType.INHERITANCE).size(), 1);
		assertEquals(relationships.get(RelationshipType.IMPLEMENTATION).size(), 0);
	}
	
	@Test
	public void TestInheritanceDeclaration() {
		_visitor.visit(VERSION, ACCESS, classPath, null, "test/example/Cat", new String[] { });
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		HashMap<RelationshipType, HashSet<String>> relationships = clazz.getRelationEdges();
		
		assertEquals(relationships.get(RelationshipType.INHERITANCE).size(), 1);
		assertEquals(relationships.get(RelationshipType.IMPLEMENTATION).size(), 0);
	}
	
	@Test
	public void TestInterfacesDeclaration() {
		_visitor.visit(VERSION, ACCESS, classPath, null, "Thread", new String[] { "test/Runnable", "test/IFaceApp" });
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		HashMap<RelationshipType, HashSet<String>> relationships = clazz.getRelationEdges();
		
		assertEquals(relationships.get(RelationshipType.INHERITANCE).size(), 1);
		assertEquals(relationships.get(RelationshipType.IMPLEMENTATION).size(), 2);
	}
	
	@Test
	public void TestInterfaceAndInheritanceDeclaration() {
		_visitor.visit(VERSION, ACCESS, classPath, null, "test/example/Cat", new String[] { "test/example/IRunnable" });
		
		ITargetClass clazz = model.getTargetClassByName(classPath);
		HashMap<RelationshipType, HashSet<String>> relationships = clazz.getRelationEdges();
		
		assertEquals(relationships.get(RelationshipType.INHERITANCE).size(), 1);
		assertEquals(relationships.get(RelationshipType.IMPLEMENTATION).size(), 1);
	}
	
	@After
	public void tearDown() {
		model = null;
		_visitor = null;
	}
}
