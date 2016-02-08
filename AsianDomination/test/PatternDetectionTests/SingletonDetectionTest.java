package PatternDetectionTests;

import pattern.decoration.SingletonDecorator;
import pattern.detection.SingletonPatternDetector;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;

public class SingletonDetectionTest {

	@Test
	public void simpleSingletonTest() {

		String[] classes = new String[] { "examples/singleton/SingletonExample", 
										  "examples/singleton/BadSingleton1",
										  "examples/singleton/BadSingleton2",
										  "java/io/FilterInputStream",
										  "java/lang/Runtime",
										  "java/awt/Desktop",
										  "java/util/Calendar"};

		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "");
		SingletonPatternDetector s = new SingletonPatternDetector();
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
	

	@Test
	public void IntegrationTest() {
		String[] classes = new String[] {
				 "headfirst/singleton/chocolate/ChocolateBoiler",
				 "headfirst/singleton/chocolate/ChocolateController"
				 
		};
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M4/SingletonTest");
		model.printModel();
	}
	
	@Test
	public void IntegrationTest2() {
		String[] classes = new String[] {
				  "java/io/FilterInputStream",
				  "java/lang/Runtime",
				  "java/awt/Desktop",
				  "java/util/Calendar"
		};
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M4/AutomatedSingletonTest");
		model.printModel();
	}
}
