package impl;

import java.util.Collection;

import api.IClassDeclaration;
import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;
import utils.AsmClassUtils;
import visitor.SequenceOutputStream;

public class SequenceDiagramTraverser {
	private IProjectModel _model;
	private SequenceOutputStream _output;
	private SequenceInputCommand _inputCommand;
	private int _callDepth;
	
	public SequenceDiagramTraverser(SequenceOutputStream output) {
		_output = output;
		_model = output.getProjectModel();
		_inputCommand = (SequenceInputCommand) _model.getInputCommand();
		_callDepth = _inputCommand.getCallDepth();
	}

	public void startOutput() {
		ITargetClass targetClass = _model.getTargetClassByName(_inputCommand.getInitialClass());
		IClassMethod initialMethod = getInitialMethod(targetClass);
		
		search("", targetClass.getDeclaration(), initialMethod, 1);
	}
	
	private void search(String prevClass, IClassDeclaration clazz, IClassMethod method, int level) {
		clazz.accept(_output);
		Collection<MethodStatement> stmts = method.getStatements();
		
		for (MethodStatement stmt : stmts) {
			stmt.accept(_output);
			
			if (level != _callDepth) {
				ITargetClass targetClass = _model.getTargetClassByName(AsmClassUtils.GetStringStrippedByCharacter(stmt.getClassName(), '/'));
				IClassMethod nextMethod = getMethodFromCollection(_model.getTargetClassMethods(targetClass), stmt.getMethodName());
				
				// do recursion
				if (targetClass != null && nextMethod != null)
					this.search(clazz.getName(), targetClass.getDeclaration(), nextMethod, level+1);
			}
		}
	}
	
	private IClassMethod getInitialMethod(ITargetClass targetClass) {
		IClassMethod method = null;
		Collection<IClassMethod> methods = _model.getTargetClassMethods(targetClass);
		
		method = getMethodFromCollection(methods, _inputCommand.getMethodName());
		
		return method;
	}
	
	private IClassMethod getMethodFromCollection(Collection<IClassMethod> methods, String methodName) {
		IClassMethod method = null;
		
		for (IClassMethod md : methods) {
			// TODO: think of overloaded methods
			if (md.getName().equals(methodName)) {
				method = md;
				break;
			}
		}
		
		return method;
	}
}
 