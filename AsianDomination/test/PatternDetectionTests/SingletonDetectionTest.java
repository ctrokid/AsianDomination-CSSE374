package PatternDetectionTests;

import pattern.decoration.GraphVizStyleTargetClass;
import pattern.detection.SingletonPatternDetector;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;
import fake.FakeUMLAddStrategy;
import framework.IPhase;

public class SingletonDetectionTest {
	private List<IPhase> phases;
	private Properties props;
	
	@Before
	public void setup() {
		phases = new ArrayList<IPhase>();
		props = new Properties();
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

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new SingletonPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertTrue(c.getColor().equals("blue"));		

		c = (GraphVizStyleTargetClass)i.next();
		assertFalse(c.getColor().equals("blue"));
		
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse(c.getColor().equals("blue"));
		
		//FilterInputStream
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse(c.getColor().equals("blue"));
		
		//Runtime
		c = (GraphVizStyleTargetClass)i.next();
		assertTrue(c.getColor().equals("blue"));
		
		//Desktop
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse(c.getColor().equals("blue"));
		
		//Calendar
		c = (GraphVizStyleTargetClass)i.next();
		assertFalse(c.getColor().equals("blue"));
	}
	
	@Test
	public void testSingletonWithoutRequireGetInstance() {
		String[] classes = new String[] { "examples/singleton/SingletonExampleWithoutGetInstance" };

		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new SingletonPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertTrue(c.getColor().equals("blue"));
	}
	
	@Test
	public void testConfiguredSingletonWithoutRequireGetInstance() {
		String[] classes = new String[] { "examples/singleton/SingletonExampleWithoutGetInstance" };
		props.setProperty("singleton-require-getInstance", "true");
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new SingletonPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertFalse(c.getColor().equals("blue"));
	}
	
	@Test
	public void testConfiguredSingletonWithRequireGetInstance() {
		String[] classes = new String[] { "examples/singleton/SingletonExample" };
		props.setProperty("singleton-require-getInstance", "true");
		
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new SingletonPatternDetector(props));
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		GraphVizStyleTargetClass c = (GraphVizStyleTargetClass)i.next();
		assertTrue(c.getColor().equals("blue"));
	}
}
