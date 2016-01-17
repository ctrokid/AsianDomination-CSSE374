package fake;

import impl.RelationshipManager;
import impl.TargetClass;
import input.InputCommand;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;

public class FakeProjectModel implements IProjectModel {
	private InputCommand _cmd;
	private IRelationshipManager _relationshipManager;
	private HashMap<String, ITargetClass> _targetClassNameToClass;
	
	public FakeProjectModel(InputCommand cmd) {
		_cmd = cmd;
		_targetClassNameToClass = new LinkedHashMap<String, ITargetClass>();
		_relationshipManager = new RelationshipManager();
	}
	
	@Override
	public void parseModel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addClass(String classPath) {
		if (_targetClassNameToClass.containsKey(classPath))
			return;
		
		_targetClassNameToClass.put(classPath, new TargetClass(classPath));
	}

	@Override
	public List<ITargetClass> getTargetClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITargetClass getTargetClassByName(String classPath) {
		return _targetClassNameToClass.get(classPath);
	}

	@Override
	public IRelationshipManager getRelationshipManager() {
		return _relationshipManager;
	}

	@Override
	public InputCommand getInputCommand() {
		return _cmd;
	}

}
