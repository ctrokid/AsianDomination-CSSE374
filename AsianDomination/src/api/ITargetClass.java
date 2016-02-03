package api;

import java.util.Collection;
import utils.DotClassUtils.RelationshipType;
import visitor.ITraverser;

public interface ITargetClass extends ITraverser {
	public String getClassName();
	
	public String getPatternString(boolean parseCarrots);
	
	public void setPatternString(String pattern);

	public Collection<IClassMethod> getMethods();

	public Collection<IClassField> getFields();

	public void addClassMethod(IClassMethod classMethod);

	public void addClassField(IClassField classField);

	public IClassMethod getMethodByName(String methodName, String params);

//	public void addRelationship(RelationshipType edgeType, String subjectClass);

//	public List<Relationship> getRelationEdges();
	
//	public Relationship getRelationship(RelationshipType type, String subjectClass);

	public void setClassDeclaration(IClassDeclaration classDeclaration);

	public IClassDeclaration getDeclaration();
	
	public boolean containsRelationship(RelationshipType edgeType, String subjectClass);
}
