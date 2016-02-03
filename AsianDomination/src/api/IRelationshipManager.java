package api;

import java.util.List;
import java.util.Set;

import impl.Relationship;
import utils.DotClassUtils.RelationshipType;

public interface IRelationshipManager {
	public void addRelationship(String subject, RelationshipType relation, String object);
	public List<Relationship> getClassRelationships(String subject);
	public Relationship getClassRelationship(String subject, RelationshipType relation, String object);
	public Set<String> getClassSuperTypes(String subject, IProjectModel model);
	public List<String> getClassSubClasses(String subject);
}
