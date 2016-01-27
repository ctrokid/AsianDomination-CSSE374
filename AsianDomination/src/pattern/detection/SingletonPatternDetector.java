package pattern.detection;

import java.util.Collection;

import org.objectweb.asm.Opcodes;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;

public class SingletonPatternDetector implements IPatternDetectionStrategy {
	public static enum SINGLETON_PATTERN {
		SINGLETON
	}
	
	@Override
	public void detectPatterns(IProjectModel model) {
		for (ITargetClass clazz : model.getTargetClasses()) {
			if (isSingleton(clazz)) {
				// TODO : DECORATE WITH SINGLETON DETECTION
			}
		}
	}

	private boolean isSingleton(ITargetClass t){
		String classType = t.getClassName();
		if(fieldContainsClassInstance(t, classType)){
			if(isConstructorPrivate(t)){
				if(returnsSelfInstance(t, classType)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean fieldContainsClassInstance(ITargetClass t, String classType){
		Collection<IClassField> fields = t.getFields();
		for(IClassField current: fields) {
			if((current.getAccessLevel() & Opcodes.ACC_STATIC) != 0 && current.getType().equals(classType)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isConstructorPrivate(ITargetClass t){
		Collection<IClassMethod> methods = t.getMethods();
		for(IClassMethod current : methods){
			if(current.getMethodName().contains("<init>") && (current.getAccessLevel() & Opcodes.ACC_PRIVATE) == 0){
				return false;
			}
		}
		return true;
	}
	
	private boolean returnsSelfInstance(ITargetClass t, String classType){
		Collection<IClassMethod> methods = t.getMethods();
		for(IClassMethod current : methods){
			if(current.getReturnType().equals(classType) && (current.getAccessLevel() & Opcodes.ACC_STATIC) != 0){
				return true;
			}
		}
		return false;
	}
}
