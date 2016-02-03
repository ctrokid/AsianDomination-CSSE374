package asm.visitor;

import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;

import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassDeclaration;
import utils.DotClassUtils.RelationshipType;

public class ClassDeclarationVisitor extends ClassVisitor {
	protected ITargetClass _targetClass;
	private IRelationshipManager _relationshipManager;

	public ClassDeclarationVisitor(int api, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superClass, String[] interfaces) {
		for (int i = 0; i < interfaces.length; i++) {
			_relationshipManager.addRelationship(_targetClass.getClassName(), RelationshipType.IMPLEMENTATION,
					interfaces[i]);
		}
		_relationshipManager.addRelationship(_targetClass.getClassName(), RelationshipType.INHERITANCE, superClass);
		_targetClass.setClassDeclaration(new ClassDeclaration(superClass, signature, Arrays.asList(interfaces)));

		super.visit(version, access, name, signature, superClass, interfaces);
	}
}
