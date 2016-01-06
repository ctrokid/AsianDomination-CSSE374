package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import api.IRelationshipManager;
import visitor.DotClassUtils.RelationshipType;
import visitor.IVisitor;

public class RelationshipManager implements IRelationshipManager {
	private HashMap<RelationshipType, Collection<String>> relationships;

	public RelationshipManager() {
		relationships = new HashMap<RelationshipType, Collection<String>>();
		
		for (RelationshipType type : RelationshipType.values()) {
			relationships.put(type, new ArrayList<String>());
		}
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

	@Override
	public void addRelationshipEdge(String subClass, String superClass, RelationshipType edgeType) {
		relationships.get(edgeType).add(subClass + " -> " + superClass);
	}

	@Override
	public Collection<String> getRelationshipEdges(RelationshipType edgeType) {
		return relationships.get(edgeType);
	}

}
