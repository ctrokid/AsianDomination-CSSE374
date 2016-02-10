package api;

import java.util.Collection;

public interface IProjectModel{
	public IRelationshipManager getRelationshipManager();
	
	public Collection<ITargetClass> getTargetClasses();
	public ITargetClass getTargetClassByName(String className);
	public ITargetClass forcefullyGetClassByName(String className);
	
	public void addClass(String classPath);
	public void decorateClass(ITargetClass clazz);
}
