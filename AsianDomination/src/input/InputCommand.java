package input;

import api.IProjectModel;
import construction.IAddStrategy;
import output.IDiagramOutputStream;

public abstract class InputCommand {
	protected IDiagramOutputStream _diagramOutputStream;

	public InputCommand(IDiagramOutputStream outputStream) {
		_diagramOutputStream = outputStream;
	}
	
	public abstract String[] getInputParameters();
	public abstract IAddStrategy getAddStrategy(IProjectModel model);
	
	public IDiagramOutputStream getDiagramOutputStream() {
		return _diagramOutputStream;
	}
}
