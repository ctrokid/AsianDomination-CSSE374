package pattern.detection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import pattern.decoration.CompositeDecorator;

public class CompositePatternDetector implements IPatternDetectionStrategy {

	@Override
	public void detectPatterns(IProjectModel model) {
		IRelationshipManager manager = model.getRelationshipManager();

		for (ITargetClass clazz : model.getTargetClasses()) {
			Set<String> superTypes = manager.getClassSuperTypes(clazz.getClassName(), model);
			Set<String> matchingTypes = getComposedSuperTypes(superTypes, clazz.getFields());

			// check if inherited and composed types have add and remove methods
			boolean matchingTypesHaveAddAndRemove = false;
			List<ITargetClass> potentialComponents = new ArrayList<ITargetClass>();

			for (String type : matchingTypes) {
				ITargetClass matchingClass = model.forcefullyGetClassByName(type);
				potentialComponents.add(matchingClass);

				if (classHasAddAndRemoveMethods(matchingClass)) {
					matchingTypesHaveAddAndRemove = true;
				}
			}

			if (matchingTypesHaveAddAndRemove || classHasAddAndRemoveMethods(clazz)) {
				// get super class leaves
				List<ITargetClass> leaves = checkSuperClassesForLeaves(clazz.getClassName(), matchingTypes, model);
				List<String> mySubClasses = manager.getClassSubClasses(clazz.getClassName());
				mySubClasses.add(clazz.getClassName());
				
				for (ITargetClass leaf : leaves) {
					if (mySubClasses.contains(leaf.getClassName()))
						continue;
					
					tagLeafClass(leaf, model);
				}

				if (leaves.size() > 0) {
					tagCompositeClass(clazz, model);
					for (ITargetClass component : potentialComponents) {
						tagComponentClass(component, model);
					}
				}
			}
		}
	}

	private void tagLeafClass(ITargetClass leaf, IProjectModel model) {
		leaf = new CompositeDecorator(PATTERN_TYPE.COMPOSITE_LEAF, "", leaf);
		model.decorateClass(leaf);
	}

	private void tagCompositeClass(ITargetClass composite, IProjectModel model) {
		composite = new CompositeDecorator(PATTERN_TYPE.COMPOSITE_COMPOSITE, "", composite);
		model.decorateClass(composite);
	}

	private void tagComponentClass(ITargetClass component, IProjectModel model) {
		component = new CompositeDecorator(PATTERN_TYPE.COMPOSITE_COMPONENT, "", component);
		model.decorateClass(component);
	}

	private Set<String> getComposedSuperTypes(Set<String> superTypes, Collection<IClassField> fields) {
		Set<String> matchingTypes = new HashSet<String>();

		for (IClassField field : fields) {
			if (superTypes.contains(field.getType())) {
				matchingTypes.add(field.getType());
			} else if (field.getSignature() != null/* "" */) {
				String sig = field.getSignature().substring(2, field.getSignature().length() - 2);
				String[] params = sig.split(",");

				for (String param : params) {
					if (superTypes.contains(param)) {
						matchingTypes.add(param);
					}
				}
			}
		}
		// for the fields, check inside the collections
		return matchingTypes;
	}

	private boolean classHasAddAndRemoveMethods(ITargetClass clazz) {
		boolean addMethod = false;
		boolean removeMethod = false;

		for (IClassMethod method : clazz.getMethods()) {
			if (method.getMethodName().contains("add")) {
				addMethod = true;
			} else if (method.getMethodName().contains("remove")) {
				removeMethod = true;
			}
		}

		return addMethod && removeMethod;
	}

	private List<ITargetClass> checkSuperClassesForLeaves(String className, Set<String> matchingTypes,
			IProjectModel model) {
		List<ITargetClass> classes = new ArrayList<ITargetClass>();
		IRelationshipManager manager = model.getRelationshipManager();

		for (String type : matchingTypes) {
			model.forcefullyGetClassByName(type);
			List<String> subClasses = manager.getClassSubClasses(type);
			for (String subClassName : subClasses) {
				if (subClassName.equals(className))
					continue;

				ITargetClass subClass = model.getTargetClassByName(subClassName);

				if (manager.getClassSubClasses(subClassName).size() == 0) {
					classes.add(subClass);
				}
			}
		}

		return classes;
	}

}
