package pattern.detection;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.IClassField;
import api.IClassMethod;
import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;

public class CompositePatternDetector implements IPatternDetectionStrategy {

	@Override
	public void detectPatterns(IProjectModel model) {
		IRelationshipManager manager = model.getRelationshipManager();
		
		for (ITargetClass clazz : model.getTargetClasses()) {
			Set<String> superTypes = manager.getClassSuperTypes(clazz.getClassName(), model);
			Set<String> matchingTypes = getComposedSuperTypes(superTypes, clazz.getFields());
			
			if (classHasAddAndRemoveMethods(clazz)) {
				// check super clazz for leaves
				List<ITargetClass> leaves = checkSuperClassesForLeaves(clazz.getClassName(), matchingTypes);
			} else {
			
//				for (String type : matchingTypes) {
//					ITargetClass superClazz = model.getTargetClassByName(type);
//					if (classHasAddAndRemoveMethods(superClazz)) {
						
//					}
//				}	
//			}
		
		}
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

}
