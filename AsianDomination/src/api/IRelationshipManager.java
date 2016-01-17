package api;

import java.util.Collection;
import impl.RelationshipManager.RelationshipEdge;
import utils.DotClassUtils.RelationshipType;

public interface IRelationshipManager extends ITargetClassPart {
	public void addRelationshipEdge(RelationshipEdge edge, RelationshipType edgeType);

	public Collection<RelationshipEdge> getRelationshipEdges(RelationshipType edgeType);

	boolean containsRelationshipEdge(RelationshipEdge edge, RelationshipType edgeType);
}
