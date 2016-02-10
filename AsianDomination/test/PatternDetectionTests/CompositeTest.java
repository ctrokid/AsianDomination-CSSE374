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
	
	@After
	public void tearDown() {
		props = null;
		phases = null;
	}
}
