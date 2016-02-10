package fake;

import java.util.Properties;

import output.UMLDiagramOutputStream;
import visitor.IVisitor;

public class FakeUMLDiagramOutputStream extends UMLDiagramOutputStream {

	public FakeUMLDiagramOutputStream(Properties props, IVisitor visitor) {
		super(props, visitor);
	}
	
	@Override
	public void generateDiagram() {
		
	}

}
