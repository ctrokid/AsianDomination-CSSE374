package system;

import impl.ProjectModel;
import impl.Visitor;
import input.InputCommand;
import input.UMLInputCommand;
import output.AbstractDiagramOutputStream;
import output.IDiagramOutputStream;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import construction.UMLAddStrategy;
import fake.FakeInputCommand;
import fake.FakeUMLDiagramOutputStream;

public class TestSimpleUMLSystem {
	private InputCommand _cmd;
	
	@Before
	public void setup() {
		_cmd = null;
	}
	
	@Test
	public void TestUMLForSimpleFactorySystem() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String[] classes = new String[] {
				"examples/simple/Animal",
				"examples/simple/Cat",
				"examples/simple/Dog",
				"examples/simple/SimpleAnimalFactory",
		};
		
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		IDiagramOutputStream out = new FakeUMLDiagramOutputStream("", new Visitor());
		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
		f.setAccessible(true);
		f.set(out, bytesOut);
		
		FakeInputCommand cmd = new FakeInputCommand("", "", classes);
		cmd.setOutputStream(out);
		cmd.setAddStrategy(new UMLAddStrategy());
		
		IProjectModel model = new ProjectModel(cmd);
		model.parseModel();
		
		String actual = bytesOut.toString();
		String expectedFactory = "{SimpleAnimalFactory||+ createAnimal(String) : Animal\\l}";
		String expectedAnimalUses = "SimpleAnimalFactory -> Animal[arrowhead = \"vee\",style = \"dashed\"];\n";
		String expectedDogUses = "SimpleAnimalFactory -> Dog[arrowhead = \"vee\",style = \"dashed\"];\n";
		String expectedCatUses = "SimpleAnimalFactory -> Cat[arrowhead = \"vee\",style = \"dashed\"];\n";
		
		assertTrue(actual.contains(expectedFactory));
		assertTrue(actual.contains(expectedAnimalUses));
		assertTrue(actual.contains(expectedDogUses));
		assertTrue(actual.contains(expectedCatUses));
	}
	
	@Test
	public void TestUMLForSimpleExampleInheritanceSystem() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String[] classes = new String[] {
				"examples/simple/Animal",
				"examples/simple/Cat"
		};
		
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		IDiagramOutputStream out = new FakeUMLDiagramOutputStream("", new Visitor());
		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
		f.setAccessible(true);
		f.set(out, bytesOut);
		
		FakeInputCommand cmd = new FakeInputCommand("", "", classes);
		cmd.setOutputStream(out);
		cmd.setAddStrategy(new UMLAddStrategy());
		
		IProjectModel model = new ProjectModel(cmd);
		model.parseModel();
		
		String actual = bytesOut.toString();
		String catExpected = "{Cat|- fur : Fur\\l|+ getNoise(int) : String\\l}";
		String animalExpected = "{Animal|# name : String\\l- age : int\\l+ var : double\\l|+ getNoise(int) : String\\l+ run() : void\\l}";
		String catToAnimal = "Cat -> Animal[arrowhead = \"empty\",style = \"solid\"];\n";
		String catDoesNotAssociateFur = "Cat -> Fur";
		String catDoesNotAssociateAnimal = "Cat -> Animal[arrowhead = \"vee\",style = \"solid\"];\n";
		
		assertTrue(actual.contains(catExpected));
		assertTrue(actual.contains(animalExpected));
		assertTrue(actual.contains(catToAnimal));
		assertFalse(actual.contains(catDoesNotAssociateFur));
		assertFalse(actual.contains(catDoesNotAssociateAnimal));
	}
	
	@Test
	public void TestUMLForProjectSubsystem() {
		String[] classes = new String[] {
//				"SequenceDiagramPack/driver",
//				"SequenceDiagramPack/Payment",
//				"SequenceDiagramPack/Sale"
//			"api/IClassField",
//			"api/IClassMethod",
//			"api/IMethodStatement",
//			"api/IProjectModel",
//			"api/IRelationshipManager",
//			"api/ITargetClass",
//			"api/ITargetClassPart",
//			"impl/ClassField",
//			"impl/ClassMethod",
//			"impl/MethodStatement",
//			"impl/ProjectModel",
//			"impl/TargetClass",
//			"impl/PrintCommand",
//			"asm/visitor/ClassDeclarationVisitor",
//			"asm/visitor/ClassFieldVisitor",
//			"asm/visitor/ClassMethodVisitor",
//			"asm/visitor/MethodAssociationVisitor",
//			"asm/visitor/DiagramType",
//			"asm/visitor/DesignParser",
//			"construction/AbstractAddStrategy",
//			"construction/IAddStrategy",
//			"construction/SDAddStrategy",
//			"construction/UMLAddStrategy",
//			"input/InputCommand",
//			"input/SequenceInputCommand",
//			"input/UMLInputCommand",
//			"output/AbstractDiagramOutputStream",
//			"output/IDiagramOutputStream",
//			"output/SDDiagramOutputStream",
//			"output/UMLDiagramOutputStream",
//			"utils/AsmClassUtils",
//			"utils/DotClassUtils",
//			"utils/LaunchDiagramGenerator",
//			"utils/PackageInspector",
//			"utils/SignatureParser",
//			"visitor/ITraverser",
//			"visitor/IVisitMethod",
//			"visitor/IVisitor",
//			"visitor/LookupKey",
//			"visitor/VisitorAdapter",
//			"visitor/VisitType"
			"examples/simple/SingletonExample"
		};
		
		_cmd = new UMLInputCommand("input_output/testProjectSubsystem", "input_output/testProjectSubsystem", classes);
		IProjectModel model = new ProjectModel(_cmd);
		model.parseModel();
	}
	
//	@Test
//	public void TestUMLForAnimalCatExample() {
//		String[] classes = new String[] {
//			"examples/simple/Animal",
//			"examples/simple/Cat",
//			"examples/simple/Dog",
//			"examples/simple/SimpleAnimalFactory",
//			"examples/simple/Fur"
//		};
//		
//		_cmd = new UMLInputCommand("input_output/testUML", "input_output/testUML", classes);
//		IProjectModel model = new ProjectModel(_cmd);
//		model.parseModel();
//	}
//	
//	@Test
//	public void TestGenerationUML() {
//		LaunchDiagramGenerator.RunGVEdit("input_output/testProjectSubsystem.gv", "input_output/testProjectSubsystem", DiagramFileExtension.PDF);
//	}
	
	@After
	public void tearDown() {
		_cmd = null;
	}
}
