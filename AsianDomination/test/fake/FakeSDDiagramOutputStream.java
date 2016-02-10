package fake;

import java.util.Properties;

import output.SDDiagramOutputStream;
import visitor.IVisitor;

public class FakeSDDiagramOutputStream extends SDDiagramOutputStream {

	public FakeSDDiagramOutputStream(Properties props, IVisitor visitor) {
		super(props, visitor);
	}
	
	@Override
	public void generateDiagram() {
		
	}

}
