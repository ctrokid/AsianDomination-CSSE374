package PatternDetectionTests;

import pattern.decoration.GraphVizStyleTargetClass;
import pattern.detection.AdapterPatternDetector;

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

public class AdapterTest {
	private Properties props;
	private List<IPhase> phases;
	
	@Before
	public void setup() {
		props = new Properties();
		phases = new ArrayList<IPhase>();
	}
	
	@Test
	public void simpleAdapterTest() {
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/Adapter",
				"examples/adapter/ITarget" };
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new AdapterPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertTrue(((GraphVizStyleTargetClass) Adaptee).getColor().equals("red"));
		
		ITargetClass Adapter = i.next();
		assertTrue(((GraphVizStyleTargetClass) Adapter).getColor().equals("red"));

		ITargetClass ITarget = i.next();
		assertTrue(((GraphVizStyleTargetClass) ITarget).getColor().equals("red"));
	}
	
	@Test
	public void adapterTestFailure() {
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/AbstractTarget", "examples/adapter/AdapterAbstractTarget"}; // add classes in here, you could

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new AdapterPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertFalse(((GraphVizStyleTargetClass) Adaptee).getColor().equals("red"));

		ITargetClass ITarget = i.next();
		assertFalse(((GraphVizStyleTargetClass) ITarget).getColor().equals("red"));
		
		ITargetClass Adapter = i.next();
		assertFalse(((GraphVizStyleTargetClass) Adapter).getColor().equals("red"));
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
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/ITarget", "examples/adapter/WeirdAdapter"};

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new AdapterPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertFalse(((GraphVizStyleTargetClass) Adaptee).getColor().equals("red"));

		ITargetClass ITarget = i.next();
		assertFalse(((GraphVizStyleTargetClass) ITarget).getColor().equals("red"));
		
		ITargetClass Adapter = i.next();
		assertFalse(((GraphVizStyleTargetClass) Adapter).getColor().equals("red"));
	}
	
	@Test
	public void adapterTestFailMethodDelegationAdapter() {
		// this test runs with 80% delegation to pass
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/IDelegationTarget", "examples/adapter/DelegationAdapter"};

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new AdapterPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertFalse(((GraphVizStyleTargetClass) Adaptee).getColor().equals("red"));

		ITargetClass ITarget = i.next();
		assertFalse(((GraphVizStyleTargetClass) ITarget).getColor().equals("red"));
		
		ITargetClass Adapter = i.next();
		assertFalse(((GraphVizStyleTargetClass) Adapter).getColor().equals("red"));
	}
	
	@Test
	public void adapterTestSuccessfulMethodDelegationAdapter() {
		// this test runs with 75% delegation to pass
		props.setProperty("adapter-method-delegation", "0.75");
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/IDelegationTarget", "examples/adapter/DelegationAdapter"};

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new AdapterPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertTrue(((GraphVizStyleTargetClass) Adaptee).getColor().equals("red"));

		ITargetClass ITarget = i.next();
		assertTrue(((GraphVizStyleTargetClass) ITarget).getColor().equals("red"));
		
		ITargetClass Adapter = i.next();
		assertTrue(((GraphVizStyleTargetClass) Adapter).getColor().equals("red"));
	}

	@After
	public void tearDown() {
		props = null;
		phases = null;
	}
}
