package pattern.detection;

import java.util.Collection;
import java.util.Properties;

import org.objectweb.asm.Opcodes;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;
import pattern.decoration.SingletonDecorator;
import visitor.ITraverser;
import visitor.IVisitor;
import visitor.VisitType;
import visitor.Visitor;

public class SingletonDetectionVisitor extends AbstractDetectionVisitor {
	private IProjectModel _projectModel;
	private IVisitor _visitor;
	private boolean REQUIRE_GET_INSTANCE = false;

	public SingletonDetectionVisitor(Properties config, IVisitor visitor) {
		super(config);
		_visitor = visitor;
		this.setupVisitTargetClass();
	}
	
	public SingletonDetectionVisitor(Properties config) {
		super(config);
		_visitor = new Visitor();
		this.setupVisitTargetClass();
	}
	
	@Override
	protected void loadConfig(Properties props) {
		String require = props.getProperty("singleton-require-getInstance");
		if (require != null && require.toLowerCase().equals("true"))
			REQUIRE_GET_INSTANCE = true;
	}

	private void setupVisitTargetClass() {
		_visitor.addVisit(VisitType.Visit, ITargetClass.class, (ITraverser t) -> {
			ITargetClass c = (ITargetClass) t;
			if (isSingleton(c)) {
				c = new SingletonDecorator(PATTERN_TYPE.SINGLETON, "", c);
				c.setPatternString("\\n\\<\\<Singleton\\>\\>");
				this._projectModel.decorateClass(c);
			}
		});
	}

	private boolean isSingleton(ITargetClass t) {
		String classType = t.getClassName();
		if (fieldContainsClassInstance(t, classType)) {
			if (isConstructorPrivate(t)) {
				if (returnsSelfInstance(t, classType)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean fieldContainsClassInstance(ITargetClass t, String classType) {
		Collection<IClassField> fields = t.getFields();
		for (IClassField current : fields) {
			if ((current.getAccessLevel() & Opcodes.ACC_STATIC) != 0 && current.getType().equals(classType)) {
				return true;
			}
		}
		return false;
	}

	private boolean isConstructorPrivate(ITargetClass t) {
		Collection<IClassMethod> methods = t.getMethods();
		for (IClassMethod current : methods) {
			if (current.getMethodName().contains("<init>") && (current.getAccessLevel() & Opcodes.ACC_PRIVATE) == 0) {
				return false;
			}
		}
		return true;
	}

	private boolean returnsSelfInstance(ITargetClass t, String classType) {
		Collection<IClassMethod> methods = t.getMethods();
		for (IClassMethod current : methods) {
			if (current.getReturnType().equals(classType) && (current.getAccessLevel() & Opcodes.ACC_STATIC) != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute(IProjectModel model) {
		_projectModel = model;
		
		for (ITargetClass clazz : model.getTargetClasses()) {
			clazz.accept(_visitor);
		}
	}

}
