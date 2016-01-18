package impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import asm.visitor.ClassDeclarationVisitor;
import asm.visitor.ClassFieldVisitor;
import asm.visitor.ClassMethodVisitor;
import construction.IAddStrategy;
import input.InputCommand;
import output.IDiagramOutputStream;

public class ProjectModel implements IProjectModel {
	private InputCommand _command;
	private HashMap<String, ITargetClass> _targetClasses;
	private IRelationshipManager _relationshipManager;

	public ProjectModel(InputCommand command) throws IOException {
		_command = command;
		_targetClasses = new LinkedHashMap<String, ITargetClass>();
		_relationshipManager = new RelationshipManager();
	}

	@Override
	public void parseModel() throws IOException {
		IAddStrategy addStrategy  = _command.getAddStrategy();
		addStrategy.setProjectModel(this);
		addStrategy.buildModel(_command.getInputParameters());
		
		IDiagramOutputStream digramOutputStream = _command.getOutputStream();
		digramOutputStream.setProjectModel(this);
		digramOutputStream.writeOutput();
		digramOutputStream.generateDiagram();
	}

	@Override
	public ITargetClass getTargetClassByName(String className) {
		return _targetClasses.get(className);
	}

	@Override
	public InputCommand getInputCommand() {
		return _command;
	}

	@Override
	public IRelationshipManager getRelationshipManager() {
		return _relationshipManager;
	}

	@Override
	public Collection<ITargetClass> getTargetClasses() {
		Collection<ITargetClass> targetClasses = new ArrayList<ITargetClass>();
		for(String c: _targetClasses.keySet()){
			targetClasses.add(_targetClasses.get(c));
		}
		return targetClasses;
	}

	@Override
	public void addClass(String classPath) {
		ITargetClass clazz = new TargetClass(classPath);
		if(_targetClasses.containsKey(classPath)){
			return;
		}
		
		_targetClasses.put(classPath, clazz);
		
		try {
			ClassReader reader = new ClassReader(classPath);
			
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, this, classPath);
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, this, classPath);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, this, classPath);
			
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
