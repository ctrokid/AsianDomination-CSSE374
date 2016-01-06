package api;

import java.util.Collection;

import visitor.DotClassUtils.RelationshipType;

public interface IRelationshipManager extends ITargetClassPart {
	public void addRelationshipEdge(String subClass, String superClass, RelationshipType edgeType);
	public Collection<String> getRelationshipEdges(RelationshipType edgeType);
	
}