package fake;

import output.IDiagramOutputStream;
import api.IProjectModel;
import construction.IAddStrategy;
import input.InputCommand;

public class FakeInputCommand extends InputCommand {
	private String[] _params;
	private IAddStrategy _addStrat;
	private IDiagramOutputStream _outStrat;
	
	public boolean calledGetInputParameters;
	public boolean calledGetAddStrategy;
	public boolean calledGetOutputStream;
	
	public FakeInputCommand(String diagramOutputPath, String asmOutputPath, String[] params) {
		super(diagramOutputPath, asmOutputPath);
		calledGetInputParameters = false;
		calledGetAddStrategy = false;
		calledGetOutputStream = false;
		
		_addStrat = null;
		_outStrat = null;
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
	
	public void setOutputStream(IDiagramOutputStream stream) {
		_outStrat = stream;
	}

	@Override
	public IDiagramOutputStream getOutputStream() {
		calledGetOutputStream = true;
		return _outStrat;
	}

}
