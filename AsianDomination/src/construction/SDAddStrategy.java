package construction;

import api.IClassMethod;
import api.IMethodStatement;
import api.IProjectModel;
import api.ITargetClass;

public class SDAddStrategy extends AbstractAddStrategy {
	private int MAX_CALLDEPTH = 5;

	public SDAddStrategy(int maxCallDepth, IProjectModel model) {
		super(model);
		MAX_CALLDEPTH = maxCallDepth;
	}
	
	@Override
	public void buildModel(String[] params) {
		if (_projectModel == null)
			return;
		
		super._projectModel.addClass(params[0]);
		addClassesRecursively(params[0], params[1], params[2], 1);
	}

	private void addClassesRecursively(String className, String methodName, String params, int depth) {
		if (depth > MAX_CALLDEPTH) {
			return;
		}

		ITargetClass currentClass = super._projectModel.getTargetClassByName(className);

		if (currentClass.equals(null)) {
			System.err.println("Cannot find class");
			return;
		}

		IClassMethod currentMethod = currentClass.getMethodByName(methodName, params);
		for (IMethodStatement currentStatement : currentMethod.getMethodStatements()) {
			String classToCall = currentStatement.getClassToCall();
			if (!modelContainClass(classToCall)) {
				super._projectModel.addClass(classToCall);
			}
				addClassesRecursively(classToCall, currentStatement.getMethodName(), currentStatement.getParameters(), depth + 1);
			
		}
	}

	private boolean modelContainClass(String className) {
		if (super._projectModel.getTargetClassByName(className) == null) {
			return false;
		}
		return true;
	}

}
