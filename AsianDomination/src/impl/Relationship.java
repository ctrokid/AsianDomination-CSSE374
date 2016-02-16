package impl;

import java.util.HashMap;
import java.util.Map;

import api.IRelationship;
import utils.DotClassUtils.RelationshipType;

public class Relationship implements IRelationship {
	private RelationshipType _relationshipType;
	private String _superClass;
	private Map<String, String> description;

	public Relationship(String superClass, RelationshipType type) {
		this._relationshipType = type;
		this._superClass = superClass;
		this.description = new HashMap<>();
	}

	@Override
	public String getDependentClass() {
		return _superClass;
	}

	@Override
	public RelationshipType getRelationshipType() {
		return this._relationshipType;
	}

	@Override
	public void addDescription(String type, String description) {
		this.description.put(type, description);
	}

	@Override
	public String getDecoration() {
		StringBuilder str = new StringBuilder();
		for (String key : description.keySet()) {
			str.append(key + " = " + description.get(key) + " ,");
		}
		return str.toString();

	}

}
