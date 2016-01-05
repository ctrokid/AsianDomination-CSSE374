package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import api.ITargetClass;
import classParser.AsmClassUtils;
import impl.ClassField;

public class ClassFieldVisitor extends ClassVisitor {
	protected ITargetClass _targetClass;
	
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
		String accessLevel = AsmClassUtils.GetAccessLevel(access);
		
		_targetClass.addPart(new ClassField(name, accessLevel, signature, returnType));
		
		return toDecorate;
	};
}