package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import visitor.IVisitor;
import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import utils.AsmClassUtils;

public class TargetClass implements ITargetClass {
	private String _className;
	private IClassDeclaration _declaration;
	private HashMap<String, IClassMethod> _methodNameToClassMethod;
	private Collection<IClassField> _fields;
	
	private String _patternString;

	public TargetClass(String className) {
		_fields = new ArrayList<IClassField>();
		_declaration = null;
		_methodNameToClassMethod = new LinkedHashMap<String, IClassMethod>();
		_className = className;
		_patternString = "";
	}

	@Override
	public String getClassName() {
		return _className;
	}

	@Override
	public Collection<IClassMethod> getMethods() {
		Collection<IClassMethod> methods = new ArrayList<IClassMethod>();
		for (String key : _methodNameToClassMethod.keySet()) {
			methods.add(_methodNameToClassMethod.get(key));
		}
		return methods;
	}

	@Override
	public Collection<IClassField> getFields() {
		return _fields;
	}

	@Override
	public void addClassMethod(IClassMethod classMethod) {
		String key = classMethod.getMethodName() + AsmClassUtils.GetArguments(classMethod.getSignature(), true);
		this._methodNameToClassMethod.put(key, classMethod);
	}

	@Override
	public void addClassField(IClassField classField) {
		this._fields.add(classField);
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
		
		for (IClassField field : _fields) {
			field.accept(v);
		}

		v.postVisit(new ClassField(null, 0, null, null));

		for (IClassMethod method : getMethods()) {
			method.accept(v);
		}

		v.postVisit(this);
	}

	@Override
	public IClassMethod getMethodByName(String methodName, String params) {
		String key = methodName + params;
		return _methodNameToClassMethod.get(key);
	}

	@Override
	public void setClassDeclaration(IClassDeclaration classDeclaration) {
		_declaration = classDeclaration;
	}

	public IClassDeclaration getDeclaration() {
		return _declaration;
	}

	@Override
	public String getPatternString(boolean parseCarrots) {
		if (!parseCarrots)
			return _patternString;
		else
			if (_patternString.equals(""))
				return "";
			String pattern = _patternString.substring(6, _patternString.length() - 4);
			return pattern;
	}

	@Override
	public void setPatternString(String pattern) {
		_patternString = pattern;
	}
	
}