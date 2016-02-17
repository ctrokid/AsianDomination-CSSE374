package input;

import java.util.List;
import java.util.Observable;

import api.IProjectModel;
import framework.IPhase;
import impl.ProjectModel;

public class InputCommand extends Observable {
	private List<IPhase> _phases;
	private IProjectModel _model;
	private PhaseProgress _progress;
	
	public InputCommand(List<IPhase> phases) {
		_phases = phases;
		_model = new ProjectModel();
		_progress = new PhaseProgress();
	}
	
	public void execute() {
		for (int i = 0; i < _phases.size(); i++) {
			IPhase phase = _phases.get(i);
			double progress = (double) i / _phases.size();
			
			_progress.setCurrentPhase(phase.toString());
			_progress.setPercentage((int) Math.floor(progress * 100));
			
			this.setChanged();
			this.notifyObservers(_progress);
			
			phase.execute(_model);
		}
		
		_progress.setCurrentPhase("Diagram Analysis Successful!");
		_progress.setPercentage(100);
		
		this.setChanged();
		this.notifyObservers(_progress);
	}
	
	public IPhase getPhase(Class<? extends IPhase> clazz) {
		for (IPhase phase : _phases) {
			if (clazz.isInstance(phase)) {
				return phase;
			}
		}
		
		return null;
	}
	
	public IProjectModel getProjectModel() {
		return _model;
	}
}
