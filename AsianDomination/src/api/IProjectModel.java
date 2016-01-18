package api;

import java.util.Collection;

import input.InputCommand;

public interface IProjectModel{
	public void parseModel();
	public Collection<ITargetClass> getTargetClasses();
	public ITargetClass getTargetClassByName(String className);
	public InputCommand getInputCommand();
	public IRelationshipManager getRelationshipManager();
	public void addClass(String classPath);
}
