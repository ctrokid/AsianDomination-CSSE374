package output;

import api.IProjectModel;

public interface IDiagramOutputStream {
	public void writeOutput();
	public void generateDiagram(String diagramOutputPath);
	public void setProjectModel(IProjectModel model);
}
