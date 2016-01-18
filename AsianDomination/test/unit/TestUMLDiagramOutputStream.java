package unit;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import fake.FakeInputCommand;
import fake.FakeProjectModel;
import impl.ClassField;
import impl.ClassMethod;
import impl.TargetClass;
import input.InputCommand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import output.AbstractDiagramOutputStream;
import output.UMLDiagramOutputStream;
import utils.DotClassUtils.RelationshipType;

public class TestUMLDiagramOutputStream {
	private UMLDiagramOutputStream _outStreamVisitor;
	private OutputStream bytesOut;
	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		bytesOut = new ByteArrayOutputStream();
		_outStreamVisitor = new UMLDiagramOutputStream("");
		
		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
		f.setAccessible(true);
		
		f.set(_outStreamVisitor, bytesOut);
	}
	
	@Test
	public void TestUMLPreVisitClass() {
		ITargetClass clazz = new TargetClass("problem/test");
		_outStreamVisitor.preVisit(clazz);
		
		String expected = "test[\n\tlabel = \"{test|";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLPostVisitClass() {
		ITargetClass clazz = new TargetClass("problem/test");
		_outStreamVisitor.postVisit(clazz);
		
		String expected = "}\"\n]\n\n";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLVisitField() {
		IClassField field = new ClassField("_name", "#", "\\<String\\>", "Collection");
		_outStreamVisitor.visit(field);
		
		String expected = "# _name : Collection\\<String\\>\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLVisitMethod() {
		IClassMethod method = new ClassMethod("helloWorld", "int, String", "-", "double");
		_outStreamVisitor.visit(method);
		
		String expected = "- helloWorld(int, String) : double\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLPostVisitField() {
		IClassField field = new ClassField(null, null, null, null);
		_outStreamVisitor.postVisit(field);
		
		String expected = "|";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLVisitRelationshipManager() {
		InputCommand fakeCmd = new FakeInputCommand("", "", new String[] { });
		IProjectModel fakeModel = new FakeProjectModel(fakeCmd);
		
		fakeModel.addClass("test/example/Animal");
		fakeModel.addClass("test/example/Cat");
		fakeModel.addClass("test/example/output/OutStream");
		
		IRelationshipManager manager = fakeModel.getRelationshipManager();
		manager.addRelationshipEdge("test/example/Animal", "test/example/Cat", RelationshipType.INHERITANCE);
		manager.addRelationshipEdge("test/example/Cat", "test/example/output/OutStream", RelationshipType.ASSOCIATION);
		manager.addRelationshipEdge("test/example/Animal", "test/interfaces/IRunnable", RelationshipType.IMPLEMENTATION);
		
		_outStreamVisitor.setProjectModel(fakeModel);
		_outStreamVisitor.visit(manager);
		
		String expected = "edge [\n\tarrowhead = \"empty\"\n\tstyle = \"solid\"\n]\n\nAnimal -> Cat\n\nedge [\n\tarrowhead = \"empty\"\n\tstyle = \"dashed\"\n]\n\n\nedge [\n\tarrowhead = \"vee\"\n\tstyle = \"solid\"\n]\n\nCat -> OutStream\n\nedge [\n\tarrowhead = \"vee\"\n\tstyle = \"dashed\"\n]\n\n\n";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@After
	public void tearDown() {
		_outStreamVisitor = null;
	}
}
