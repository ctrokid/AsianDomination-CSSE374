package output;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import api.IClassMethod;
import api.IMethodStatement;
import api.ITargetClass;
import impl.MethodStatement;
import impl.TargetClass;
import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.ITraverser;
import visitor.VisitType;

public class SDDiagramOutputStream extends AbstractDiagramOutputStream {
	private Map<String, String> _classNames;
	private List<String> _methodStatements;
	private String _initialClass;
	private String _initialMethod;
	private String _initialMethodParameters;
	private int _maxCallDepth;
	
	
	public SDDiagramOutputStream(String asmOutputPath, String initialClass, String initialMethod, String initialParameters, int maxCallDepth) {
		super(asmOutputPath);
		_classNames = new LinkedHashMap<String, String>();
		_methodStatements = new ArrayList<String>();
		_initialClass = initialClass;
		_initialMethod = initialMethod;
		_initialMethodParameters = initialParameters;
		_maxCallDepth = maxCallDepth;
		
		this.setupVisitMethodStatement();
		this.setupVisitTargetClass();
	}
	
	public void setupVisitMethodStatement() {
		super.addVisit(VisitType.Visit, IMethodStatement.class, (ITraverser t) -> {
			MethodStatement stmt = (MethodStatement) t;
			StringBuilder sb = new StringBuilder();
			String methodNameAndParams = stmt.getMethodName() + "(" + stmt.getParameters() + ")";
			
			String[] params = stmt.getParameters().split(",");
			
			
			if (stmt.getMethodName().equals("<init>")) {
				methodNameAndParams = "create("+ stmt.getParameters()+")";
				
				if (!_classNames.containsKey(stmt.getClassToCall())) {
					_classNames.put(stmt.getClassToCall(), stmt.getClassToCall() + ":" + stmt.getClassToCall() + "[a]\n");
				}
			}
			for(String p: params){
				if (!_classNames.containsKey(p.trim())) 
					_classNames.put(stmt.getClassToCall(), stmt.getClassToCall() + ":" + stmt.getClassToCall() + "[a]\n");
			}
			
			sb.append(stmt.getCallerClass() + ":" + stmt.getClassToCall() + "." + methodNameAndParams + "\n");
			_methodStatements.add(sb.toString());
		});
	}
	
	public void setupVisitTargetClass() {
		super.addVisit(VisitType.Visit, ITargetClass.class, (ITraverser t) -> {
			ITargetClass clazz = (TargetClass) t;
			StringBuilder sb = new StringBuilder();
			
			sb.append(clazz.getClassName() + ":" + clazz.getClassName() + "[a]\n");
			_classNames.put(clazz.getClassName(), sb.toString());
		});
	}

	@Override
	public void writeOutput() {
		_classNames.put(_initialClass, _initialClass + ":" + _initialClass + "[a]\n");
		visitModelRecursively(_initialClass, _initialMethod, _initialMethodParameters, 1);
	
		for (String key : _classNames.keySet()) {
			write(_classNames.get(key));
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
			ms.accept(this);
			visitModelRecursively(ms.getClassToCall(), ms.getMethodName(), ms.getParameters(), level + 1);
		}
	}
	
	@Override
	public void generateDiagram(String diagramOutputPath) {
		LaunchDiagramGenerator.RunSDEdit(_asmOutputPath, diagramOutputPath, DiagramFileExtension.PDF);
	}

}
