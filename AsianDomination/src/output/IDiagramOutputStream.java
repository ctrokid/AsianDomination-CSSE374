package output;

import api.IProjectModel;
import visitor.IVisitor;

public interface IDiagramOutputStream extends IVisitor {
	public void writeOutput();
	public void generateDiagram(String diagramOutputPath);
	public void setProjectModel(IProjectModel model);
}
