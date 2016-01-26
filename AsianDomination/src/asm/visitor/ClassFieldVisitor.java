package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import api.IProjectModel;
//import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassField;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassFieldVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
//	private IRelationshipManager _relationshipManager;
	
	public ClassFieldVisitor(int api, IProjectModel _model, String className) {
		super(api);
		_targetClass = _model.getTargetClassByName(className);
//		_relationshipManager = _model.getRelationshipManager();
	}

	public ClassFieldVisitor(int api, ClassVisitor decorated, IProjectModel model, String className) {
		super(api, decorated);
		_targetClass = model.getTargetClassByName(className);
//		_relationshipManager = model.getRelationshipManager();
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String returnType = AsmClassUtils.GetReturnType(desc);
		
		if (signature != null) {
			String sig = AsmClassUtils.parseSignature(signature, false);
			signature = "\\<" + AsmClassUtils.parseSignature(signature, true) + "\\>";
			
			for (String param : sig.split(",")) {
				_targetClass.addRelationship(RelationshipType.ASSOCIATION, param.trim());
//				_relationshipManager.addRelationshipEdge(_targetClass.getClassName(), param.trim(), RelationshipType.ASSOCIATION);
			}
		}
		else
			_targetClass.addRelationship(RelationshipType.ASSOCIATION, returnType);
//			_relationshipManager.addRelationshipEdge(_targetClass.getClassName(), returnType, RelationshipType.ASSOCIATION);
		
		_targetClass.addClassField((new ClassField(name, access, signature, returnType)));
		
		return toDecorate;
	};
}