package input;

import java.util.List;

import construction.IAddStrategy;
import construction.UMLAddStrategy;
import output.IDiagramOutputStream;
import output.UMLDiagramOutputStream;
import pattern.detection.IPatternDetectionStrategy;
import visitor.Visitor;

public class UMLInputCommand extends InputCommand{
	private String[] _classes;
	private String[] _inputParameters;
	private List<IPatternDetectionStrategy> _detectors;
	
	public UMLInputCommand(String diagramOutputPath, String asmOutputPath, String[] inputParameters, List<IPatternDetectionStrategy> detectors) {
		super(diagramOutputPath, asmOutputPath);
		_inputParameters = inputParameters;
		_detectors = detectors;
	}
	
	public String[] getClasses() {
		return _classes;
	}
	
	public String[] getInputParameters(){
		return _inputParameters;
	}
	
	public IAddStrategy getAddStrategy(){
		return new UMLAddStrategy(_detectors);
	}
	
	public IDiagramOutputStream getOutputStream(){
		return new UMLDiagramOutputStream(_asmOutputPath + ".gv", new Visitor());
	}
}
