package output;

import java.util.ArrayList;
import java.util.List;

import api.IClassMethod;
import api.IMethodStatement;
import api.ITargetClass;
import impl.MethodStatement;
import impl.TargetClass;
import utils.AsmClassUtils;
import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.ITraverser;
import visitor.VisitType;

public class SDDiagramOutputStream extends AbstractDiagramOutputStream {
	private List<String> _classNames;
	private List<String> _methodStatements;
	private String _initialClass;
	private String _initialMethod;
	private String _initialMethodParameters;
	private int _maxCallDepth;
	
	public SDDiagramOutputStream(String asmOutputPath, String initialClass, String initialMethod, String initialParameters, int maxCallDepth) {
		super(asmOutputPath);
		_classNames = new ArrayList<String>();
		_methodStatements = new ArrayList<String>();
		_initialClass = initialClass;
		_initialMethod = initialMethod;
		_initialMethodParameters = initialParameters;
		_maxCallDepth = maxCallDepth;
	}
	
	public void setupVisitMethodStatement() {
		super.addVisit(VisitType.Visit, MethodStatement.class, (ITraverser t) -> {
			MethodStatement stmt = (MethodStatement) t;
			StringBuilder sb = new StringBuilder();
			
			String methodNameAndParams = stmt.getMethodName() + stmt.getParameter();
			
			if (stmt.getMethodName().equals("<init>"))
				methodNameAndParams = "new";
			
			sb.append(stmt.getCallerClass() + ":" + AsmClassUtils.GetStringStrippedByCharacter(stmt.getClassToCall(), '/') + "." + methodNameAndParams + "\n");//stmt.getReturn() + "\n");
			//write(sb.toString());
			_methodStatements.add(sb.toString());
		});
	}
	
	public void setupVisitTargetClass() {
		super.addVisit(VisitType.Visit, TargetClass.class, (ITraverser t) -> {
			ITargetClass clazz = (TargetClass) t;
			StringBuilder sb = new StringBuilder();
			
			// TODO: remember to not slash the first class
//			if (!clazz.isFirstClass())
//				sb.append("/");
			sb.append(clazz.getClassName() + ":" + clazz.getClassName() + "[a]\n");
//			write(sb.toString());
			_classNames.add(sb.toString());
		});
	}

	@Override
	public void writeOutput() {
		_classNames.add(_initialClass);
		visitModelRecursively(_initialClass, _initialMethod, _initialMethodParameters, 1);
	}

	private void visitModelRecursively(String className, String methodName, String params, int level) {
		// 3. get the method*****(from instance variables)
		if (level > _maxCallDepth)
			return;
		
		ITargetClass clazz = _projectModel.getTargetClassByName(className);
		IClassMethod methods = clazz.getMethodByName(methodName, params);
		for(IMethodStatement ms: methods.getMethodStatements()){
			ms.accept(this);
			
			String classToCall = ms.getClassToCall();
			if(!_classNames.contains(className))
				classToCall = "/" + ms.getClassToCall();
				
			visitModelRecursively(classToCall, ms.getMethodName(), ms.getParameter(), level + 1);
		}
		// 4. loop over each statement
		// 5. add statement to "data structure"
		// 5. recurse on stmt.classToCall()
		// 6. check if calssToCall has the current class, if the class is in the list, no / otherwise add /
			
	}
	
	@Override
	public void generateDiagram(String diagramOutputPath) {
		LaunchDiagramGenerator.RunSDEdit(_asmOutputPath, diagramOutputPath, DiagramFileExtension.PDF);
	}

}
