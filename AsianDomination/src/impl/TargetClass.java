package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import visitor.IVisitor;
import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import utils.DotClassUtils.RelationshipType;

public class TargetClass implements ITargetClass {
	private String _className;
	private IClassDeclaration _declaration;
	private HashMap<String, IClassMethod> _methodNameToClassMethod;
	private HashMap<RelationshipType, HashSet<String>> _edges;

	private Collection<IClassField> _fields;

	public TargetClass(String className) {
		_fields = new ArrayList<IClassField>();
		_declaration = null;
		_methodNameToClassMethod = new LinkedHashMap<String, IClassMethod>();
		_edges = new HashMap<RelationshipType, HashSet<String>>();
		_className = className;
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
		String key = classMethod.getMethodName() + classMethod.getSignature();
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

	@Override
	public IClassMethod getMethodByName(String methodName, String params) {
		String key = methodName + params;
		return _methodNameToClassMethod.get(key);
	}

	@Override
	public void addRelationship(RelationshipType edgeType, String subjectClass) {
		if (_edges.containsKey(edgeType)) {
			_edges.get(edgeType).add(subjectClass);
		} else {
			HashSet<String> temp = new HashSet<String>();
			temp.add(subjectClass);
			_edges.put(edgeType, temp);
		}

	}

	@Override
	public HashMap<RelationshipType, HashSet<String>> getRelationEdges() {
		return _edges;
	}

	@Override
	public void addDeclarationVisitor(ClassDeclaration classDeclaration) {
		_declaration = classDeclaration;
	}

	public IClassDeclaration getDeclaration() {
		return _declaration;
	}

	@Override
	public boolean containsRelationship(RelationshipType edgeType, String subjectClass) {
		HashSet<String> particutlarEdges = _edges.get(edgeType);
		if (particutlarEdges != null) {
			if (particutlarEdges.contains(subjectClass)) {
				return true;
			}
		}
		return false;
	}

}