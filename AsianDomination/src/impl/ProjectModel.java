package impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import asm.visitor.ClassDeclarationVisitor;
import asm.visitor.ClassFieldVisitor;
import asm.visitor.ClassMethodVisitor;
import visitor.IDiagramOutputStream;
import visitor.IVisitor;
import visitor.UMLOutputStream;

public class ProjectModel implements IProjectModel {
	private String[] _targetClasses;
	private IRelationshipManager _relationshipManager;
	private IDiagramOutputStream _output;
	String _textOutputPath;
	String _diagramOutputPath;

	public ProjectModel(String[] targetClasses, IRelationshipManager relationshipManager, String textOutputPath,
			String diagramOutputPath) throws IOException {
		_targetClasses = targetClasses;
		_relationshipManager = relationshipManager;
		_textOutputPath = textOutputPath;
		_diagramOutputPath = diagramOutputPath;
		OutputStream out = new FileOutputStream(_textOutputPath);
		_output = new UMLOutputStream(out);

	}

	@Override
	public void accept(IVisitor v) {

	}

	@Override
	public void parseModel() throws IOException {
		_output.prepareFile();
		ITargetClass target = null;
		
		for (int i = 0; i < _targetClasses.length; i++) {
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
		}
		
		_relationshipManager.accept(_output);
		
		_output.endFile(_textOutputPath, _diagramOutputPath);
	}

	@Override
	public void setOutputStream(IDiagramOutputStream v) {
		_output = v;
	}

}
