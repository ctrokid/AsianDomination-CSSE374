package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import impl.ClassDeclaration;
import utils.DotClassUtils.RelationshipType;
import visitor.ITraverser;

public interface ITargetClass extends ITraverser {
	public String getClassName();

	public Collection<IClassMethod> getMethods();

	public Collection<IClassField> getFields();

	public void addClassMethod(IClassMethod classMethod);

	public void addClassField(IClassField classField);

	public IClassMethod getMethodByName(String methodName, String params);

	public void addRelationship(RelationshipType edgeType, String subjectClass);

	public HashMap<RelationshipType, HashSet<String>> getRelationEdges();

	public void addDeclarationVisitor(ClassDeclaration classDeclaration);

	public IClassDeclaration getDeclaration();
	
	public boolean containsRelationship(RelationshipType edgeType, String subjectClass);
}
