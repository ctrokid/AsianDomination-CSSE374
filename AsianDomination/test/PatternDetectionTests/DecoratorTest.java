package PatternDetectionTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;
import impl.ProjectModel;
import input.InputCommand;
import input.UMLInputCommand;
import pattern.decoration.DecoratorTargetClass;
import pattern.detection.DecoratorPatternDetector;
import pattern.detection.IDetectionVisitor;
import pattern.detection.IPatternDetectionStrategy;
import pattern.detection.SingletonDetectionVisitor;
import visitor.Visitor;

public class DecoratorTest {
	private InputCommand _cmd;

	@Before
	public void setup() {
		_cmd = null;
	}

	@Test
	public void simpleDecoratorTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/CondimentDecorator",
				"examples/decorator/Milk" };
		IProjectModel model = buildModel(classes, "docs/M5/SimpleDecoratorTest");

		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		// Example for checking
		ITargetClass dec = i.next();

		assertTrue(dec instanceof DecoratorTargetClass);

		ITargetClass Adapter = i.next();
		assertTrue(Adapter instanceof DecoratorTargetClass);

		ITargetClass ITarget = i.next();
		assertTrue(ITarget instanceof DecoratorTargetClass);

	}

	@Test
	public void falseSimpleDecoratorTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/CondimentDecorator",
				"examples/decorator/Decaf" };// Decaf and ComdimentDecorator
												// both extend from Beverage
		IProjectModel model = buildModel(classes, "docs/M5/SimpleDecoratorTest");

		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass dec = i.next();

		// TODO: add check here

	}

	@Test
	public void noComponentFieldTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/NoComponentFieldClass",
				"examples/decorator/ChildrenClassForNoField" }; // NoComponentFieldClass
																// doesn't take
																// Beverage as a
																// field
		IProjectModel model = buildModel(classes, "docs/M5/SimpleDecoratorTest");

		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass dec = i.next();

		// TODO: Add check here
	}

	@Test
	public void notImplementingTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/NotImplementingClass", }; // NotImplementingClass
																														// only
																														// uses
																														// Beverage
																														// class
		IProjectModel model = buildModel(classes, "docs/M5/SimpleDecoratorTest");

		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass dec = i.next();

		// TODO: add check here

	}

	private IProjectModel buildModel(String[] classes, String path) {
		List<IPatternDetectionStrategy> detectors = Arrays.asList(new DecoratorPatternDetector());
		List<IDetectionVisitor> detectVisitors = Arrays.asList(new SingletonDetectionVisitor(new Visitor()));
		_cmd = new UMLInputCommand(path, path, classes, detectVisitors, detectors);
		IProjectModel model = new ProjectModel(_cmd);
		model.buildModel();
		return model;
	}

}
