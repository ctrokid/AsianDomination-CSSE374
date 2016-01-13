package api;

import java.io.IOException;

import visitor.ITraverser;
import visitor.IVisitor;

public interface IProjectModel extends ITraverser {
	public void parseModel() throws IOException;
	public void setOutputStream(IVisitor v);
}
