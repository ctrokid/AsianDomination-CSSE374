package api;

import java.io.IOException;
import java.util.Collection;

import impl.InputCommand;

import visitor.IDiagramOutputStream;
import visitor.ITraverser;

public interface IProjectModel extends ITraverser {
	public void parseModel() throws IOException;

	public void setOutputStream(IDiagramOutputStream v);

	public ITargetClass getTargetClassByName(String className);

	public Collection<IClassMethod> getTargetClassMethods(ITargetClass targetClass);

	public InputCommand getInputCommand();

	public IRelationshipManager getRelationshioManager();
}
