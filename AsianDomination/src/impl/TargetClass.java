package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import visitor.IVisitor;
import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class TargetClass implements ITargetClass {
	private String _className;
	private IClassDeclaration _declaration;
	private HashMap<String, IClassMethod> _methodNameToClassMethod;
	private HashMap<RelationshipType, List<Relationship>> _edges;
	private Collection<IClassField> _fields;
	
	private String _patternString;

	public TargetClass(String className) {
		_fields = new ArrayList<IClassField>();
		_declaration = null;
		_methodNameToClassMethod = new LinkedHashMap<String, IClassMethod>();
		_className = className;
		_patternString = "";

		_edges = new HashMap<RelationshipType, List<Relationship>>();
		for (RelationshipType type : RelationshipType.values()) {
			_edges.put(type, new ArrayList<Relationship>());
		}
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
	public void addRelationship(RelationshipType edgeType, String subjectClass) {
		if (edgeType.equals(RelationshipType.USES) && _className.equals(subjectClass))
			return;
		
		Relationship r = new Relationship(subjectClass, edgeType);

		if (_edges.containsKey(edgeType)) {
			_edges.get(edgeType).add(r);
		} else {
			List<Relationship> temp = new ArrayList<Relationship>();
			temp.add(r);
			_edges.put(edgeType, temp);
		}
	}

	@Override
	public void setClassDeclaration(IClassDeclaration classDeclaration) {
		_declaration = classDeclaration;
	}

	public IClassDeclaration getDeclaration() {
		return _declaration;
	}

	@Override
	public boolean containsRelationship(RelationshipType edgeType, String subjectClass) {
		List<Relationship> particutlarEdges = _edges.get(edgeType);

		for (Relationship r : particutlarEdges) {
			if (r.getDependentClass().equals(subjectClass))
				return true;
		}
		return false;
	}

	@Override
	public List<Relationship> getRelationEdges() {
		List<Relationship> relationships = new ArrayList<Relationship>();

		for (RelationshipType type : RelationshipType.values()) {
			for (Relationship r : _edges.get(type)) {
				relationships.add(r);
			}
		}

		return relationships;
	}

	@Override
	public Relationship getRelationship(RelationshipType type, String subjectClass) {
		List<Relationship> relationships = _edges.get(type);

		for (Relationship r : relationships) {
			if (r.getDependentClass().equals(subjectClass))
				return r;
		}

		return null;
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