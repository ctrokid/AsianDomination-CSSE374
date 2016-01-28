package pattern.detection;

import java.util.Collection;
import java.util.List;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.IMethodStatement;
import api.IProjectModel;
import api.ITargetClass;
import pattern.decoration.AdapteeDecorator;
import pattern.decoration.AdapterDecorator;
import pattern.decoration.TargetDecorator;

public class AdaptorPatternDetector implements IPatternDetectionStrategy {

	@Override
	public void detectPatterns(IProjectModel model) {
		for (ITargetClass clazz : model.getTargetClasses()) {
			detectAdapter(clazz, model);
		}
	}

	private void detectAdapter(ITargetClass clazz, IProjectModel model) {
		IClassDeclaration dec = clazz.getDeclaration();
		List<String> target = dec.getInterfaces();
		// check interface
		if (target.size() != 1) {
			return;
		}
		// check field
		Collection<IClassField> fields = clazz.getFields();
		IClassField f = fields.iterator().next();
		String adaptee = f.getType();

		// check each methods
		Collection<IClassMethod> methods = clazz.getMethods();
		if (!isValidMethodCall(methods, adaptee)) {
			return;
		}

		// decorate adaptee, and client
		clazz = new AdapterDecorator(clazz);
		model.decorateClass(clazz);

		ITargetClass adapteee = model.getTargetClassByName(adaptee);
		adapteee = new AdapteeDecorator(adapteee);
		model.decorateClass(adapteee);

		ITargetClass targ = model.getTargetClassByName(target.get(0));
		targ = new TargetDecorator(targ);
		model.decorateClass(targ);

		return;
	}

	private boolean isValidMethodCall(Collection<IClassMethod> methods, String adaptee) {
		for (IClassMethod m : methods) {
			// check each statement
			Collection<IMethodStatement> statms = m.getMethodStatements();
			boolean isValid = false;
			for (IMethodStatement statm : statms) {
				if (statm.getCallerClass().equals(adaptee)) {
					isValid = true;
					break;
				}
			}
			if (!isValid) {
				return false;
			}
		}
		return true;
	}

}
