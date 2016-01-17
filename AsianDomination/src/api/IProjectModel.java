package api;

import java.io.IOException;
import java.util.Collection;

import input.InputCommand;

public interface IProjectModel{
	public void parseModel() throws IOException;
	public Collection<ITargetClass> getTargetClasses();
	public ITargetClass getTargetClassByName(String className);
	public InputCommand getInputCommand();
	public IRelationshipManager getRelationshipManager();
	public void addClass(String classPath);
}
