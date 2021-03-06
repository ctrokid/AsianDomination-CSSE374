package PatternDetectionTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;
import fake.FakeUMLAddStrategy;
import framework.IPhase;
import pattern.decoration.GraphVizStyleTargetClass;
import pattern.detection.DecoratorPatternDetector;

public class DecoratorTest {
	private List<IPhase> phases;
	private Properties props;
	
	@Before
	public void setup() {
		phases = new ArrayList<IPhase>();
		props = new Properties();
	}
	
	@Test
	public void simpleDecoratorTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/CondimentDecorator",
				"examples/decorator/Milk" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new DecoratorPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		// Example for checking
		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertTrue((c).getColor().equals("green"));

		c = (GraphVizStyleTargetClass)i.next();
		assertTrue((c).getColor().equals("green"));

		c = (GraphVizStyleTargetClass)i.next();
		assertTrue((c).getColor().equals("green"));
	}

	@Test
	public void falseSimpleDecoratorTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/CondimentDecorator",
				"examples/decorator/Decaf" };// Decaf and ComdimentDecorator
												// both extend from Beverage
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new DecoratorPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
		
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
		
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
	}

	@Test
	public void noComponentFieldTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/NoComponentFieldClass",
				"examples/decorator/ChildrenClassForNoField" }; // NoComponentFieldClass
																// doesn't take
																// Beverage as a
																// field
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new DecoratorPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
		
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
		
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
	}

	@Test
	public void notImplementingTest() {
		String[] classes = new String[] { "examples/decorator/Beverage", "examples/decorator/NotImplementingClass", }; // NotImplementingClass
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new DecoratorPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
		
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse((c).getColor().equals("green"));
	}
	
	@After
	public void tearDown() {
		phases = null;
		props = null;
	}

}
