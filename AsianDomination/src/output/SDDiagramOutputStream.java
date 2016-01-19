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
	
	public SDDiagramOutputStream(String asmOutputPath, inputParams) {
		super(asmOutputPath);
		_classNames = new ArrayList<String>();
		_methodStatements = new ArrayList<String>();
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
		// 1. for initial class, add to "data structure"
		// 							EXISTING OR NOT EXISTING CLASS???? / or no /
		// 2. call visitModelRecursively
	}

	private void visitModelRecursively(String className, String methodName, String params) {
		// 3. get the method*****(from instance variables)
			ITargetClass clazz = _projectModel.getTargetClassByName(className);
			IClassMethod methods = clazz.getMethodByName(methodName, params);
			for(IMethodStatement ms: methods.getMethodStatements()){
				ms.accept(this);
				if(_classNames.contains(className)){
					visitModelRecursively(ms.getClassToCall(), ms.getMethodName(), ms.getParameter());
				}else{
					visitModelRecursively("/"+ms.getClassToCall(), ms.getMethodName(), ms.getParameter());
				}
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
