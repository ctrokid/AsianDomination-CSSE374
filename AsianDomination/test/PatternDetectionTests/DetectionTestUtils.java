package PatternDetectionTests;

import java.util.Arrays;
import java.util.List;

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
import visitor.Visitor;

public class DetectionTestUtils {
	
	/**
	 * This returns a project model with all current pattern detections.
	 * If you are testing a new pattern detection algorithm, you must at it to the respective list in the method below.
	 * 
	 * @param classes
	 * @param path
	 * @return IProjectModel configured with all pattern detectors.
	 */
	public static IProjectModel buildModelWithAllDetectors(String[] classes, String path) {
		List<IPatternDetectionStrategy> detectors = Arrays.asList(new AdapterPatternDetector(), new CompositePatternDetector(), new DecoratorPatternDetector());
		List<IDetectionVisitor> detectVisitors = Arrays.asList(new SingletonDetectionVisitor(new Visitor()));
		IDiagramOutputStream out = new UMLDiagramOutputStream(path, "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe", new Visitor());
		
		InputCommand cmd = new UMLInputCommand(out, classes, detectVisitors, detectors);
		IProjectModel model = new ProjectModel(cmd);
		model.buildModel();
		return model;
	}
}
