package pattern.detection;

import java.util.Collection;
import java.util.List;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.IMethodStatement;
import api.IProjectModel;
import api.ITargetClass;
import impl.Relationship;
import pattern.decoration.AdapterDecorator;
import utils.DotClassUtils.RelationshipType;

public class AdapterPatternDetector implements IPatternDetectionStrategy {

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
		if (fields.size() != 1) {
			return;
		}
		
		IClassField f = fields.iterator().next();
		String adaptee = f.getType();

		// check each methods
		Collection<IClassMethod> methods = clazz.getMethods();
		if (!isValidMethodCall(methods, adaptee)) {
			return;
		}

		// grab the adapter, adaptee, and target
		// TODO:FIXME This code is really ugly, maybe you can fix it later
		clazz = new AdapterDecorator(PATTERN_TYPE.ADAPTER_ADAPTER, "", clazz);
		
		ITargetClass adapteeClass = model.forcefullyGetClassByName(adaptee);
		
		ITargetClass targ = model.forcefullyGetClassByName(target.get(0));
		adapteeClass = new AdapterDecorator(PATTERN_TYPE.ADAPTER_ADAPTEE, "", adapteeClass);
		targ = new AdapterDecorator(PATTERN_TYPE.ADAPTER_TARGET, "", targ);
		
		Relationship adaptorRelation = clazz.getRelationship(RelationshipType.ASSOCIATION, adapteeClass.getClassName());
		adaptorRelation.setDecoratedType("\\<\\<adapts\\>\\>");

		model.decorateClass(clazz);
		model.decorateClass(adapteeClass);
		model.decorateClass(targ);
		return;
	}

	private boolean isValidMethodCall(Collection<IClassMethod> methods, String adaptee) {
		for (IClassMethod m : methods) {
			// don't check constructor
			if (m.getMethodName().contains("<"))
				continue;
			
			// check each statement
			Collection<IMethodStatement> statms = m.getMethodStatements();
			boolean isValid = false;
			for (IMethodStatement statm : statms) {
				if (statm.getClassToCall().equals(adaptee)) {
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
