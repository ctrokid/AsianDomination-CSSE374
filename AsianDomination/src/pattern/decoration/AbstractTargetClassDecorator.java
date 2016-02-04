package pattern.decoration;

import java.util.Collection;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;
import visitor.IVisitor;

public abstract class AbstractTargetClassDecorator implements ITargetClass {
	protected PATTERN_TYPE pattern;
	protected String _associatedClassName;
	protected ITargetClass _decoratedClass;

	public AbstractTargetClassDecorator(PATTERN_TYPE pattern, String _associatedClassName,
			ITargetClass _decoratedClass) {
		this.pattern = pattern;
		this._associatedClassName = _associatedClassName;
		this._decoratedClass = _decoratedClass;
	}

	public String getAssociatedClassName() {
		return _associatedClassName;
	}

	public PATTERN_TYPE getPatternType() {
		return pattern;
	}

	@Override
	public String getClassName() {
		return _decoratedClass.getClassName();
	}

	@Override
	public Collection<IClassMethod> getMethods() {
		return _decoratedClass.getMethods();
	}

	@Override
	public Collection<IClassField> getFields() {
		return _decoratedClass.getFields();
	}

	@Override
	public void addClassMethod(IClassMethod classMethod) {
		_decoratedClass.addClassMethod(classMethod);

	}

	@Override
	public void addClassField(IClassField classField) {
		_decoratedClass.addClassField(classField);
	}

	@Override
	public IClassMethod getMethodByName(String methodName, String params) {
		return _decoratedClass.getMethodByName(methodName, params);
	}

	@Override
	public void setClassDeclaration(IClassDeclaration classDeclaration) {
		_decoratedClass.setClassDeclaration(classDeclaration);
	}

	@Override
	public IClassDeclaration getDeclaration() {
		return _decoratedClass.getDeclaration();
	}
	
	@Override
	public void setPatternString(String pattern) {
		_decoratedClass.setPatternString(pattern);
	}
	
	@Override
	public String getPatternString(boolean parseSlashes) {
		return _decoratedClass.getPatternString(parseSlashes);
	}

	@Override
	public void accept(IVisitor v) {
		_decoratedClass.accept(v);
	}

}
