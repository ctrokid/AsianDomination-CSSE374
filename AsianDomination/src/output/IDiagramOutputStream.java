package output;

import api.IProjectModel;
import visitor.IVisitor;

public interface IDiagramOutputStream {
	public void writeOutput();
	public void generateDiagram(String diagramOutputPath);
	public void setProjectModel(IProjectModel model);
}
