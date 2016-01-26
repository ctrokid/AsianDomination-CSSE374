package asm.visitor;


import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;

import api.IProjectModel;
import api.ITargetClass;
import impl.ClassDeclaration;
import utils.DotClassUtils.RelationshipType;

public class ClassDeclarationVisitor extends ClassVisitor {
	protected ITargetClass _targetClass;
	
	public ClassDeclarationVisitor(int api,IProjectModel _model, String className) {
		super(api);
		_targetClass = _model.getTargetClassByName(className);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superClass, String[] interfaces) {
		for (int i = 0; i < interfaces.length; i++) {
			_targetClass.addRelationship(RelationshipType.IMPLEMENTATION, interfaces[i]);
		}
		_targetClass.addRelationship(RelationshipType.INHERITANCE, superClass);
		_targetClass.addDeclarationVisitor(new ClassDeclaration(superClass, signature, Arrays.asList(interfaces)));
		
		super.visit(version, access, name, signature, superClass, interfaces);
	}
}
