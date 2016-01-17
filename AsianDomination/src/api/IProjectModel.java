package api;

import java.io.IOException;
import java.util.Collection;

import input.InputCommand;

import visitor.IDiagramOutputStream;
import visitor.ITraverser;

public interface IProjectModel{
	public void parseModel() throws IOException;
	public Collection<ITargetClass> getTargetClasses();
	public ITargetClass getTargetClassByName(String className);
	public InputCommand getInputCommand();
	public IRelationshipManager getRelationshioManager();
	public void addClass(String classPath);
}
