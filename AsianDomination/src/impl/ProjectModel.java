package impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import api.IClassMethod;
import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import asm.visitor.ClassDeclarationVisitor;
import asm.visitor.ClassFieldVisitor;
import asm.visitor.ClassMethodVisitor;
import asm.visitor.DiagramType;
import visitor.IDiagramOutputStream;
import visitor.IVisitor;
import visitor.SequenceOutputStream;
import visitor.UMLOutputStream;

public class ProjectModel implements IProjectModel {
	private InputCommand _command;
	private ITargetClass[] _targetClasses;
	private IRelationshipManager _relationshipManager;
	
	private IDiagramOutputStream _output;
	private String _textOutputPath;
	private String _diagramOutputPath;

	public ProjectModel(InputCommand command, IRelationshipManager relationshipManager, String textOutputPath,
			String diagramOutputPath) throws IOException {
		_command = command;
		_targetClasses = new TargetClass[_command.getClasses().length];
		_relationshipManager = relationshipManager;
		_textOutputPath = textOutputPath;
		_diagramOutputPath = diagramOutputPath;
		
		OutputStream out = null;
		
		if (getCommand().getCommandType().equals("UML")) {
			out = new FileOutputStream(_textOutputPath + ".gv");
			_output = new UMLOutputStream(out);
		}
		else if (getCommand().getCommandType().equals("SEQ")) {
			out = new FileOutputStream(_textOutputPath + ".sd");
			_output = new SequenceOutputStream(out, this);
		}
		else {
			_output = null;
			System.err.println("Should not get here. Error in Project Model about to happen.");
		}
	}

	@Override
	public void accept(IVisitor v) {

	}

	@Override
	public void parseModel() throws IOException {
		_output.prepareFile();
		
		for (int i = 0; i < _command.getClasses().length; i++) {
			_targetClasses[i] = new TargetClass();
			
			ClassReader reader = new ClassReader(_command.getClasses()[i]);
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, _targetClasses[i], _relationshipManager);
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, _targetClasses[i], _relationshipManager);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, _targetClasses[i],
					_relationshipManager, DiagramType.UML);

			// TODO: add more DECORATORS here in later milestones to accomplish
			// specific tasks
			// Tell the Reader to use our (heavily decorated) ClassVisitor to
			// visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			
			// All TargetClass instances are populated with data
			// This should print out each class with the internal representation
			// With Sequence diagram, this should print the whole file
			if (getCommand().getCommandType().equals("UML"))
				_targetClasses[i].accept(_output);
		}
		
		if (getCommand().getCommandType().equals("UML"))
			_relationshipManager.accept(_output);
		else
			launchSequenceRun();
		
		_output.endFile(_textOutputPath, _diagramOutputPath);
	}
	
	private void launchSequenceRun() {
		SequenceInputCommand cmd = (SequenceInputCommand) _command;
		ITargetClass startingClass = null;
		
		for (ITargetClass targetClass : _targetClasses) {
			if (targetClass.getDeclaration().getName().equals(cmd.getInitialClass())) {
				startingClass = targetClass;
				break;
			}
		}
		
		startingClass.accept(_output);
	}

	@Override
	public void setOutputStream(IDiagramOutputStream v) {
		_output = v;
	}
	
	public IDiagramOutputStream getOutputStream() {
		return _output;
	}

	private InputCommand getCommand() {
		return _command;
	}

	@Override
	public ITargetClass getTargetClassByName(String className) {
		ITargetClass classToReturn = null;
		
		for (ITargetClass clazz : _targetClasses) {
			if (clazz.getDeclaration().getName().equals(className)) {
				classToReturn = clazz;
			}
		}
		
		return classToReturn;
	}

	@Override
	public Collection<IClassMethod> getTargetClassMethods(ITargetClass targetClass) {
		Collection<IClassMethod> methods = null;
		
		for (ITargetClass clazz : _targetClasses) {
			if (clazz.getDeclaration().getName().equals(targetClass.getDeclaration().getName())) {
				methods = clazz.getMethodParts();
			}
		}
		
		return methods;
	}

	@Override
	public InputCommand getInputCommand() {
		return _command;
	}

}
