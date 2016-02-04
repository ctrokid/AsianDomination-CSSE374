<<<<<<< HEAD
package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import api.ITargetClass;
import impl.ClassField;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassFieldVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	
	public ClassFieldVisitor(int api, ITargetClass targetClass) {
		super(api);
		_targetClass = targetClass;
	}

	public ClassFieldVisitor(int api, ClassVisitor decorated, ITargetClass targetClass) {
		super(api, decorated);
		_targetClass = targetClass;
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String returnType = AsmClassUtils.GetReturnType(desc);

		if (signature != null) {
			String sig = AsmClassUtils.parseSignature(signature, false);
			signature = "\\<" + AsmClassUtils.parseSignature(signature, true) + "\\>";
			
			for (String param : sig.split(",")) {
				_targetClass.addRelationship(RelationshipType.ASSOCIATION, param.trim());
			}
		}

		_targetClass.addRelationship(RelationshipType.ASSOCIATION, returnType);
		_targetClass.addClassField((new ClassField(name, access, signature, returnType)));
		
		return toDecorate;
	};
=======
package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassField;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

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

		if (signature != null) {
			String sig = AsmClassUtils.parseSignature(signature, false);
			signature = "\\<" + AsmClassUtils.parseSignature(signature, true) + "\\>";

			for (String param : sig.split(",")) {
				_relationshipManager.addRelationship(_targetClass.getClassName(), RelationshipType.ASSOCIATION,
						param.trim());
			}
		}

		_relationshipManager.addRelationship(_targetClass.getClassName(), RelationshipType.ASSOCIATION, returnType);
		_targetClass.addClassField((new ClassField(name, access, signature, returnType)));

		return toDecorate;
	};
>>>>>>> a1a8608d53853ea8a31a3765e00d308070053e9e
}