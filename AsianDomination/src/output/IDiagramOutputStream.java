package output;

import api.IProjectModel;

public interface IDiagramOutputStream {
	public void writeOutput();
	public void generateDiagram();
	public void setProjectModel(IProjectModel model);
}
