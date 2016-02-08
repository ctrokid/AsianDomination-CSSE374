package PatternDetectionTests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;
import pattern.decoration.CompositeDecorator;

public class CompositeTest {
		
	@Test
	public void simpleCompositeTest() {
		String[] classes = new String[] {
				"examples/composite/MyComponent",
				"examples/composite/MyComposite",
				"examples/composite/LeafA",
				"examples/composite/LeafB"
		};
		
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleAdapterTest");
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
}
