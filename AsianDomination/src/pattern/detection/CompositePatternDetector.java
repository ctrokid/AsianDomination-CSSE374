package pattern.detection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.objectweb.asm.Type;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import utils.AsmClassUtils;

public class CompositePatternDetector extends AbstractPatternDetectionStrategy {
	private boolean requireAddRemoveOneParameter = false;
	private IProjectModel model = null;

	public CompositePatternDetector(Properties props) {
		super(props);
		loadConfig(props);
	}

	@Override
	protected void loadConfig(Properties props) {
		String bool = props.getProperty("composite-require-addAndRemoveMethodsOneParameter");
		if (bool != null && bool.toLowerCase().equals("true")) {
			this.requireAddRemoveOneParameter = true;
		}
	}

	@Override
	protected void detectPatterns(IProjectModel _model) {
		model = _model;
		IRelationshipManager manager = model.getRelationshipManager();

		for (ITargetClass clazz : model.getTargetClasses()) {
			detectComposite(clazz, manager);
		}
	}

	private void detectComposite(ITargetClass clazz, IRelationshipManager manager) {
		Set<String> superTypes = manager.getClassSuperTypes(clazz.getClassName(), model);
		Set<String> matchingTypes = getComposedSuperTypes(superTypes, clazz.getFields());

		// check if inherited and composed types have add and remove methods
		boolean matchingTypesHaveAddAndRemove = false;
		List<ITargetClass> potentialComponents = new ArrayList<ITargetClass>();

		for (String type : matchingTypes) {
			ITargetClass matchingClass = model.forcefullyGetClassByName(type);
			potentialComponents.add(matchingClass);

			if (classHasAddAndRemoveMethods(matchingClass, superTypes)) {
				matchingTypesHaveAddAndRemove = true;
			}
		}

		if (matchingTypesHaveAddAndRemove || classHasAddAndRemoveMethods(clazz, superTypes)) {
			// get super class leaves
			List<ITargetClass> leaves = checkSuperClassesForLeaves(clazz.getClassName(), matchingTypes);
			List<String> mySubClasses = manager.getClassSubClasses(clazz.getClassName());
			mySubClasses.add(clazz.getClassName());

			for (ITargetClass leaf : leaves) {
				if (mySubClasses.contains(leaf.getClassName()))
					continue;

				tagLeafClass(leaf);
			}

			tagCompositeClass(clazz);

			for (String supertype : superTypes) {
				detectCompositeHelper(supertype, manager);
			}
			for (ITargetClass component : potentialComponents) {
				tagComponentClass(component);
			}

		}
	}

	private void detectCompositeHelper(String clazz, IRelationshipManager manager) {
		ITargetClass c = model.forcefullyGetClassByName(clazz);
		detectComposite(c, manager);
	}

	private void tagLeafClass(ITargetClass leaf) {
		pc.decorate(PATTERN_TYPE.COMPOSITE_LEAF, leaf, model);
	}

	private void tagCompositeClass(ITargetClass composite) {
		pc.decorate(PATTERN_TYPE.COMPOSITE_COMPOSITE, composite, model);

		List<String> subClasses = model.getRelationshipManager().getClassSubClasses(composite.getClassName());
		for (String clazz : subClasses) {
			ITargetClass subClass = model.forcefullyGetClassByName(clazz);
			pc.decorate(PATTERN_TYPE.COMPOSITE_COMPOSITE, subClass, model);
		}
	}

	private void tagComponentClass(ITargetClass component) {
		pc.decorate(PATTERN_TYPE.COMPOSITE_COMPONENT, component, model);
	}

	private Set<String> getComposedSuperTypes(Set<String> superTypes, Collection<IClassField> fields) {
		Set<String> matchingTypes = new HashSet<String>();

		if (superTypes.size() < 1
				|| (superTypes.size() == 1 && superTypes.iterator().next().equals("java/lang/Object")))
			return matchingTypes;

		for (IClassField field : fields) {
			if (superTypes.contains(field.getType())) {
				matchingTypes.add(field.getType());
			} else if (field.getSignature() != null) {
				String sig = field.getSignature().substring(2, field.getSignature().length() - 2);
				String[] params = sig.split(",");

				for (String param : params) {
					if (superTypes.contains(param)) {
						matchingTypes.add(param);
					}
				}
			}
		}

		// Get the fields for each the super types
		for (String superType : superTypes) {
			ITargetClass clazz = model.forcefullyGetClassByName(superType);
			Set<String> supers = model.getRelationshipManager().getClassSuperTypes(clazz.getClassName(), model);
			matchingTypes.addAll(getComposedSuperTypes(supers, clazz.getFields()));
		}
		// for the fields, check inside the collections
		return matchingTypes;
	}

	private boolean classHasAddAndRemoveMethods(ITargetClass clazz, Set<String> superTypes) {
		boolean addMethod = false;
		boolean removeMethod = false;
		for (IClassMethod method : clazz.getMethods()) {
			if (method.getMethodName().startsWith("add") && oneParamEnforced(method)) {
				if (addOrRemoveContainHierarchyParameter(method, superTypes))
					addMethod = true;
			} else if (method.getMethodName().startsWith("remove") && oneParamEnforced(method)) {
				if (addOrRemoveContainHierarchyParameter(method, superTypes))
					removeMethod = true;
			}
		}

		return addMethod && removeMethod;
	}

	private boolean oneParamEnforced(IClassMethod method) {
		// System.out.println(requireAddRemoveOneParameter);
		if (!requireAddRemoveOneParameter) {
			return true;
		}
		// System.out.println(Type.getArgumentTypes(method.getSignature()).length);
		return Type.getArgumentTypes(method.getSignature()).length == 1;
	}

	private boolean addOrRemoveContainHierarchyParameter(IClassMethod method, Set<String> superTypes) {
		boolean isValid = false;
		String[] params = AsmClassUtils.GetArguments(method.getSignature(), false).split(",");

		if (superTypes.contains(params[0])) {
			if (!requireAddRemoveOneParameter) {
				isValid = true;
			} else if (params.length == 1)
				isValid = true;
		}

		return isValid;
	}

	private List<ITargetClass> checkSuperClassesForLeaves(String className, Set<String> matchingTypes) {
		List<ITargetClass> classes = new ArrayList<ITargetClass>();
		IRelationshipManager manager = model.getRelationshipManager();

		for (String type : matchingTypes) {
			model.forcefullyGetClassByName(type);
			List<String> subClasses = manager.getClassSubClasses(type);
			for (String subClassName : subClasses) {
				if (subClassName.equals(className))
					continue;

				ITargetClass subClass = model.forcefullyGetClassByName(subClassName);

				if (manager.getClassSubClasses(subClassName).size() == 0) {
					classes.add(subClass);
				}
			}
		}

		return classes;
	}

	@Override
	public String toString() {
		return "Detecting Composite Patterns...";
	}

}
