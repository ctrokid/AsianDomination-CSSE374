package impl;

import java.util.ArrayList;
import java.util.Collection;

import visitor.IVisitor;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;

public class TargetClass implements ITargetClass {
	private String _className;
	private Collection<IClassMethod> _methodNameToClassMethod;
	private Collection<IClassField> _fieldNameToClassField;

	public TargetClass(String className) {
		_fieldNameToClassField = new ArrayList<IClassField>();
		_methodNameToClassMethod = new ArrayList<IClassMethod>();
		_className = className;
	}

	@Override
	public String getClassName() {
		return _className;
	}

	@Override
	public Collection<IClassMethod> getMethods() {
		return _methodNameToClassMethod;
	}

	@Override
	public Collection<IClassField> getFields() {
		return _fieldNameToClassField;
	}

	@Override
	public void addClassMethod(IClassMethod classMethod) {
		this._methodNameToClassMethod.add(classMethod);
	}

	@Override
	public void addClassField(IClassField classField) {
		this._fieldNameToClassField.add(classField);
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

}