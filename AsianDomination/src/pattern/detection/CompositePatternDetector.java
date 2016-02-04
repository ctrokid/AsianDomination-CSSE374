package pattern.detection;

import java.util.HashSet;
import java.util.Set;

import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;

public class CompositePatternDetector implements IPatternDetectionStrategy {

	@Override
	public void detectPatterns(IProjectModel model) {
//		IRelationshipManager manager = model.getRelationshipManager();
		
		for (ITargetClass clazz : model.getTargetClasses()) {
//			Set<String> superTypes = manager.getClassSuperTypes(clazz.getClassName(), model);
//			Set<String> matchingTypes = getComposedSuperTypes(superTypes, clazz);
			
//			if (classHasAddAndRemoveMethods(clazz)) {
				
//			} else {
			
//				for (String type : matchingTypes) {
//					ITargetClass superClazz = model.getTargetClassByName(type);
//					if (classHasAddAndRemoveMethods(superClazz)) {
						
//					}
//				}	
//			}
		
		}
	}
	
	private Set<String> getComposedSuperTypes(Set<String> superTypes, ITargetClass clazz) {
		Set<String> matchingTypes = new HashSet<String>();
		
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
