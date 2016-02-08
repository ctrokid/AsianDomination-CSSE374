package unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utils.PackageInspector;

/**
 * These clases will cause problems on others' machines and will not pass.
 * The source directory to PackageInspector.getClasses() is machine specific.
 * @author trowbrct
 *
 */
public class TestPackageInspector {
	
	@Test
	public void testSimpleSrcDir() {
		List<String> classes = PackageInspector.getClasses("C:\\Users\\trowbrct\\Desktop\\CSSE374\\AsianDomination-CSSE374\\AsianDomination\\src", new String[] { "api" });
		List<String> expected = new ArrayList<String>();
		expected.add("api/IClassDeclaration");
		expected.add("api/IClassField");
		expected.add("api/IClassMethod");
		expected.add("api/IMethodStatement");
		expected.add("api/IProjectModel");
		expected.add("api/IRelationship");
		expected.add("api/IRelationshipManager");
		expected.add("api/ITargetClass");
		expected.add("api/ITargetClassPart");
		
		
		for (String clazz : classes) {
			assertTrue(expected.contains(clazz));
			expected.remove(clazz);
		}
		
		assertEquals(0, expected.size());
	}
	
	@Test
	public void testLabSrcDirTwoPackages() {
		List<String> classes = PackageInspector.getClasses("C:\\Users\\trowbrct\\Desktop\\CSSE374\\Labs\\Week 1\\1-3\\Lab1-3\\src", new String[] { "headfirst.observer.swing", "headfirst.observer.weather" });
		List<String> expected = new ArrayList<String>();
		expected.add("headfirst/observer/swing/SwingObserverExample");
		expected.add("headfirst/observer/weather/CurrentConditionsDisplay");
		expected.add("headfirst/observer/weather/DisplayElement");
		expected.add("headfirst/observer/weather/ForecastDisplay");
		expected.add("headfirst/observer/weather/HeatIndexDisplay");
		expected.add("headfirst/observer/weather/Observer");
		expected.add("headfirst/observer/weather/StatisticsDisplay");
		expected.add("headfirst/observer/weather/Subject");
		expected.add("headfirst/observer/weather/WeatherData");
		expected.add("headfirst/observer/weather/WeatherStation");
		expected.add("headfirst/observer/weather/WeatherStationHeatIndex");
		
		for (String clazz : classes) {
			assertTrue(expected.contains(clazz));
			expected.remove(clazz);
		}
		
		assertEquals(0, expected.size());
	}
}
