package pattern.detection;

import java.util.Collection;
import java.util.Properties;

import org.objectweb.asm.Opcodes;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;

public class SingletonPatternDetector extends AbstractPatternDetectionStrategy {	
	private boolean REQUIRE_GET_INSTANCE;
	
	public SingletonPatternDetector(Properties props) {
		super(props);
	}
	
	@Override
	protected void loadConfig(Properties props) {
		String require = props.getProperty("singleton-require-getInstance");
		if (require != null && require.toLowerCase().equals("true"))
			REQUIRE_GET_INSTANCE = true;
		else
			REQUIRE_GET_INSTANCE = false;
	}

	@Override
	protected void detectPatterns(IProjectModel model) {
		for (ITargetClass clazz : model.getTargetClasses()) {
			if (isSingleton(clazz)) {
				pc.decorate(PATTERN_TYPE.SINGLETON, clazz, model);
//				clazz = new SingletonDecorator(PATTERN_TYPE.SINGLETON, "", clazz);
//				clazz.setPatternString("\\n\\<\\<Singleton\\>\\>");
//				clazz.
				
//				model.decorateClass(clazz);
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
				if (REQUIRE_GET_INSTANCE) {
					if (current.getMethodName().equals("getInstance"))
						return true;
				} else {
					return true;
				}
			}
		}
		return false;
	}
}
