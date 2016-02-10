package fake;

import impl.RelationshipManager;
import impl.TargetClass;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;

public class FakeProjectModel implements IProjectModel {
	private HashMap<String, ITargetClass> _targetClassNameToClass;
	private IRelationshipManager _relationshipManager;
	
	public FakeProjectModel() {
		_targetClassNameToClass = new LinkedHashMap<String, ITargetClass>();
		_relationshipManager = new RelationshipManager();
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
	public void decorateClass(ITargetClass clazz) {
	}

	@Override
	public ITargetClass forcefullyGetClassByName(String className) {
		return null;
	}

	@Override
	public IRelationshipManager getRelationshipManager() {
		return _relationshipManager;
	}

}
