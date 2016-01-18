package asm.visitor;


import org.objectweb.asm.ClassVisitor;

import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import utils.DotClassUtils.RelationshipType;

public class ClassDeclarationVisitor extends ClassVisitor {
	protected ITargetClass _targetClass;
	protected IRelationshipManager _relationshipManager;
	
	public ClassDeclarationVisitor(int api,IProjectModel _model, String className) {
		super(api);
		_targetClass = _model.getTargetClassByName(className);
		_relationshipManager = _model.getRelationshipManager();
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		for (int i = 0; i < interfaces.length; i++) {
			_relationshipManager.addRelationshipEdge(name, interfaces[i], RelationshipType.IMPLEMENTATION);
		}
		_relationshipManager.addRelationshipEdge(name, superName, RelationshipType.INHERITANCE);
		
		super.visit(version, access, name, signature, superName, interfaces);
	}
}
