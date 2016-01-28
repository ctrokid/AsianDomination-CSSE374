package PatternDetectionTests;

import impl.ProjectModel;
import input.InputCommand;
import input.UMLInputCommand;
import output.AbstractDiagramOutputStream;
import output.IDiagramOutputStream;
import pattern.detection.AdaptorPatternDetector;
import pattern.detection.DecoratorPatternDetector;
import pattern.detection.IPatternDetectionStrategy;
import pattern.detection.SingletonPatternDetector;
import visitor.Visitor;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;
import construction.UMLAddStrategy;
import fake.FakeInputCommand;
import fake.FakeUMLDiagramOutputStream;

@SuppressWarnings("unused")
public class SingletonDetectionTest {
	private InputCommand _cmd;

	@Before
	public void setup() {
		_cmd = null;
	}

	@Test
	public void simpleSingletonTest() {

		String[] classes = new String[] { "examples/singleton/SingletonExample", 
										  "examples/singleton/BadSingleton1",
										  "examples/singleton/BadSingleton2",
										  "java/io/FilterInputStream",
										  "java/lang/Runtime",
										  "java/awt/Desktop",
										  "java/util/Calendar"};

		IProjectModel model = buildModel(classes, "");
		SingletonPatternDetector s = new SingletonPatternDetector();
		s.detectPatterns(model);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		ITargetClass singelton = i.next();
		assertEquals("blue", singelton.getColor());
		
		ITargetClass badS1 = i.next();
		assertEquals("black", badS1.getColor());
		
		ITargetClass badS2 = i.next();
		assertEquals("black", badS2.getColor());
		
		//FilterInputStream
		assertEquals("black", i.next().getColor());
		
		//Runtime
		assertEquals("blue", i.next().getColor());
		
		//Desktop
		assertEquals("black", i.next().getColor());
		
		//Calendar
		assertEquals("black", i.next().getColor());
	}

	@Test
	public void IntegrationTest() {
		String[] classes = new String[] {
				 "headfirst/singleton/chocolate/ChocolateBoiler",
				 "headfirst/singleton/chocolate/ChocolateController"
				 
		};
		IProjectModel model = buildModel(classes, "docs/M4/SingletonTest");
		model.printModel();
	}
	
	@Test
	public void IntegrationTest2() {
		String[] classes = new String[] {
				  "java/io/FilterInputStream",
				  "java/lang/Runtime",
				  "java/awt/Desktop",
				  "java/util/Calendar"
		};
		IProjectModel model = buildModel(classes, "docs/M4/AutomatedSingletonTest");
		model.printModel();
	}

	@After
	public void tearDown() {
		_cmd = null;
	}

	private IProjectModel buildModel(String[] classes, String path) {
		List<IPatternDetectionStrategy> detectors = Arrays.asList(new SingletonPatternDetector());
		_cmd = new UMLInputCommand(path, path, classes, detectors);
		IProjectModel model = new ProjectModel(_cmd);
		model.build();
		return model;
	}
}
