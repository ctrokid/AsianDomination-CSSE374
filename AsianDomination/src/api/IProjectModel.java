package api;

import java.util.Collection;

import input.InputCommand;

public interface IProjectModel{
	public void build();
	public void printModel();
	public Collection<ITargetClass> getTargetClasses();
	public ITargetClass getTargetClassByName(String className);
	public InputCommand getInputCommand();
	public void addClass(String classPath);
	public void decorateClass(ITargetClass clazz);
}
