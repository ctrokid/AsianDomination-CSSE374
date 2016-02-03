package api;

import utils.DotClassUtils.RelationshipType;

public interface IRelationship {

	public String getDependentClass();

	public RelationshipType getRelationshipType();

	public String getDecoratedType();

	public void setDecoratedType(String type);
}
