package PatternDetectionTests;

import pattern.decoration.AdapterDecorator;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import api.IProjectModel;
import api.ITargetClass;

public class AdapterTest {

	@Test
	public void simpleAdapterTest() {

		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/Adapter",
				"examples/adapter/ITarget" };
		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleAdapterTest");
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();

		assertTrue(Adaptee instanceof AdapterDecorator);

		ITargetClass Adapter = i.next();
		assertTrue(Adapter instanceof AdapterDecorator);

		ITargetClass ITarget = i.next();
		assertTrue(ITarget instanceof AdapterDecorator);
	}
	
	@Test
	public void adapterTestFailure() {
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/AbstractTarget", "examples/adapter/AdapterAbstractTarget"}; // add classes in here, you could

		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleAdapterTestTwo");
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertFalse(Adaptee instanceof AdapterDecorator);

		ITargetClass ITarget = i.next();
		assertFalse(ITarget instanceof AdapterDecorator);
		
		ITargetClass Adapter = i.next();
		assertFalse(Adapter instanceof AdapterDecorator);
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
		String[] classes = new String[] { "examples/adapter/Adaptee", "examples/adapter/ITarget", "examples/adapter/WeirdAdapter"}; // add classes in here, you could

		IProjectModel model = DetectionTestUtils.buildModelWithAllDetectors(classes, "docs/M5/SimpleAdapterTestTwo");
		Iterator<ITargetClass> i = model.getTargetClasses().iterator();

		ITargetClass Adaptee = i.next();
		assertFalse(Adaptee instanceof AdapterDecorator);

		ITargetClass ITarget = i.next();
		assertFalse(ITarget instanceof AdapterDecorator);
		
		ITargetClass Adapter = i.next();
		assertFalse(Adapter instanceof AdapterDecorator);
	}

}
