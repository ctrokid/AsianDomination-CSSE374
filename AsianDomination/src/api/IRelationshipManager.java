package api;

import java.util.Collection;
import impl.RelationshipManager.RelationshipEdge;
import utils.DotClassUtils.RelationshipType;

public interface IRelationshipManager extends ITargetClassPart {
	public void addRelationshipEdge( String _subClass, String _superClass, RelationshipType edgeType);

	public Collection<RelationshipEdge> getRelationshipEdges(RelationshipType edgeType);

	boolean containsRelationshipEdge( String _subClass, String _superClass, RelationshipType edgeType);
}
