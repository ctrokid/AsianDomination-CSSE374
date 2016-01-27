package pattern.decoration;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import impl.ClassDeclaration;
import pattern.detection.PATTERN_TYPE;
import utils.DotClassUtils.RelationshipType;
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
	public void addRelationship(RelationshipType edgeType, String subjectClass) {
		_decoratedClass.addRelationship(edgeType, subjectClass);
	}

	@Override
	public HashMap<RelationshipType, HashSet<String>> getRelationEdges() {
		return _decoratedClass.getRelationEdges();
	}

	@Override
	public void addDeclarationVisitor(ClassDeclaration classDeclaration) {
		_decoratedClass.addDeclarationVisitor(classDeclaration);
	}

	@Override
	public IClassDeclaration getDeclaration() {
		return _decoratedClass.getDeclaration();
	}

	@Override
	public boolean containsRelationship(RelationshipType edgeType, String subjectClass) {
		return _decoratedClass.containsRelationship(edgeType, subjectClass);
	}

	@Override
	public void accept(IVisitor v) {
		_decoratedClass.accept(v);
	}

}
