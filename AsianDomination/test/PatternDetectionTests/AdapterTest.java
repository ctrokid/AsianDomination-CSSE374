package PatternDetectionTests;

import impl.ProjectModel;
import input.InputCommand;
import input.UMLInputCommand;
import pattern.decoration.AdapterDecorator;
import pattern.detection.AdapterPatternDetector;
import pattern.detection.IDetectionVisitor;
import pattern.detection.IPatternDetectionStrategy;
import pattern.detection.SingletonDetectionVisitor;
import visitor.Visitor;

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

		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/Adapter",
				"examples/adapter/ITarget" };
		IProjectModel model = buildModel(classes, "docs/M5/SimpleAdapterTest");
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();

		assertTrue(Adaptee instanceof AdapterDecorator);

		ITargetClass Adapter = i.next();
		assertTrue(Adapter instanceof AdapterDecorator);

		ITargetClass ITarget = i.next();
		assertTrue(ITarget instanceof AdapterDecorator);
	}
	
	@Test
	public void adapterTestFailure() {
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/AbstractTarget", "examples/adapter/AdapterAbstractTarget"}; // add classes in here, you could

		IProjectModel model = buildModel(classes, "docs/M5/SimpleAdapterTestTwo");
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertFalse(Adaptee instanceof AdapterDecorator);

		ITargetClass ITarget = i.next();
		assertFalse(ITarget instanceof AdapterDecorator);
		
		ITargetClass Adapter = i.next();
		assertFalse(Adapter instanceof AdapterDecorator);
	}
	
//	@Test
//	public void adapterTestFailureTwo() {
//		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/ITarget", "examples/adapter/AdapterNoImpl"}; // add classes in here, you could
//
//		IProjectModel model = buildModel(classes, "docs/M5/SimpleAdapterTestTwo");
//		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
//
//		ITargetClass Adaptee = i.next();
//		assertTrue(Adaptee instanceof AdapterDecorator);
//
//		ITargetClass ITarget = i.next();
//		assertTrue(ITarget instanceof AdapterDecorator);
//		
//		ITargetClass Adapter = i.next();
//		assertTrue(Adapter instanceof AdapterDecorator);
//	}
	
	@Test
	public void adapterTestFailureWeirdAdapter() {
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/ITarget", "examples/adapter/WeirdAdapter"}; // add classes in here, you could

		IProjectModel model = buildModel(classes, "docs/M5/SimpleAdapterTestTwo");
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertFalse(Adaptee instanceof AdapterDecorator);

		ITargetClass ITarget = i.next();
		assertFalse(ITarget instanceof AdapterDecorator);
		
		ITargetClass Adapter = i.next();
		assertFalse(Adapter instanceof AdapterDecorator);
	}

	@After
	public void tearDown() {
		_cmd = null;
	}

	private IProjectModel buildModel(String[] classes, String path) {
		List<IPatternDetectionStrategy> detectors = Arrays.asList(new AdapterPatternDetector());
		List<IDetectionVisitor> detectVisitors = Arrays.asList(new SingletonDetectionVisitor(new Visitor()));
		_cmd = new UMLInputCommand(path, path, classes, detectVisitors, detectors);
		IProjectModel model = new ProjectModel(_cmd);
		model.buildModel();
		return model;
	}
}
