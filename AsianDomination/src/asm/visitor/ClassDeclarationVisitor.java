package asm.visitor;


import org.objectweb.asm.ClassVisitor;

import api.ITargetClass;
import impl.ClassDeclaration;

public class ClassDeclarationVisitor extends ClassVisitor {
	protected ITargetClass _targetClass;
	
	public ClassDeclarationVisitor(int api, ITargetClass targetClass) {
		super(api);
		_targetClass = targetClass;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		_targetClass.addPart(new ClassDeclaration(name, signature, superName, interfaces));
		// FIXME: ??? super come before or after addPart??
		super.visit(version, access, name, signature, superName, interfaces);
	}
}
