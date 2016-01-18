package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import api.IProjectModel;
import visitor.ITraverser;
import visitor.IVisitMethod;
import visitor.LookupKey;
import visitor.VisitType;

public abstract class AbstractDiagramOutputStream implements IDiagramOutputStream {
	protected IProjectModel _projectModel;
	protected OutputStream _outputStream;
	protected String _asmOutputPath;
	protected Map<LookupKey, IVisitMethod> _keyToVisitMethodMap;

	public AbstractDiagramOutputStream(String asmOutputPath) {
		_asmOutputPath = asmOutputPath;
		_keyToVisitMethodMap = new HashMap<LookupKey, IVisitMethod>();
		_projectModel = null;
		
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
		this.doVisit(VisitType.PreVisit, i);
	}

	public void visit(ITraverser i) {
		this.doVisit(VisitType.Visit, i);
	}

	public void postVisit(ITraverser i) {
		this.doVisit(VisitType.PostVisit, i);
	}

	protected void doVisit(VisitType vType, ITraverser t) {
		LookupKey key = new LookupKey(vType, t.getClass());
		IVisitMethod m = _keyToVisitMethodMap.get(key);
		
		if (m != null)
			m.execute(t);
	}
	
	public void addVisit(VisitType visitType, Class<?> clazz, IVisitMethod m) {
		LookupKey key = new LookupKey(visitType, clazz);
		_keyToVisitMethodMap.put(key, m);
	}

	public void removeVisit(VisitType visitType, Class<?> clazz) {
		LookupKey key = new LookupKey(visitType, clazz);
		_keyToVisitMethodMap.remove(key);
	}
}
