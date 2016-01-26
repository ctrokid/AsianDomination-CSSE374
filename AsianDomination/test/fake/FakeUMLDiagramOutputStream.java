package fake;

import output.UMLDiagramOutputStream;
import visitor.IVisitor;

public class FakeUMLDiagramOutputStream extends UMLDiagramOutputStream {

	public FakeUMLDiagramOutputStream(String asmOutputPath, IVisitor visitor) {
		super(asmOutputPath, visitor);
	}
	
	@Override
	public void generateDiagram(String path) {
		
	}

}
