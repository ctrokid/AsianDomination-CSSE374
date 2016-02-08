package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import api.IProjectModel;
import impl.ProjectModel;
import input.InputCommand;
import input.UMLInputCommand;
import output.IDiagramOutputStream;
import output.UMLDiagramOutputStream;
import pattern.detection.AdapterPatternDetector;
import pattern.detection.CompositePatternDetector;
import pattern.detection.DecoratorPatternDetector;
import pattern.detection.IDetectionVisitor;
import pattern.detection.IPatternDetectionStrategy;
import pattern.detection.SingletonDetectionVisitor;
import pattern.detection.SingletonPatternDetector;
import visitor.Visitor;

public class UMLConfiguration {
	private List<String> classes;
	private String outputDir;
	private String dotPath;
	private String[] phases;
	
	public UMLConfiguration() {
		readConfig();
	}
	
	private void readConfig() {
		Properties props = new Properties();
		
		try {
			FileInputStream in = new FileInputStream("resources/config.properties");
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		outputDir = props.getProperty("output-dir");
		dotPath = props.getProperty("dot-path");
		phases = props.getProperty("phases").split(",");
		
		// combine input-folder classes and input-classes into one String[]
		String inputDir = props.getProperty("input-folder");
		String[] packages = props.getProperty("input-packages").split(",");
		
		classes = PackageInspector.getClasses(inputDir, packages);
		String[] inputClasses = new String[] {};
		
		if (!props.getProperty("input-classes").equals("-")) {
			inputClasses = props.getProperty("input-classes").replace(".", "/").split(",");
		}		
		
		for (String clazz : inputClasses) {
			classes.add(clazz);
		}
	}
	
	public IProjectModel getProjectModel() {
		IDiagramOutputStream outputStream = new UMLDiagramOutputStream(outputDir, dotPath, new Visitor());
		List<IPatternDetectionStrategy> detectors = getPatternDetectors();
		List<IDetectionVisitor> visitors = getDetectionVisitors();
		
		String[] classList = new String[classes.size()];
		classList = classes.toArray(classList);
		
		InputCommand cmd = new UMLInputCommand(outputStream, classList, visitors, detectors);
		
		return new ProjectModel(cmd);
	}
	
	private List<IPatternDetectionStrategy> getPatternDetectors() {
		HashMap<String, IPatternDetectionStrategy> map = new HashMap<String, IPatternDetectionStrategy>();
		map.put("Decorator-Detection", new DecoratorPatternDetector());
		map.put("Singleton-Detection", new SingletonPatternDetector());
		map.put("Adapter-Detection", new AdapterPatternDetector());
		map.put("Composite-Detection", new CompositePatternDetector());
		
		List<IPatternDetectionStrategy> detectors = new ArrayList<IPatternDetectionStrategy>();
		
		for (String detection : phases) {
			IPatternDetectionStrategy detector = map.get(detection);
			
			if (detector != null)
				detectors.add(detector);
		}
		
		return detectors;
	}
	
	private List<IDetectionVisitor> getDetectionVisitors() {
		HashMap<String, IDetectionVisitor> map = new HashMap<String, IDetectionVisitor>();
		map.put("Singleton-Detection-Visitor", new SingletonDetectionVisitor(new Visitor()));
		
		List<IDetectionVisitor> visitors = new ArrayList<IDetectionVisitor>();
		
		for (String phase : phases) {
			IDetectionVisitor visitor = map.get(phase);
			
			if (visitor != null)
				visitors.add(visitor);
		}
		
		return visitors;
	}
}