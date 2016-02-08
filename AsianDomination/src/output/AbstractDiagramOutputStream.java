package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import api.IProjectModel;
import utils.LaunchDiagramGenerator;
import visitor.IVisitor;

public abstract class AbstractDiagramOutputStream implements IDiagramOutputStream {
	protected IProjectModel _projectModel;
	protected OutputStream _outputStream;
	protected String _asmOutputPath;
	protected IVisitor _visitor;
	protected LaunchDiagramGenerator _diagramGenerator;

	public AbstractDiagramOutputStream(String asmOutputPath, String diagramExecutablePath, IVisitor visitor) {
		_asmOutputPath = asmOutputPath;
		_projectModel = null;
		_visitor = visitor;
		_diagramGenerator = new LaunchDiagramGenerator(diagramExecutablePath);
		
		try {
			_outputStream = new FileOutputStream(asmOutputPath);
		} catch (FileNotFoundException e) {
			// TODO: don't swallow for now
		}
	}

	public abstract void writeOutput();

	public abstract void generateDiagram();

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
