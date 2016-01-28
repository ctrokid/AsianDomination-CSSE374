package pattern.detection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;
import pattern.decoration.DecoraterTargetClass;
import utils.AsmClassUtils;

public class DecoratorPatternDetector implements IPatternDetectionStrategy {
	private IProjectModel _model = null;
	
	@Override
	public void detectPatterns(IProjectModel model) {
		_model = model;
		
		for (ITargetClass clazz : _model.getTargetClasses()) {
			for (IClassMethod method : clazz.getMethods()) {
				
				if (method.getMethodName().equals("<init>")) {
					List<IClassField> matchingFields = getConstructorInstanceFields(method.getSignature(), clazz.getFields());
					
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
		if (clazz == null)
			return false;
		
		// check super type
		String superType = clazz.getDeclaration().getSuperClassType();
		
		// detected the DECORATOR class.
		if (superType.equals(field.getType())) {
			clazz = new DecoraterTargetClass(PATTERN_TYPE.DECORATOR_DECORATOR, field.getType(), clazz);
			_model.decorateClass(clazz);
			
			// add the component
			ITargetClass superClazz = _model.getTargetClassByName(superType);
			superClazz = new DecoraterTargetClass(PATTERN_TYPE.DECORATOR_COMPONENT, "", superClazz);
			_model.decorateClass(superClazz);
			
			return true;
		}
		
		// recurse on super type
		if (decorateClassIfDecorator(field, _model.getTargetClassByName(superType))) {
			clazz = new DecoraterTargetClass(PATTERN_TYPE.DECORATOR_CONCRETE, superType, clazz);
			_model.decorateClass(clazz);
			
			return true;
		}
		
		for (String iface : clazz.getDeclaration().getInterfaces()) {
			// check interface
			if (iface.equals(field.getType())) {
				clazz = new DecoraterTargetClass(PATTERN_TYPE.DECORATOR_DECORATOR, iface, clazz);
				_model.decorateClass(clazz);
				
				ITargetClass superClazz = _model.getTargetClassByName(iface);
				superClazz = new DecoraterTargetClass(PATTERN_TYPE.DECORATOR_COMPONENT, "", superClazz);

				return true;
			}
			
			// recurse on interface
			if (decorateClassIfDecorator(field, _model.getTargetClassByName(iface))) {
				clazz = new DecoraterTargetClass(PATTERN_TYPE.DECORATOR_CONCRETE, iface, clazz);
				_model.decorateClass(clazz);
				
				return true;
			}
		}
		
		return false;
	}

}
