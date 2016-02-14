package construction;

import java.util.Properties;

import api.IClassMethod;
import api.IMethodStatement;
import api.IProjectModel;
import api.ITargetClass;

public class SDAddStrategy extends AbstractAddStrategy {
	private int MAX_CALL_DEPTH = 5;
	private IProjectModel _projectModel;
	
	public SDAddStrategy(Properties props) {
		super(props);
	}
	
	@Override
	protected void loadConfig(Properties props) {
		_params = new String[3];
		// load SD specific parameters
		String initialClass = props.getProperty("initial-class");
		if (initialClass != null) {
			_params[0] = initialClass;
		} else {
			System.err.println("Must provide initial-class config value.");
			System.exit(-1);
		}
		
		String initialMethod = props.getProperty("initial-method");
		if (initialMethod != null) {
			_params[1] = initialMethod;
		} else {
			System.err.println("Must provide initial-method config value.");
			System.exit(-1);
		}
		
		String initialParams = props.getProperty("initial-method-parameters");
		if (initialParams != null) {
			_params[2] = initialParams;
		} else {
			System.err.println("Must provide initial-method-parameters config value.");
			System.exit(-1);
		}
		
		String maxCallDepth = props.getProperty("max-call-depth");
		if (maxCallDepth != null) {
			try {
				MAX_CALL_DEPTH = Integer.parseInt(maxCallDepth);
			} catch (NumberFormatException e) {}
		}
	}
	
	@Override
	public void buildModel(IProjectModel model) {
		_projectModel = model;
		model.addClass(_params[0]);
		addClassesRecursively(_params[0], _params[1], _params[2], 1);
	}

	private void addClassesRecursively(String className, String methodName, String params, int depth) {
		if (depth > MAX_CALL_DEPTH) {
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
