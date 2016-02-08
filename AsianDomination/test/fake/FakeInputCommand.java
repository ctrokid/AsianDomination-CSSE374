package fake;

import output.IDiagramOutputStream;
import api.IProjectModel;
import construction.IAddStrategy;
import input.InputCommand;

public class FakeInputCommand extends InputCommand {
	private String[] _params;
	private IAddStrategy _addStrat;
	
	public boolean calledGetInputParameters;
	public boolean calledGetAddStrategy;
	public boolean calledGetOutputStream;
	
	public FakeInputCommand(IDiagramOutputStream outStream, String[] params) {
		super(outStream);
		calledGetInputParameters = false;
		calledGetAddStrategy = false;
		calledGetOutputStream = false;
		
		_addStrat = null;
		_params = params;
	}

	public void setInputParameters(String[] params) {
		_params = params;
	}
	
	@Override
	public String[] getInputParameters() {
		calledGetInputParameters = true;
		return _params;
	}

	public void setAddStrategy(IAddStrategy strat) {
		_addStrat = strat;
	}
	
	@Override
	public IAddStrategy getAddStrategy(IProjectModel model) {
		calledGetAddStrategy = true;
		return _addStrat;
	}

	@Override
	public IDiagramOutputStream getDiagramOutputStream() {
		calledGetOutputStream = true;
		return _diagramOutputStream;
	}

}
