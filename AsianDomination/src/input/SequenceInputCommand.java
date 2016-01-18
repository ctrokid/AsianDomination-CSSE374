package input;

import construction.IAddStrategy;
import construction.SDAddStrategy;
import output.IDiagramOutputStream;
import output.SDDiagramOutputStream;

public class SequenceInputCommand extends InputCommand {
	private String _initialClass;
	private String _methodName;
	private int _maxCallDepth;
	private String _parameters;

	public SequenceInputCommand(String diagramOutputPath, String asmOutputPath, String initialClass, String methodName, String parameters,
			int maxCallDepth) {
		super(diagramOutputPath, asmOutputPath);
		this._initialClass = initialClass;
		this._methodName = methodName;
		this._maxCallDepth = maxCallDepth;
		this._parameters = parameters;
	}

	public String getInitialClass() {
		return _initialClass;
	}

	public String getMethodName() {
		return _methodName;
	}

	public int getMaxCallDepth() {
		return _maxCallDepth;
	}
	
	public String getParameters() {
		return _parameters;
	}

	public String[] getInputParameters(){
		String[] params = new String[]{
				getInitialClass(), getMethodName(), getParameters(), String.valueOf(getMaxCallDepth())
		};
		return params;
	}
	public IAddStrategy getAddStrategy(){
		
		return new SDAddStrategy();
	}
	public IDiagramOutputStream getOutputStream(){
		return new SDDiagramOutputStream(_asmOutputPath);
	}
}
