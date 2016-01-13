package impl;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import asm.visitor.ClassDeclarationVisitor;
import asm.visitor.ClassFieldVisitor;
import asm.visitor.ClassMethodVisitor;
import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.IVisitor;
import visitor.UMLOutputStream;

public class ProjectModel implements IProjectModel  {
	private String[] _targetClasses;
	private IRelationshipManager _relationshipManager;
	private IVisitor _output;
	String _textOutputPath;
	String _diagramOutputPath;

	public ProjectModel(String[] targetClass, IRelationshipManager relationshipManager, String textOutputPath,
			String diagramOutputPath) {
		_targetClasses = targetClass;
		_relationshipManager = relationshipManager;
		_textOutputPath = textOutputPath;
		_diagramOutputPath = diagramOutputPath;

	}

	@Override
	public void accept(IVisitor v) {

	}
	
	@Override
	public void parseModel() throws IOException {
		//TODO: not complete yet
		ITargetClass target = null;
		for (int i = 0; i < _targetClasses.length; i++) {
			// ASM's ClassReader does the heavy lifting of parsing the compiled
			// Java class
			target = new TargetClass();
			ClassReader reader = new ClassReader(_targetClasses[i]);
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, target, _relationshipManager);
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, target, _relationshipManager);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, target,
					_relationshipManager);
			
			// TODO: add more DECORATORS here in later milestones to accomplish
			// specific tasks
			// Tell the Reader to use our (heavily decorated) ClassVisitor to
			// visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			// All TargetClass instances are populated with data
			// This should print out each class with the internal representation
			target.accept(_output);
			
			((UMLOutputStream) _output).endDotFile();
			LaunchDiagramGenerator.RunGVEdit(_textOutputPath, _diagramOutputPath, DiagramFileExtension.PDF);
		}

	}

	@Override
	public void setOutputStream(IVisitor v) {
		_output = v;
	}

}
