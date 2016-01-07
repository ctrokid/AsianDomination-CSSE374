package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import Utils.AsmClassUtils;
import Utils.DotClassUtils.RelationshipType;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassField;

public class ClassFieldVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	private IRelationshipManager _relationshipManager;
	
	public ClassFieldVisitor(int api, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
	}

	public ClassFieldVisitor(int api, ClassVisitor decorated, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api, decorated);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String returnType = AsmClassUtils.GetReturnType(desc);
		String accessLevel = AsmClassUtils.GetAccessLevel(access);
		
		if (signature != null) {
			signature = AsmClassUtils.parseSignature(signature);
			_relationshipManager.addRelationshipEdge(_targetClass.getDeclaration().getName(), signature.substring(2, signature.length()-2), RelationshipType.ASSOCIATION);
		}
		else
			_relationshipManager.addRelationshipEdge(_targetClass.getDeclaration().getName(), returnType, RelationshipType.ASSOCIATION);
		
		_targetClass.addPart(new ClassField(name, accessLevel, signature, returnType));
		
		return toDecorate;
	};
}