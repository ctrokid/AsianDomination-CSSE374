package impl;

public class SequenceInputCommand extends InputCommand {
	private String _initialClass;
	private String _methodName;
	private int _callDepth;
	
	public SequenceInputCommand(String commandType, String[] classes, String initialClass, String methodName, int callDepth) {
		super(commandType, classes);
		_initialClass = initialClass;
		_methodName = methodName;
		_callDepth = callDepth;
	}

	public String getInitialClass() {
		return _initialClass;
	}

	public String getMethodName() {
		return _methodName;
	}

	public int getCallDepth() {
		return _callDepth;
	}

}
