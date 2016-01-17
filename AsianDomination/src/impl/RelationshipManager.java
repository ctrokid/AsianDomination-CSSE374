package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import api.IRelationshipManager;
import utils.DotClassUtils.RelationshipType;
import visitor.IVisitor;

public class RelationshipManager implements IRelationshipManager {

	private HashMap<RelationshipType, Collection<RelationshipEdge>> relationships;

	public RelationshipManager() {

		relationships = new HashMap<RelationshipType, Collection<RelationshipEdge>>();

		for (RelationshipType type : RelationshipType.values()) {
			relationships.put(type, new ArrayList<RelationshipEdge>());
		}
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

	@Override
	public void addRelationshipEdge(RelationshipEdge edge, RelationshipType edgeType) {
		if (!containsRelationshipEdge(edge, edgeType))
			relationships.get(edgeType).add(edge);
	}

	@Override
	public Collection<RelationshipEdge> getRelationshipEdges(RelationshipType edgeType) {
		return relationships.get(edgeType);
	}

	@Override
	public boolean containsRelationshipEdge(RelationshipEdge edge, RelationshipType edgeType) {
		return relationships.get(edgeType).contains(edge);
	}

	public class RelationshipEdge {

		String _superClass;
		String _subclass;

		public RelationshipEdge(String _superClass, String _subclass) {
			this._superClass = _superClass;
			this._subclass = _subclass;
		}

		public String get_superClass() {
			return _superClass;
		}

		public String get_subclass() {
			return _subclass;
		}

		private RelationshipManager getOuterType() {
			return RelationshipManager.this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((_subclass == null) ? 0 : _subclass.hashCode());
			result = prime * result + ((_superClass == null) ? 0 : _superClass.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RelationshipEdge other = (RelationshipEdge) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (_subclass == null) {
				if (other._subclass != null)
					return false;
			} else if (!_subclass.equals(other._subclass))
				return false;
			if (_superClass == null) {
				if (other._superClass != null)
					return false;
			} else if (!_superClass.equals(other._superClass))
				return false;
			return true;
		}

	}

}
