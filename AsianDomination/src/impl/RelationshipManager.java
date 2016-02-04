package impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.IClassDeclaration;
import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import utils.DotClassUtils.RelationshipType;

public class RelationshipManager implements IRelationshipManager {
	private HashMap<String, List<Relationship>> parentRelationships;
	private HashMap<String, List<Relationship>> childrenRelationships;

	public RelationshipManager() {
		parentRelationships = new HashMap<String, List<Relationship>>();
		childrenRelationships = new HashMap<String, List<Relationship>>();
	}
	
	@Override
	public void addRelationship(String subject, RelationshipType type, String object) {
		if (type.equals(RelationshipType.USES) && subject.equals(object))
			return;		
		
		Relationship relation = new Relationship(object, type);
		// add to parent list
		List<Relationship> pList = parentRelationships.get(subject);
		
		if (pList == null)
			pList = new ArrayList<Relationship>();
		pList.add(relation);
		parentRelationships.put(subject, pList);

		// add children list
		Relationship flippedRelation = new Relationship(subject, type);
		List<Relationship> cList = childrenRelationships.get(subject);
		if (cList == null)
			cList = new ArrayList<Relationship>();
		cList.add(flippedRelation);
		childrenRelationships.put(subject, cList);
	}

	@Override
	public List<Relationship> getClassRelationships(String subject) {
		List<Relationship> relationships = this.parentRelationships.get(subject);
		if (relationships == null)
			relationships = new ArrayList<Relationship>();
		
		return relationships;
	}

	@Override
	public Relationship getClassRelationship(String subject, RelationshipType relation, String object) {
		List<Relationship> ls = parentRelationships.get(subject);
		for (Relationship rela : ls) {
			if (rela.getDependentClass().equals(object) && rela.getRelationshipType().equals(relation)) {
				return rela;
			}
		}
		return null;
	}

	@Override
	public Set<String> getClassSuperTypes(String subject, IProjectModel model) {
		Set<String> set = new HashSet<String>();
		if (subject.equals("java/lang/Object")) {
			return set;
		}
		ITargetClass c = model.forcefullyGetClassByName(subject);
		Set<String> subset = getClassSuperType(c);
		for (String clazz : subset) {
			set.add(clazz);
			set.addAll(getClassSuperTypes(clazz, model));
		}
		return set;
	}

	private Set<String> getClassSuperType(ITargetClass c) {
		Set<String> set = new HashSet<String>();
		IClassDeclaration d = c.getDeclaration();
		set.addAll(d.getInterfaces());
		set.add(d.getSuperClassType());
		return set;
	}

	@Override
	public List<String> getClassSubClasses(String subject) {
		List<Relationship> subClassesRela = this.childrenRelationships.get(subject);
		List<String> subClassesNames = new ArrayList<String>();
		for (Relationship r : subClassesRela) {
			subClassesNames.add(r.getDependentClass());
		}
		return subClassesNames;
	}
}
