package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import api.IProjectModel;
import framework.AbstractPhase;
import utils.LaunchDiagramGenerator;
import visitor.IVisitor;
import visitor.Visitor;

public abstract class AbstractDiagramOutputStream extends AbstractPhase {
	protected IProjectModel _projectModel;
	protected OutputStream _outputStream;
	protected String _asmOutputPath;
	protected IVisitor _visitor;
	protected LaunchDiagramGenerator _diagramGenerator;

	public AbstractDiagramOutputStream(Properties props, IVisitor visitor) {
		super(props);
		_projectModel = null;
		_visitor = visitor;
	}
	
	public AbstractDiagramOutputStream(Properties props) {
		super(props);
		_projectModel = null;
		_visitor = new Visitor();
	}

	protected abstract void writeOutput();

	protected abstract void generateDiagram();
	
	@Override
	protected void loadConfig(Properties props) {
		_asmOutputPath = props.getProperty("output-dir");
		
		// instantiate the diagram generator with dotPath
		String DEFAULT_DOT_PATH = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
		String dotPath = props.getProperty("dot-path");
		
		if (dotPath == null)
			dotPath = DEFAULT_DOT_PATH;
		
		_diagramGenerator = new LaunchDiagramGenerator(dotPath);
	}
	
	@Override
	public void execute(IProjectModel model) {
		// instantiate outputStream each time we write execute this phase
		// this is so subset diagrams get newly generated
		try {
			_outputStream = new FileOutputStream(_asmOutputPath, false);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot write diagram. Output path: " + _asmOutputPath + " not found.");
			return;
		}
		
		setProjectModel(model);
		writeOutput();
		generateDiagram();
	}
	
	protected void setProjectModel(IProjectModel model) {
		_projectModel = model;
	}

	protected void write(String s) {
		try {
			_outputStream.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
