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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IClassMethod> getMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IClassField> getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addClassMethod(IClassMethod classMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClassField(IClassField classField) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IClassMethod getMethodByName(String methodName, String params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRelationship(RelationshipType edgeType, String subjectClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<RelationshipType, HashSet<String>> getRelationEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDeclarationVisitor(ClassDeclaration classDeclaration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IClassDeclaration getDeclaration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsRelationship(RelationshipType edgeType, String subjectClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void accept(IVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
