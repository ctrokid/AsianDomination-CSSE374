package input;

import java.util.List;

import api.IProjectModel;
import construction.IAddStrategy;
import construction.UMLAddStrategy;
import output.IDiagramOutputStream;
import pattern.detection.IDetectionVisitor;
import pattern.detection.IPatternDetectionStrategy;

public class UMLInputCommand extends InputCommand{
	private String[] _inputParameters;
	private List<IPatternDetectionStrategy> _detectors;
	private List<IDetectionVisitor> _detectionVisitors;
	
	public UMLInputCommand(IDiagramOutputStream outputStream, String[] inputParameters, List<IDetectionVisitor> detectionVisitor, List<IPatternDetectionStrategy> detectors) {
		super(outputStream);
		_inputParameters = inputParameters;
		_detectors = detectors;
		this._detectionVisitors = detectionVisitor;
	}

	@Override
	public String[] getInputParameters(){
		return _inputParameters;
	}
	
	@Override
	public IAddStrategy getAddStrategy(IProjectModel model){
		return new UMLAddStrategy(_detectors, _detectionVisitors, model);
	}
}
