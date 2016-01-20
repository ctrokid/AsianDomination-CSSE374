package construction;

import api.IClassMethod;
import api.IMethodStatement;
import api.ITargetClass;

public class SDAddStrategy extends AbstractAddStrategy {
	private int MAX_CALLDEPTH = 5;

	public SDAddStrategy(int maxCallDepth) {
		MAX_CALLDEPTH = maxCallDepth;
	}
	
	@Override
	public void buildModel(String[] params) {
		super._projectModel.addClass(params[0]);
		addClassesRecursively(params[0], params[1], params[2], 1, Integer.parseInt(params[3]));
	}

	private void addClassesRecursively(String className, String methodName, String params, int depth,
			int sequenceLevel) {
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
			currentStatement.setSequencelevel(depth);
			String classToCall = currentStatement.getClassToCall();
			if (!modelContainClass(classToCall)) {
				super._projectModel.addClass(classToCall);
				addClassesRecursively(classToCall, currentStatement.getMethodName(), currentStatement.getParameter(),
						depth + 1, sequenceLevel + 1);
			}

		}

	}

	private boolean modelContainClass(String className) {
		if (super._projectModel.getTargetClassByName(className) == null) {
			return false;
		}
		return true;
	}

}
