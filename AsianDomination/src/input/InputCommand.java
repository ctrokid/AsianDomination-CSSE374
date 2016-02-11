package input;

import java.util.List;

import api.IProjectModel;
import framework.IPhase;
import impl.ProjectModel;

public class InputCommand {
	private List<IPhase> _phases;
	private IProjectModel _model;
	
	public InputCommand(List<IPhase> phases) {
		_phases = phases;
		_model = new ProjectModel();
	}
	
	public void execute() {
		for (IPhase phase : _phases) {
			phase.execute(_model);
		}
	}
	
	public IProjectModel getProjectModel() {
		return _model;
	}
}
