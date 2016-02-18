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
import examples.composite.MutiParamComposite;
import examples.composite.MutiParamLeaf;
import examples.composite.MutiparamComponent;
import fake.FakeUMLAddStrategy;
import framework.IPhase;
import pattern.decoration.GraphVizStyleTargetClass;
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
		String[] classes = new String[] { "examples/composite/MyComponent", "examples/composite/MyComposite",
				"examples/composite/LeafA", "examples/composite/LeafB" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertTrue((c).getColor().equals("yellow"));

	}

	@Test
	public void testCompositeNoFieldInComposite() {
		String[] classes = new String[] { "examples/composite/MyComponent", "examples/adapter/Adaptee",
				"examples/composite/SimulatedJButton", "examples/composite/LeafA" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		// this should eventually have 5 classes and include MyComposite in the
		// iterator
		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Component", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertNotEquals("Composite", c.getClassType());
		assertFalse((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Composite", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Leaf", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		// c = (GraphVizStyleTargetClass)i.next();
		// assert/*True False*/(c instanceof CompositeDecorator);
	}

	@Test
	public void testCompositeMultipleComponentComposite() {
		String[] classes = new String[] { "examples/composite/MyCollinComposite", "examples/composite/CollinLeaf" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Composite", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Leaf", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		// third class added as a component
		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("examples/composite/CollinComponent", c.getClassName());
		assertEquals("Component", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		// fourth class added as a component
		// c = (GraphVizStyleTargetClass)i.next();
		// assertEquals("examples/composite/Component", c.getClassName());
		// assertEquals("Component", c.getClassType());
		// assertTrue(c instanceof CompositeDecorator);
	}

	@Test
	public void simpleCompositeTest2() {
		String[] classes = new String[] { "examples/composite/MyComposite", "examples/composite/LeafA",
				"examples/composite/LeafB" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Composite", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Leaf", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Leaf", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));
	}

	@Test
	public void WeirdFalseTest() {
		String[] classes = new String[] { "examples/composite/WeirdComposite1", "examples/composite/LeafA",
				"examples/composite/LeafB" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Composite", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Leaf", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Leaf", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));
	}

	@Test
	public void testCompositeNoComposite() {
		String[] classes = new String[] { "examples/composite/MyComponent", "examples/composite/LeafA" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertNotEquals("Component", c.getClassType());
		assertFalse((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertNotEquals("Composite", c.getClassType());
		assertFalse((c).getColor().equals("yellow"));
	}

	@Test
	public void simpleCompositeTest3() {
		String[] classes = new String[] { "examples/composite/MyComponent", "examples/composite/LeafA",
				"examples/composite/LeafB" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertFalse((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertFalse((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertFalse((c).getColor().equals("yellow"));
	}

	@Test
	public void testNoAddRemoveInComposite() {
		String[] classes = new String[] { "examples/composite/ComponentWithAddRemove",
				"examples/composite/CompositeWithNoAddRemove" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("", c.getClassType());
		assertFalse((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("", c.getClassType());
		assertFalse((c).getColor().equals("yellow"));

	}

	@Test
	public void testNoComponentInComposite() {
		String[] classes = new String[] { "examples/composite/ComponentWithAddRemove",
				"examples/composite/CompositeWithAddRemove" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("", c.getClassType());
		assertFalse((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("", c.getClassType());
		assertFalse((c).getColor().equals("yellow"));
	}

	@Test
	public void allowMutiParamComposite() {
		String[] classes = new String[] { "examples/composite/MutiParamComposite", "examples/composite/MutiParamLeaf",
				"examples/composite/MutiparamComponent" };

		props.setProperty("composite-require-addAndRemoveMethodsOneParameter", "false");

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Composite", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("Leaf", c.getClassType());
		assertTrue((c).getColor().equals("yellow"));

	}

	@Test
	public void disallowMutiParamComposite() {
		String[] classes = new String[] { "examples/composite/MutiParamComposite", "examples/composite/MutiParamLeaf",
				"examples/composite/MutiparamComponent" };

		props.setProperty("composite-require-addAndRemoveMethodsOneParameter", "true");

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));

		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass) i.next();
		assertEquals("", c.getClassType());
		assertTrue((c).getColor().equals("black"));

		c = (GraphVizStyleTargetClass) i.next();
		assertEquals("", c.getClassType());
		assertTrue((c).getColor().equals("black"));

	}

	@After
	public void tearDown() {
		props = null;
		phases = null;
	}
}
