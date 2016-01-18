package system;

import impl.ProjectModel;
import input.UMLInputCommand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import api.IProjectModel;

public class TestSimpleUMLSystem {
	private UMLInputCommand _cmd;
	
	@Before
	public void setup() {
		_cmd = null;
	}
	
	@Test
	public void TestUMLForProjectSubsystem() {
		String[] classes = new String[] {
			"api/IClassField",
			"api/IClassMethod",
			"api/IMethodStatement",
			"api/IProjectModel",
			"api/IRelationshipManager",
			"api/ITargetClass",
			"api/ITargetClassPart",
			"impl/ClassField",
			"impl/ClassMethod",
			"impl/MethodStatement",
			"impl/RelationshipManager",
			"impl/ProjectModel",
			"impl/TargetClass",
		};
		
		_cmd = new UMLInputCommand("input_output/testProjectSubsystem", "input_output/testProjectSubsystem", classes);
		IProjectModel model = new ProjectModel(_cmd);
		model.parseModel();
	}
	
//	@Test
//	public void TestUMLForAnimalCatExample() {
//		String[] classes = new String[] {
//			"test/examples/Animal",
//			"test/examples/Cat"
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
