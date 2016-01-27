package impl;

public class Relationship {
	private String _superClass;
	private String _relationshipType;
	private String _decoratedType;

	public Relationship(String superClass, String type) {
		this._superClass = superClass;
		this._relationshipType = type;
	}

	public String getSuperClass() {
		return this._superClass;
	}

	public String getRelationshipType() {
		return this._relationshipType;
	}
	
	public String getDecoratedType(){
		return this._decoratedType;
	}
	public void setDecoratedType(String type){
		this._decoratedType = type;
	}

}
