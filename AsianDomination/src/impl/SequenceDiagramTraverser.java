package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
	private ArrayList<MethodStatement> _stmtsToWrite;
	private HashMap<Integer, ArrayList<String>> _classesAdded;

	public SequenceDiagramTraverser(SequenceOutputStream output) {
		_output = output;
		_model = output.getProjectModel();
		_inputCommand = (SequenceInputCommand) _model.getInputCommand();
		_callDepth = _inputCommand.getCallDepth();
		_stmtsToWrite = new ArrayList<MethodStatement>();
		_classesAdded = new HashMap<Integer, ArrayList<String>>();
	}

	public void startOutput() {
		ITargetClass targetClass = _model.getTargetClassByName(_inputCommand.getInitialClass());
		IClassMethod initialMethod = getInitialMethod(targetClass);
		IClassDeclaration decl = targetClass.getDeclaration();
		decl.setFirstClass();

		search("", decl, initialMethod, 1, 0);
		_output.writeNewLine();
		writeStatements();
	}

	private void search(String prevClass, IClassDeclaration clazz, IClassMethod method, int level, int seqLevel) {
		// if (!_classesAdded.contains(clazz.getName())) {
		if(!_classesAdded.containsKey(seqLevel)){
			_classesAdded.put(seqLevel, new ArrayList<String>());
		}
		if (!_classesAdded.get(seqLevel).contains(clazz.getName())) {
			clazz.accept(_output);
			// _classesAdded.add(clazz.getName());
			_classesAdded.get(seqLevel).add(clazz.getName());
		}

		Collection<MethodStatement> stmts = method.getStatements();

		for (MethodStatement stmt : stmts) {
			_stmtsToWrite.add(stmt);

			if (level != _callDepth) {
				ITargetClass targetClass = _model
						.getTargetClassByName(AsmClassUtils.GetStringStrippedByCharacter(stmt.getClassName(), '/'));
				IClassMethod nextMethod = getMethodFromCollection(_model.getTargetClassMethods(targetClass),
						stmt.getMethodName());

				// do recursion
				if (targetClass != null && nextMethod != null)
					this.search(clazz.getName(), targetClass.getDeclaration(), nextMethod, level + 1, seqLevel++);
				else if (targetClass != null
						&& !_classesAdded.get(seqLevel).contains(targetClass.getDeclaration().getName())) {
					targetClass.getDeclaration().accept(_output);
					_classesAdded.get(seqLevel).add(targetClass.getDeclaration().getName());
				}

			}
		}
	}

	private void writeStatements() {
		for (MethodStatement stmt : _stmtsToWrite) {
			stmt.accept(_output);
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
