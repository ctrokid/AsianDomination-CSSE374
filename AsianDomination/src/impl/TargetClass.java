package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import visitor.IVisitor;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;

public class TargetClass implements ITargetClass {
	private String _className;
//	private Collection<IClassMethod> _methodNameToClassMethod;
	private HashMap<String, IClassMethod> _methodNameToClassMethod;
	
	private Collection<IClassField> _fields;

	public TargetClass(String className) {
		_fields = new ArrayList<IClassField>();
		_methodNameToClassMethod = new HashMap<String, IClassMethod>();
		_className = className;
	}

	@Override
	public String getClassName() {
		return _className;
	}
	
	@Override
	public Collection<IClassMethod> getMethods() {
		Collection<IClassMethod> methods= new ArrayList<IClassMethod>();
		for (String key : _methodNameToClassMethod.keySet()) {
		   methods.add( _methodNameToClassMethod.get(key));
		}
		return methods;
	}

	@Override
	public Collection<IClassField> getFields() {
		return _fields;
	}

	@Override
	public void addClassMethod(IClassMethod classMethod) {
		String key = classMethod.getMethodName()+classMethod.getSignature();
		this._methodNameToClassMethod.put(key, classMethod);
	}

	@Override
	public void addClassField(IClassField classField) {
		this._fields.add(classField);
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);

	}

}