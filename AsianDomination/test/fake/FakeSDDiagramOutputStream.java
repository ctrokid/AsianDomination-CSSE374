package fake;

import impl.Visitor;
import output.SDDiagramOutputStream;

public class FakeSDDiagramOutputStream extends SDDiagramOutputStream {

	public FakeSDDiagramOutputStream(String asmOutputPath, String initialClass, String initialMethod,
			String initialParameters, int maxCallDepth) {
		super(asmOutputPath, initialClass, initialMethod, initialParameters, maxCallDepth, new Visitor());
	}
	
	@Override
	public void generateDiagram(String diagramOutputPath) {
		
	}

}
