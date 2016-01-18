package output;

import java.io.OutputStream;

import api.IDiagramOutputStream;
import api.IProjectModel;
import visitor.ITraverser;
import visitor.IVisitMethod;
import visitor.VisitType;

public class AbstractDiagramOutputStream implements IDiagramOutputStream {
	protected IProjectModel _projectModel;
	protected OutputStream _outputStream;

	public AbstractDiagramOutputStream(String asmOutputPath) {

	}

	@Override
	public void writeOutput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProjectModel(IProjectModel projectModel) {
		// TODO Auto-generated method stub

	}

	public void write(String output) {

	}

	public void preVisit(ITraverser i) {

	}

	public void vist(ITraverser i) {

	}

	public void postVisit(ITraverser i) {

	}

	public void addVisit(VisitType type, Class<?> clazz, IVisitMethod visitMethod) {

	}

	public void removeVisit(VisitType type, Class<?> clazz) {

	}

	public void doVisit(VisitType type, ITraverser traverser) {

	}

}
