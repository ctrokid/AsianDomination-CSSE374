package input;

import construction.IAddStrategy;
import construction.UMLAddStrategy;
import output.IDiagramOutputStream;
import output.UMLSingletonDiagramOutputStream;

public class UMLInputCommand extends InputCommand{
	private String[] _classes;
	private String[] _inputParameters;
	
	public UMLInputCommand(String diagramOutputPath, String asmOutputPath, String[] inputParameters) {
		super(diagramOutputPath, asmOutputPath);
		_inputParameters = inputParameters;
	}
	
	public String[] getClasses() {
		return _classes;
	}
	
	public String[] getInputParameters(){
		return _inputParameters;
	}
	
	public IAddStrategy getAddStrategy(){
		return new UMLAddStrategy();
	}
	
	public IDiagramOutputStream getOutputStream(){
		return new UMLSingletonDiagramOutputStream(_asmOutputPath + ".gv");
	}
}
