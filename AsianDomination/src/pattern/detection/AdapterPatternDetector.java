package pattern.detection;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.IMethodStatement;
import api.IProjectModel;
import api.ITargetClass;
import impl.Relationship;
import pattern.decoration.GraphVizStyleTargetClass;
import pattern.decoration.PatternConfig;
import utils.DotClassUtils.RelationshipType;

public class AdapterPatternDetector extends AbstractPatternDetectionStrategy {
	private double METHOD_DELEGATION_PERCENTAGE_THRESHOLD;

	public AdapterPatternDetector(Properties props) {
		super(props);
	}

	@Override
	protected void loadConfig(Properties props) {
		String md = props.getProperty("adapter-method-delegation");
		if (md != null) {
			try {
				METHOD_DELEGATION_PERCENTAGE_THRESHOLD = Double.parseDouble(md);
			} catch (NumberFormatException e) {
				METHOD_DELEGATION_PERCENTAGE_THRESHOLD = 0.80;
			}
		} else {
			METHOD_DELEGATION_PERCENTAGE_THRESHOLD = 0.80;
		}
	}

	@Override
	protected void detectPatterns(IProjectModel model) {
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
		pc.decorate(PATTERN_TYPE.ADAPTER_ADAPTER, clazz, model);

		ITargetClass adapteeClass = model.forcefullyGetClassByName(adaptee);

		ITargetClass targ = model.forcefullyGetClassByName(target.get(0));
		
		pc.decorate(PATTERN_TYPE.ADAPTER_ADAPTEE, adapteeClass, model);
		pc.decorate(PATTERN_TYPE.ADAPTER_TARGET, targ, model);

		Relationship adaptorRelation = model.getRelationshipManager().getClassRelationship(clazz.getClassName(),
				RelationshipType.ASSOCIATION, adapteeClass.getClassName());
		adaptorRelation.setDecoratedType("\\<\\<adapts\\>\\>");

		return;
	}

	private boolean isValidMethodCall(Collection<IClassMethod> methods, String adaptee) {
		int methodAmount = methods.size();
		int methodDelegations = 0;

		for (IClassMethod m : methods) {
			// don't check constructor
			if (m.getMethodName().contains("<")) {
				// remove this method from the total amount
				methodAmount--;
				continue;
			}

			// check each statement
			Collection<IMethodStatement> statms = m.getMethodStatements();
			for (IMethodStatement statm : statms) {
				if (statm.getClassToCall().equals(adaptee)) {
					methodDelegations++;
					break;
				}
			}
		}

		if (((double) methodDelegations / methodAmount) >= METHOD_DELEGATION_PERCENTAGE_THRESHOLD) {
			return true;
		}

		return false;
	}

}
