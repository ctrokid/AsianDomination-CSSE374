package fake;

import output.SDDiagramOutputStream;

public class FakeSDDiagramOutputStream extends SDDiagramOutputStream {

	public FakeSDDiagramOutputStream(String asmOutputPath, String initialClass, String initialMethod,
			String initialParameters, int maxCallDepth) {
		super(asmOutputPath, initialClass, initialMethod, initialParameters, maxCallDepth);
	}
	
	@Override
	public void generateDiagram(String diagramOutputPath) {
		
	}

}
