package pattern.detection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;
import impl.Relationship;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class DecoratorPatternDetector extends AbstractPatternDetectionStrategy {
	private IProjectModel _model = null;
	// private double METHOD_DELEGATION_PERCENTAGE_THRESHOLD = 1;

	public DecoratorPatternDetector(Properties props) {
		super(props);
	}

	@Override
	protected void loadConfig(Properties props) {
		// String md = props.getProperty("decorator-method-delegation");
		// if (md != null) {
		// try {
		// METHOD_DELEGATION_PERCENTAGE_THRESHOLD = Double.parseDouble(md);
		// } catch (NumberFormatException e) {
		// METHOD_DELEGATION_PERCENTAGE_THRESHOLD = 0.80;
		// }
		// } else {
		// METHOD_DELEGATION_PERCENTAGE_THRESHOLD = 0.80;
		// }
	}

	@Override
	protected void detectPatterns(IProjectModel model) {
		_model = model;

		for (ITargetClass clazz : _model.getTargetClasses()) {
			for (IClassMethod method : clazz.getMethods()) {

				if (method.getMethodName().equals("<init>")) {
					List<IClassField> matchingFields = getConstructorInstanceFields(method.getSignature(),
							clazz.getFields());

					for (IClassField field : matchingFields) {
						decorateClassIfDecorator(field, clazz);
					}
				}
			}
		}
	}

	private List<IClassField> getConstructorInstanceFields(String methodSig, Collection<IClassField> fields) {
		List<IClassField> matchingFields = new ArrayList<IClassField>();

		methodSig = AsmClassUtils.GetArguments(methodSig, false);
		String[] params = methodSig.split(",");

		for (String param : params) {
			for (IClassField field : fields) {

				// constructor param type equals field type
				if (field.getType().equals(param.trim())) {
					matchingFields.add(field);
				}

			}
		}

		return matchingFields;
	}

	private boolean decorateClassIfDecorator(IClassField field, ITargetClass clazz) {
		if (field.getType().equals("java/lang/Object") || clazz == null
				|| clazz.getClassName().equals("java/lang/Object"))
			return false;

		// check super type
		String superType = clazz.getDeclaration().getSuperClassType();

		if (superType.equals(field.getType())) {
			tagDecoratedClass(clazz, superType, RelationshipType.INHERITANCE);

			return true;
		}

		// recurse on super type
		if (decorateClassIfDecorator(field, _model.getTargetClassByName(superType))) {
			pc.decorate(PATTERN_TYPE.DECORATOR_COMPONENT, clazz, _model);
			return true;
		}

		for (String iface : clazz.getDeclaration().getInterfaces()) {
			// check interface
			if (iface.equals(field.getType())) {
				tagDecoratedClass(clazz, iface, RelationshipType.IMPLEMENTATION);

				return true;
			}

			// recurse on interface
			if (decorateClassIfDecorator(field, _model.getTargetClassByName(iface))) {
				pc.decorate(PATTERN_TYPE.DECORATOR_CONCRETE, clazz, _model);

				return true;
			}
		}

		return false;
	}

	private void tagDecoratedClass(ITargetClass clazz, String superType, RelationshipType relationshipType) {
		pc.decorate(PATTERN_TYPE.DECORATOR_DECORATOR, clazz, _model);
		Relationship r = _model.getRelationshipManager().getClassRelationship(clazz.getClassName(), relationshipType,
				superType);
		r.addDescription("label", "\"\\<\\<decorates\\>\\>\"");

		// add the component
		ITargetClass superClazz = _model.getTargetClassByName(superType);
		if (superClazz == null) {
			_model.addClass(superType);
		}

		superClazz = _model.getTargetClassByName(superType);
		pc.decorate(PATTERN_TYPE.DECORATOR_COMPONENT, superClazz, _model);

		tagConcreteDecorators(clazz.getClassName());
	}

	private void tagConcreteDecorators(String superType) {
		for (ITargetClass c : _model.getTargetClasses()) {
			// found concrete decorator
			if (_model.getRelationshipManager().getClassRelationship(c.getClassName(), RelationshipType.INHERITANCE,
					superType) != null) {
				pc.decorate(PATTERN_TYPE.DECORATOR_CONCRETE, c, _model);
			}
		}
	}

}
