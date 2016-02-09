package utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pattern.detection.AdapterPatternDetector;
import pattern.detection.CompositePatternDetector;
import pattern.detection.DecoratorPatternDetector;
import pattern.detection.IPatternDetectionStrategy;
import pattern.detection.SingletonPatternDetector;

public class PatternDetectionFactory {
	private static Map<String, Class<? extends IPatternDetectionStrategy>> detectors = new HashMap<String, Class<? extends IPatternDetectionStrategy>>(); 
	static{
		detectors.put("adapter", AdapterPatternDetector.class);
		detectors.put("composite", CompositePatternDetector.class);
		detectors.put("decorator", DecoratorPatternDetector.class);
		detectors.put("singleton", SingletonPatternDetector.class);
	}
	
	public static List<IPatternDetectionStrategy> getDetecotrs(String[] detectorConfig) throws Exception{
		List<IPatternDetectionStrategy> dete = new ArrayList<IPatternDetectionStrategy>();
		for(String s:detectorConfig){
			Class<? extends IPatternDetectionStrategy> clazz = detectors.get(s);
			Constructor<? extends IPatternDetectionStrategy> ctor = clazz.getDeclaredConstructor();
			dete.add(ctor.newInstance());
		}
		return dete;
	}
}

