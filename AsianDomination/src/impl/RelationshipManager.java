package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import api.IRelationshipManager;
import classParser.AsmClassUtils;
import visitor.DotClassUtils.RelationshipType;
import visitor.IVisitor;

public class RelationshipManager implements IRelationshipManager {
	private HashMap<RelationshipType, Collection<String>> relationships;
	private Collection<String> classList;

	public RelationshipManager(String[] classes) {
		classList = new ArrayList<String>();
		for (String clazz : classes) {
			clazz = AsmClassUtils.GetStringStrippedByCharacter(clazz, '.');
			classList.add(clazz);
		}
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
		superClass = AsmClassUtils.GetStringStrippedByCharacter(superClass, '/');
		if (classList.contains(superClass)) {
			String edge = subClass + " -> " + superClass;
			
			if (!relationships.get(edgeType).contains(edge))
				relationships.get(edgeType).add(edge);
		}
	}

	@Override
	public Collection<String> getRelationshipEdges(RelationshipType edgeType) {
		return relationships.get(edgeType);
	}

}
