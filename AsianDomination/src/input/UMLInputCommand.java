package input;

import java.util.List;

import api.IProjectModel;
import construction.IAddStrategy;
import construction.UMLAddStrategy;
import output.IDiagramOutputStream;
import output.UMLDiagramOutputStream;
import pattern.detection.IDetectionVisitor;
import pattern.detection.IPatternDetectionStrategy;
import visitor.Visitor;

public class UMLInputCommand extends InputCommand{
	private String[] _classes;
	private String[] _inputParameters;
	private List<IPatternDetectionStrategy> _detectors;
	private List<IDetectionVisitor> _detectVisitors;
	
	public UMLInputCommand(String diagramOutputPath, String asmOutputPath, String[] inputParameters,List<IDetectionVisitor> detectionVisitor, List<IPatternDetectionStrategy> detectors) {
		super(diagramOutputPath, asmOutputPath);
		_inputParameters = inputParameters;
		_detectors = detectors;
		this._detectVisitors = detectionVisitor;
	}
	
	public String[] getClasses() {
		return _classes;
	}
	
	public String[] getInputParameters(){
		return _inputParameters;
	}
	
	public IAddStrategy getAddStrategy(IProjectModel model){
		return new UMLAddStrategy(_detectors, _detectVisitors,model);
	}
	
	public IDiagramOutputStream getOutputStream(){
		return new UMLDiagramOutputStream(_asmOutputPath + ".gv", new Visitor());
	}
}
