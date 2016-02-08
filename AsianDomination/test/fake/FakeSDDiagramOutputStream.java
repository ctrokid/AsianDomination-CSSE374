package fake;

import output.SDDiagramOutputStream;
import visitor.Visitor;

public class FakeSDDiagramOutputStream extends SDDiagramOutputStream {

	public FakeSDDiagramOutputStream(String asmOutputPath, String executablePath, String initialClass, String initialMethod,
			String initialParameters, int maxCallDepth) {
		super(asmOutputPath, executablePath, initialClass, initialMethod, initialParameters, maxCallDepth, new Visitor());
	}
	
	@Override
	public void generateDiagram() {
		
	}

}
