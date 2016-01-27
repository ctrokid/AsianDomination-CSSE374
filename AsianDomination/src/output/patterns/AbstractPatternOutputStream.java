package output.patterns;

import output.UMLDiagramOutputStream;
import visitor.IVisitor;

public abstract class  AbstractPatternOutputStream extends UMLDiagramOutputStream {

	public AbstractPatternOutputStream(String asmOutputPath, IVisitor visitor) {
		super(asmOutputPath, visitor);
	}

	@Override
	protected abstract void setupPreVisitTargetClass();
}
