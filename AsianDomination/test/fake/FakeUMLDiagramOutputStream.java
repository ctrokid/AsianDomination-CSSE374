package fake;

import output.UMLDiagramOutputStream;
import visitor.IVisitor;

public class FakeUMLDiagramOutputStream extends UMLDiagramOutputStream {

	public FakeUMLDiagramOutputStream(String asmOutputPath, String executablePath, IVisitor visitor) {
		super(asmOutputPath, executablePath, visitor);
	}
	
	@Override
	public void generateDiagram() {
		
	}

}
