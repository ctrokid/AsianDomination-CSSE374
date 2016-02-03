package fake;

import impl.TargetClass;
import input.InputCommand;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import api.IProjectModel;
import api.ITargetClass;

public class FakeProjectModel implements IProjectModel {
	private InputCommand _cmd;
	private HashMap<String, ITargetClass> _targetClassNameToClass;
	
	public FakeProjectModel(InputCommand cmd) {
		_cmd = cmd;
		_targetClassNameToClass = new LinkedHashMap<String, ITargetClass>();
	}

	@Override
	public void addClass(String classPath) {
		if (_targetClassNameToClass.containsKey(classPath))
			return;
		
		_targetClassNameToClass.put(classPath, new TargetClass(classPath));
	}

	@Override
	public List<ITargetClass> getTargetClasses() {
		return null;
	}

	@Override
	public ITargetClass getTargetClassByName(String classPath) {
		return _targetClassNameToClass.get(classPath);
	}

	@Override
	public InputCommand getInputCommand() {
		return _cmd;
	}

	@Override
	public void decorateClass(ITargetClass clazz) {
	}

	@Override
	public void buildModel() {
	}

	@Override
	public void printModel() {
	}

	@Override
	public ITargetClass forcefullyGetClassByName(String className) {
		return null;
	}

}
