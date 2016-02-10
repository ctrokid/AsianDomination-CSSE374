package PatternDetectionTests;

import pattern.decoration.SingletonDecorator;
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
		
		IProjectModel model = DetectionTestUtils.getPatternDetectedModel(phases);
		
		SingletonPatternDetector s = new SingletonPatternDetector(props);
		s.detectPatterns(model);
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();
		
		ITargetClass singelton = i.next();
		assertTrue(singelton instanceof SingletonDecorator);
		
		ITargetClass badS1 = i.next();
		assertFalse(badS1 instanceof SingletonDecorator);
		
		ITargetClass badS2 = i.next();
		assertFalse(badS2 instanceof SingletonDecorator);
		
		//FilterInputStream
		assertFalse(i.next() instanceof SingletonDecorator);
		
		//Runtime
		assertTrue(i.next() instanceof SingletonDecorator);
		
		//Desktop
		assertFalse(i.next() instanceof SingletonDecorator);
		
		//Calendar
		assertFalse(i.next() instanceof SingletonDecorator);
	}
}
