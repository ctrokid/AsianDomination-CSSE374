package PatternDetectionTests;

import java.util.List;

import api.IProjectModel;
import framework.IPhase;
import input.InputCommand;

public class DetectionTestUtils {
	
	/**
	 * Run model with selected detection phases.
	 * NOTE: The first phase must be a UMLAddStrategy.
	 */
	public static IProjectModel getPatternDetectedModel(List<IPhase> detectionPhases) {
		InputCommand cmd = new InputCommand(detectionPhases);
		cmd.execute();
		
		return cmd.getProjectModel();
	}
}
