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
	
	private boolean containsRelationshipEdge(RelationshipEdge edge, RelationshipType edgeType) {
		return relationships.get(edgeType).contains(edge);
	}
	
	@Override
	public void addRelationshipEdge(String _subClass, String _superClass, RelationshipType edgeType) {
		RelationshipEdge edge = new RelationshipEdge(_subClass,_superClass);
		
		if (_subClass.equals(_superClass) && edgeType.equals(RelationshipType.USES))
			return;
			
		// TODO : let's talk about this. Email from Chandan added the second condition to the if statement.
		if (!relationships.get(edgeType).contains(edge) && !relationships.get(RelationshipType.INHERITANCE).contains(edge))
			relationships.get(edgeType).add(edge);
		
	}

	@Override
	public Collection<RelationshipEdge> getRelationshipEdges(RelationshipType edgeType) {
		return relationships.get(edgeType);
	}

	@Override
	public boolean containsRelationshipEdge(String _subClass, String _superClass, RelationshipType edgeType) {
		RelationshipEdge edge = new RelationshipEdge(_subClass, _superClass);
		return containsRelationshipEdge(edge, edgeType);
	}

	public class RelationshipEdge {
		String _superClass;
		String _subClass;

		public RelationshipEdge( String _subClass, String _superClass) {
			this._superClass = _superClass;
			this._subClass = _subClass;
		}

		public String getSuperClass() {
			return _superClass;
		}

		public String getSubClass() {
			return _subClass;
		}

		private RelationshipManager getOuterType() {
			return RelationshipManager.this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((_subClass == null) ? 0 : _subClass.hashCode());
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
			if (_subClass == null) {
				if (other._subClass != null)
					return false;
			} else if (!_subClass.equals(other._subClass))
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
