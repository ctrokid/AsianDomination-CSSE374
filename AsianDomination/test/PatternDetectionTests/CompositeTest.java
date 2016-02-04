package PatternDetectionTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;
import impl.ProjectModel;
import input.InputCommand;
import input.UMLInputCommand;
import pattern.decoration.CompositeDecorator;
import pattern.detection.CompositePatternDetector;
import pattern.detection.IPatternDetectionStrategy;

public class CompositeTest {
	private InputCommand _cmd;

	@Before
	public void setup() {
		_cmd = null;
	}
	
	@Test
	public void simpleCompositeTest() {
		String[] classes = new String[] {
				"examples/composite/MyComponent",
				"examples/composite/MyComposite",
				"examples/composite/LeafA",
				"examples/composite/LeafB"
		};
		
		IProjectModel model = buildModel(classes, "docs/M5/SimpleAdapterTest");
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
		_cmd = null;
	}
	
	private IProjectModel buildModel(String[] classes, String path) {
		List<IPatternDetectionStrategy> detectors = Arrays.asList(new CompositePatternDetector());
		_cmd = new UMLInputCommand(path, path, classes, null, detectors);
		IProjectModel model = new ProjectModel(_cmd);
		model.buildModel();
		return model;
	}
}
