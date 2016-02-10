package output;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import api.IClassMethod;
import api.IMethodStatement;
import api.ITargetClass;
import impl.MethodStatement;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.ITraverser;
import visitor.IVisitor;
import visitor.VisitType;

public class SDDiagramOutputStream extends AbstractDiagramOutputStream {
	private Map<String, String> _classNameToOutput;
	private List<String> _methodStatements;
	private String _initialClass;
	private String _initialMethod;
	private String _initialMethodParameters;
	private int _maxCallDepth = 3;
	
	public SDDiagramOutputStream(Properties props, IVisitor visitor) {
		super(props, visitor);
		_classNameToOutput = new LinkedHashMap<String, String>();
		_methodStatements = new ArrayList<String>();
		
		this.setupVisitMethodStatement();
	}
	
	@Override
	protected void loadConfig(Properties props) {
		// load diagramGenerator and output stream
		super.loadConfig(props);
		
		// load SD specific parameters
		String initialClass = props.getProperty("initial-class");
		if (initialClass != null) {
			_initialClass = initialClass;
		}
		
		String initialMethod = props.getProperty("initial-method");
		if (initialMethod != null) {
			_initialMethod = initialMethod;
		}
		
		String initialParams = props.getProperty("initial-method-parameters");
		if (initialParams != null) {
			_initialMethodParameters = initialParams;
		}
		
		String maxCallDepth = props.getProperty("max-call-depth");
		if (maxCallDepth != null) {
			try {
				_maxCallDepth = Integer.parseInt(maxCallDepth);
			} catch (NumberFormatException e) {}
		}
	}
	
	protected void setupVisitMethodStatement() {
		_visitor.addVisit(VisitType.Visit, IMethodStatement.class, (ITraverser t) -> {
			MethodStatement stmt = (MethodStatement) t;
			StringBuilder sb = new StringBuilder();
			String methodNameAndParams = stmt.getMethodName() + "(" + stmt.getParameters() + ")";
			
			String[] params = stmt.getParameters().split(",");
			
			
			if (stmt.getMethodName().equals("<init>")) {
				methodNameAndParams = "create("+ stmt.getParameters()+")";
				
				if (!_classNameToOutput.containsKey(stmt.getClassToCall())) {
					_classNameToOutput.put(stmt.getClassToCall(), stmt.getClassToCall() + ":" + stmt.getClassToCall() + "[a]\n");
				}
			}
			
			for(String p: params){
				if (!_classNameToOutput.containsKey(p.trim())) 
					_classNameToOutput.put(stmt.getClassToCall(), stmt.getClassToCall() + ":" + stmt.getClassToCall() + "[a]\n");
			}
			
			sb.append(stmt.getCallerClass() + ":" + stmt.getClassToCall() + "." + methodNameAndParams + "\n");
			_methodStatements.add(sb.toString());
		});
	}

	@Override
	public void writeOutput() {
		_classNameToOutput.put(_initialClass, _initialClass + ":" + _initialClass + "[a]\n");
		visitModelRecursively(_initialClass, _initialMethod, _initialMethodParameters, 1);
	
		for (String key : _classNameToOutput.keySet()) {
			write(_classNameToOutput.get(key));
		}
		write("\n");
		for (String stmt : _methodStatements) {
			write(stmt);
		}
	}

	private void visitModelRecursively(String className, String methodName, String params, int level) {
		if (level > _maxCallDepth)
			return;
		
		ITargetClass clazz = _projectModel.getTargetClassByName(className);
		IClassMethod methods = clazz.getMethodByName(methodName, params);
		
		for(IMethodStatement ms: methods.getMethodStatements()){
			ms.accept(_visitor);
			visitModelRecursively(ms.getClassToCall(), ms.getMethodName(), ms.getParameters(), level + 1);
		}
	}
	
	@Override
	public void generateDiagram() {
		_diagramGenerator.RunSDEdit(_asmOutputPath, DiagramFileExtension.PNG);
	}

}
