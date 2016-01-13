package impl;

public class InputCommand {
	private String _commandType;
	private String[] _classes;
	
	public InputCommand(String commandType, String[] classes) {
		_commandType = commandType;
		_classes = classes;
	}

	public String getCommandType() {
		return _commandType;
	}

	public String[] getClasses() {
		return _classes;
	}
}
