package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassField;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassFieldVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	private IRelationshipManager _relationshipManager;
	
	public ClassFieldVisitor(int api, IProjectModel _model, String className) {
		super(api);
		_targetClass = _model.getTargetClassByName(className);
		_relationshipManager = _model.getRelationshioManager();
	}

	public ClassFieldVisitor(int api, ClassVisitor decorated, IProjectModel model, String className) {
		super(api, decorated);
		_targetClass = model.getTargetClassByName(className);
		_relationshipManager = model.getRelationshioManager();
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String returnType = AsmClassUtils.GetReturnType(desc);
		String accessLevel = AsmClassUtils.GetAccessLevel(access);
		
		if (signature != null) {
			signature = AsmClassUtils.parseSignature(signature);
			_relationshipManager.addRelationshipEdge(_targetClass.getClassName(), signature.substring(2, signature.length()-2), RelationshipType.ASSOCIATION);
		}
		else
			_relationshipManager.addRelationshipEdge(_targetClass.getClassName(), returnType, RelationshipType.ASSOCIATION);
		
		_targetClass.addClassField((new ClassField(name, accessLevel, signature, returnType)));
		
		return toDecorate;
	};
}