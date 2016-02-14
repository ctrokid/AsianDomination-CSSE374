package framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import construction.SDAddStrategy;
import construction.UMLAddStrategy;
import output.SDDiagramOutputStream;
import output.UMLDiagramOutputStream;
import pattern.detection.AdapterPatternDetector;
import pattern.detection.CompositePatternDetector;
import pattern.detection.DecoratorPatternDetector;
import pattern.detection.SingletonDetectionVisitor;
import pattern.detection.SingletonPatternDetector;

public class StaticPhaseFactory {
	private static Map<String, Class<? extends IPhase>> phases = new HashMap<String, Class<? extends IPhase>>(); 
	static{
		phases.put("UML-Class-Loading", UMLAddStrategy.class);
		phases.put("SD-Class-Loading", SDAddStrategy.class);
		phases.put("Adapter-Detection", AdapterPatternDetector.class);
		phases.put("Composite-Detection", CompositePatternDetector.class);
		phases.put("Decorator-Detection", DecoratorPatternDetector.class);
		phases.put("Singleton-Detection", SingletonPatternDetector.class);
		phases.put("Singleton-Detection-Visitor", SingletonDetectionVisitor.class);
		phases.put("DOT-Generation", UMLDiagramOutputStream.class);
		phases.put("SD-Generation", SDDiagramOutputStream.class);
	}
	
	public static List<IPhase> getPhases(Properties props){
		List<IPhase> phaseList = new ArrayList<IPhase>();
		String[] phaseString = props.getProperty("phases").split(",");
		
		for(String s : phaseString){
			Class<? extends IPhase> clazz = phases.get(s);
			if (clazz == null) {
				System.err.println("Configuration phase: " + phaseString + " is not recognized by the StaticPhaseFactory");
				return new ArrayList<IPhase>();
			}
			
			try {
				Constructor<? extends IPhase> ctor = clazz.getDeclaredConstructor(Properties.class);
				phaseList.add(ctor.newInstance(props));
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
	}
		
		return phaseList;
	}
}

