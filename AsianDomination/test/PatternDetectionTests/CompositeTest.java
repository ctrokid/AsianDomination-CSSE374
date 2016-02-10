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
import pattern.decoration.CompositeDecorator;
import pattern.detection.CompositePatternDetector;

public class CompositeTest {
	private List<IPhase> phases;
	private Properties props;
	
	@Before
	public void setup() {
		phases = new ArrayList<IPhase>();
		props = new Properties();
	}
	
	@Test
	public void simpleCompositeTest() {
		String[] classes = new String[] {
				"examples/composite/MyComponent",
				"examples/composite/MyComposite",
				"examples/composite/LeafA",
				"examples/composite/LeafB"
		};
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass c = i.next();
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertTrue(c instanceof CompositeDecorator);
	}
	
	@Test
	public void testCompositeNoFieldInComposite() {
		String[] classes = new String[] {
				"examples/composite/MyComponent",
				"examples/adapter/Adaptee",
				"examples/composite/SimulatedJButton",
				"examples/composite/LeafA"
		};
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		// this should eventually have 5 classes and include MyComposite in the iterator
		ITargetClass c = i.next();
		assertEquals("Component", c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertNotEquals("Composite", c.getPatternString(true));
		assertFalse(c instanceof CompositeDecorator);
		
		c = i.next();
		assertEquals("Composite", c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertEquals("Leaf", c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
//		c = i.next();
//		assert/*True False*/(c instanceof CompositeDecorator);
	}
	
	@Test
	public void testCompositeNoComposite() {
		String[] classes = new String[] {
				"examples/composite/MyComponent",
				"examples/composite/LeafA"
		};
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		ITargetClass c = i.next();
		assertNotEquals("Component", c.getPatternString(true));
		assertFalse(c instanceof CompositeDecorator);
		
		c = i.next();
		assertNotEquals("Composite", c.getPatternString(true));
		assertFalse(c instanceof CompositeDecorator);
	}
	
	@Test
	public void testCompositeMultipleComponentComposite() {
		String[] classes = new String[] {
				"examples/composite/MyCollinComposite",
				"examples/composite/CollinLeaf"
		};
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		ITargetClass c = i.next();
		assertEquals("Composite", c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertEquals("Leaf", c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		// third class added as a component
		c = i.next();
		assertEquals("examples/composite/CollinComponent", c.getClassName());
		assertEquals("Component", c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		// fourth class added as a component
//		c = i.next();
//		assertEquals("examples/composite/Component", c.getClassName());
//		assertEquals("Component", c.getPatternString(true));
//		assertTrue(c instanceof CompositeDecorator);
	}
	
	@Test
	public void simpleCompositeTest2() {
		String[] classes = new String[] {
				"examples/composite/MyComposite",
				"examples/composite/LeafA",
				"examples/composite/LeafB"
		};
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		ITargetClass c = i.next();
		assertEquals("Composite",c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertEquals("Leaf",c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertEquals("Leaf",c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
	}
	
	@Test
	public void simpleCompositeTest3() {
		String[] classes = new String[] {
				"examples/composite/MyComponent",
				"examples/composite/LeafA",
				"examples/composite/LeafB"
		};
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass c = i.next();
		assertFalse(c instanceof CompositeDecorator);
		
		c = i.next();
		assertFalse(c instanceof CompositeDecorator);
		
		c = i.next();
		assertFalse(c instanceof CompositeDecorator);
	}
	
	@Test
	public void WeirdFalseTest() {
		String[] classes = new String[] {
				"examples/composite/WeirdComposite1",
				"examples/composite/LeafA",
				"examples/composite/LeafB"
		};
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass c = i.next();
		assertEquals("Composite",c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertEquals("Leaf",c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
		
		c = i.next();
		assertEquals("Leaf",c.getPatternString(true));
		assertTrue(c instanceof CompositeDecorator);
	}
	
	@After
	public void tearDown() {
		props = null;
		phases = null;
	}
}
