package output;

import framework.IPhase;

public interface IDiagramOutputStream extends IPhase {
	public void writeOutput();
	public void generateDiagram();
}
