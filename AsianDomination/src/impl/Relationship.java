package impl;

import api.IRelationship;
import utils.DotClassUtils.RelationshipType;

public class Relationship implements IRelationship{
	private String _superClass;
	private RelationshipType _relationshipType;
	private String _decoratedType;

	public Relationship(String superClass, RelationshipType type) {
		this._superClass = superClass;
		this._relationshipType = type;
		_decoratedType = "";
	}

	public String getSuperClass() {
		return this._superClass;
	}

	public RelationshipType getRelationshipType() {
		return this._relationshipType;
	}

	public String getDecoratedType() {
		return this._decoratedType;
	}

	public void setDecoratedType(String type) {
		this._decoratedType = type;
	}

}
