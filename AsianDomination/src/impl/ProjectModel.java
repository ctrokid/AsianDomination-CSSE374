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

	public ProjectModel(InputCommand command) {
		_command = command;
		_targetClasses = new LinkedHashMap<String, ITargetClass>();
		_relationshipManager = new RelationshipManager();
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
	public Collection<ITargetClass> getTargetClasses() {
		Collection<ITargetClass> targetClasses = new ArrayList<ITargetClass>();
		for(String c: _targetClasses.keySet()){
			targetClasses.add(_targetClasses.get(c));
		}
		return targetClasses;
	}

	@Override
	public void addClass(String classPath) {
		if(_targetClasses.containsKey(classPath)){
			return;
		}
		
		ITargetClass clazz = dynamicallyReadClass(classPath);
		_targetClasses.put(classPath, clazz);
	}

	@Override
	public void decorateClass(ITargetClass clazz) {
		_targetClasses.put(clazz.getClassName(), clazz);
	}

	@Override
	public void buildModel() {
		IAddStrategy addStrategy  = _command.getAddStrategy(this);
		addStrategy.buildModel(_command.getInputParameters());
		
	}

	@Override
	public void printModel() {
		if(_targetClasses.isEmpty()){
			System.out.println("The model is empty!");
		} else {
			IDiagramOutputStream digramOutputStream = _command.getOutputStream();
			digramOutputStream.setProjectModel(this);
			digramOutputStream.writeOutput();
			digramOutputStream.generateDiagram(_command.getDiagramOutputPath());
		}
		
	}
	
	@Override
	public ITargetClass forcefullyGetClassByName(String className) {
		ITargetClass clazz = getTargetClassByName(className);
		
		if (clazz == null) {
			return dynamicallyReadClass(className);
		}
		
		return clazz;
	}
	
	private ITargetClass dynamicallyReadClass(String classPath) {
		ITargetClass clazz = new TargetClass(classPath);
		
		try {
			ClassReader reader = new ClassReader(classPath);
			
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, clazz, _relationshipManager);
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, clazz, _relationshipManager);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, clazz, _relationshipManager);
			
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		} catch (IOException e) {
			System.err.println("Can not find " + classPath);
			e.printStackTrace();
		}
		
		return clazz;
	}

}
