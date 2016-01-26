package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import api.IProjectModel;
import impl.Visitor;
import visitor.ITraverser;
import visitor.IVisitMethod;
import visitor.IVisitor;
import visitor.LookupKey;
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

	public void preVisit(ITraverser i) {
		_visitor.preVisit(i);
	}

	public void visit(ITraverser i) {
		_visitor.visit(i);
	}

	public void postVisit(ITraverser i) {
		_visitor.postVisit(i);
	}
	
	public void addVisit(VisitType visitType, Class<?> clazz, IVisitMethod m) {
		_visitor.addVisit(visitType, clazz, m);
	}

	public void removeVisit(VisitType visitType, Class<?> clazz) {
		_visitor.removeVisit(visitType, clazz);
	}
}
