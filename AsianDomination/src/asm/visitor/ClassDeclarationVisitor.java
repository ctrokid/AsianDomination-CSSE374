package asm.visitor;


import org.objectweb.asm.ClassVisitor;

import Utils.DotClassUtils.RelationshipType;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassDeclaration;

public class ClassDeclarationVisitor extends ClassVisitor {
	protected ITargetClass _targetClass;
	protected IRelationshipManager _relationshipManager;
	
	public ClassDeclarationVisitor(int api, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		_targetClass.addPart(new ClassDeclaration(name, signature, superName, interfaces));
		_relationshipManager.addRelationshipEdge(_targetClass.getDeclaration().getName(), superName, RelationshipType.INHERITANCE);
		for (String iface : interfaces) {
			_relationshipManager.addRelationshipEdge(_targetClass.getDeclaration().getName(), iface, RelationshipType.IMPLEMENTATION);
		}
		
		super.visit(version, access, name, signature, superName, interfaces);
	}
}
