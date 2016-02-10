package construction;

import java.util.Properties;

import api.IClassMethod;
import api.IMethodStatement;
import api.IProjectModel;
import api.ITargetClass;

public class SDAddStrategy extends AbstractAddStrategy {
	private int MAX_CALLDEPTH = 5;
	private IProjectModel _projectModel;
	
	public SDAddStrategy(Properties props) {
		super(props);
	}
	
	@Override
	protected void loadConfig(Properties props) {
		/*
		 * TODO: this needs to be configured to work again. We must have a separate properties file or something?
		 */
	}
	
	@Override
	public void buildModel(IProjectModel model) {
		_projectModel = model;
		model.addClass(_params[0]);
		addClassesRecursively(_params[0], _params[1], _params[2], 1);
	}

	private void addClassesRecursively(String className, String methodName, String params, int depth) {
		if (depth > MAX_CALLDEPTH) {
			return;
		}

		ITargetClass currentClass = _projectModel.getTargetClassByName(className);

		if (currentClass.equals(null)) {
			System.err.println("Cannot find class");
			return;
		}

		IClassMethod currentMethod = currentClass.getMethodByName(methodName, params);
		for (IMethodStatement currentStatement : currentMethod.getMethodStatements()) {
			String classToCall = currentStatement.getClassToCall();
			if (!modelContainClass(classToCall)) {
				_projectModel.addClass(classToCall);
			}
				addClassesRecursively(classToCall, currentStatement.getMethodName(), currentStatement.getParameters(), depth + 1);
			
		}
	}

	private boolean modelContainClass(String className) {
		if (_projectModel.getTargetClassByName(className) == null) {
			return false;
		}
		return true;
	}

}
