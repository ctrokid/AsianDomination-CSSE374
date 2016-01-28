package PatternDetectionTests;

import impl.ProjectModel;
import input.InputCommand;
import input.UMLInputCommand;
import pattern.decoration.AdapterDecorator;
import pattern.detection.AdapterPatternDetector;
import pattern.detection.IPatternDetectionStrategy;
import pattern.detection.SingletonPatternDetector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;

public class AdapterTest {
	private InputCommand _cmd;

	@Before
	public void setup() {
		_cmd = null;
	}

	@Test
	public void simpleAdapterTest() {

		String[] classes = new String[] { "examples/adapter/Adaptee",
										  "examples/adapter/Adapter",
										  "examples/adapter/ITarget"
										  };

		IProjectModel model = buildModel(classes, "docs/M5/SimpleAdapterTest");
		SingletonPatternDetector s = new SingletonPatternDetector();
		s.detectPatterns(model);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		ITargetClass Adaptee = i.next();
		
		assertTrue(Adaptee instanceof AdapterDecorator);
		
		ITargetClass Adapter = i.next();
		assertTrue(Adapter instanceof AdapterDecorator);
		
		ITargetClass ITarget = i.next();
		assertTrue(ITarget instanceof AdapterDecorator);
		
		model.printModel();
	}

	@After
	public void tearDown() {
		_cmd = null;
	}

	private IProjectModel buildModel(String[] classes, String path) {
		List<IPatternDetectionStrategy> detectors = Arrays.asList(new AdapterPatternDetector());
		_cmd = new UMLInputCommand(path, path, classes, detectors);
		IProjectModel model = new ProjectModel(_cmd);
		model.build();
		return model;
	}
}



