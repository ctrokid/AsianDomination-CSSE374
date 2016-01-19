package fake;

import output.UMLDiagramOutputStream;

public class FakeUMLDiagramOutputStream extends UMLDiagramOutputStream {

	public FakeUMLDiagramOutputStream(String asmOutputPath) {
		super(asmOutputPath);
	}
	
	@Override
	public void generateDiagram(String path) {
		
	}

}
