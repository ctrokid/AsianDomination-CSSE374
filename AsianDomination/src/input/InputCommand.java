package input;

import api.IProjectModel;
import construction.IAddStrategy;
import output.IDiagramOutputStream;

public abstract class InputCommand {
	protected String _diagramOutputPath;
	protected String _asmOutputPath;

	public InputCommand(String diagramOutputPath, String asmOutputPath ) {
		_diagramOutputPath = diagramOutputPath;
		_asmOutputPath = asmOutputPath;
	}
	
	public String getDiagramOutputPath(){
		return _diagramOutputPath;
	}
	
	public String getAsmOutputPath(){
		return _asmOutputPath;
	}
	
	public abstract String[] getInputParameters();
	public abstract IAddStrategy getAddStrategy(IProjectModel model);
	public abstract IDiagramOutputStream getOutputStream();
}
