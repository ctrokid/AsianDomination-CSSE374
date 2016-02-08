package PatternDetectionTests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;
import pattern.decoration.DecoratorTargetClass;

public class DecoratorTest {
	
	@Test
	public void simpleDecoratorTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/CondimentDecorator",
				"examples/decorator/Milk" };
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleDecoratorTest");

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
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleDecoratorTest");

		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);
		
		dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);
		
		dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);

	}

	@Test
	public void noComponentFieldTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/NoComponentFieldClass",
				"examples/decorator/ChildrenClassForNoField" }; // NoComponentFieldClass
																// doesn't take
																// Beverage as a
																// field
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleDecoratorTest");

		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);
		
		dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);
		
		dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);
		
	}

	@Test
	public void notImplementingTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/NotImplementingClass", }; // NotImplementingClass
		
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleDecoratorTest");

		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);
		
		dec = i.next();
		assertFalse(dec instanceof DecoratorTargetClass);
	}

}
