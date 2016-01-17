package input;

import java.lang.reflect.Array;
import java.util.ArrayList;

import construction.IAddStrategy;
import visitor.IDiagramOutputStream;

public class SequenceInputCommand extends InputCommand {
	private String _initialClass;
	private String _methodName;
	private int _maxCallDepth;

	public SequenceInputCommand(String diagramOutputPath, String asmOutputPath, String initialClass, String methodName,
			int maxCallDepth) {
		super(diagramOutputPath, asmOutputPath);
		this._initialClass = initialClass;
		this._methodName = methodName;
		this._maxCallDepth = maxCallDepth;

	}

	public String getInitialClass() {
		return _initialClass;
	}

	public String getMethodName() {
		return _methodName;
	}

	public int getMacCallDepth() {
		return _maxCallDepth;
	}

	public String[] getInputParameters(){
		String[] params = new String[]{
				getInitialClass(), getMethodName(), String.valueOf(getMacCallDepth())
		};
		return params;
	}
	public IAddStrategy getAddStrategy(){
		
		return new SDAddStrategy();
	}
	public IDiagramOutputStream getOutputStream(){
		return new SDDiagramOutputStream();
	}
}
