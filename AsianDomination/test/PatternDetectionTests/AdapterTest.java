package PatternDetectionTests;

import impl.ProjectModel;
import input.InputCommand;
import input.UMLInputCommand;
import output.AbstractDiagramOutputStream;
import output.IDiagramOutputStream;
import pattern.detection.DecoratorPatternDetector;
import pattern.detection.IPatternDetectionStrategy;
import pattern.detection.SingletonPatternDetector;
import visitor.Visitor;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import construction.UMLAddStrategy;
import fake.FakeInputCommand;
import fake.FakeUMLDiagramOutputStream;

@SuppressWarnings("unused")
public class AdapterTest {
	
		private InputCommand _cmd;
		
		@Before
		public void setup() {
			_cmd = null;
		}
		
//		@Test
//		public void TestUMLForProjectSubsystem() {
//			String[] classes = new String[] {
//				"impl/ClassField",
//				"examples/simple/SingletonExample",
//				"examples/decorator/Beverage",
//				"examples/decorator/CondimentDecorator",
//				"examples/decorator/Milk"
//			};
//			
//			List<IPatternDetectionStrategy> detectors = Arrays.asList(new SingletonPatternDetector(), new DecoratorPatternDetector());
//			_cmd = new UMLInputCommand("input_output/testProjectSubsystem", "input_output/testProjectSubsystem", classes, detectors);
//			IProjectModel model = new ProjectModel(_cmd);
//			model.parseModel();
//		}

		@After
		public void tearDown() {
			_cmd = null;
		}

}
