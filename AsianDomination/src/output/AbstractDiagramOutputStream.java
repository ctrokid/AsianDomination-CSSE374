package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import api.IProjectModel;
import visitor.ITraverser;
import visitor.IVisitMethod;
import visitor.IVisitor;
import visitor.VisitType;

public abstract class AbstractDiagramOutputStream implements IDiagramOutputStream {
	protected IProjectModel _projectModel;
	protected OutputStream _outputStream;
	protected String _asmOutputPath;
	protected IVisitor _visitor;

	public AbstractDiagramOutputStream(String asmOutputPath, IVisitor visitor) {
		_asmOutputPath = asmOutputPath;
		_projectModel = null;
		_visitor = visitor;
		try {
			_outputStream = new FileOutputStream(asmOutputPath);
		} catch (FileNotFoundException e) {
			// TODO: don't swallow for now
		}
	}

	public abstract void writeOutput();

	public abstract void generateDiagram(String diagramOutputPath);

	protected void write(String s) {
		try {
			_outputStream.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setProjectModel(IProjectModel projectModel) {
		_projectModel = projectModel;
	}
}
