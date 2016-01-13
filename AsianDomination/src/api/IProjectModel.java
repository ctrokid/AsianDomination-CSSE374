package api;

import java.io.IOException;

import visitor.IDiagramOutputStream;
import visitor.ITraverser;

public interface IProjectModel extends ITraverser {
	public void parseModel() throws IOException;
	public void setOutputStream(IDiagramOutputStream v);
}
