package api;

import utils.DotClassUtils.RelationshipType;

public interface IRelationship {

	public String getDependentClass();

	public RelationshipType getRelationshipType();

	public String getDecoration();

	public void addDescription(String type, String setting);
}
