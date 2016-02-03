package api;

import java.util.Collection;

import input.InputCommand;

public interface IProjectModel{
	public void buildModel();
	public void printModel();
	
	public IRelationshipManager getRelationshipManager();
	
	public Collection<ITargetClass> getTargetClasses();
	public ITargetClass getTargetClassByName(String className);
	public ITargetClass forcefullyGetClassByName(String className);
	
	public void addClass(String classPath);
	public void decorateClass(ITargetClass clazz);
	
	public InputCommand getInputCommand();
}
