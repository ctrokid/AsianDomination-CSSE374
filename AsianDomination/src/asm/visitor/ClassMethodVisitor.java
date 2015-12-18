package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import api.ITargetClass;
import classParser.DotClassUtils;
import impl.ClassMethod;

public class ClassMethodVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	
	public ClassMethodVisitor(int api, ITargetClass targetClass) {
		super(api);
		_targetClass = targetClass;
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, ITargetClass targetClass) {
		super(api, decorated);
		_targetClass = targetClass;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);

		String accessLevel = DotClassUtils.GetAccessLevel(access);
		String returnType = DotClassUtils.GetReturnType(desc);
		String args = DotClassUtils.GetArguments(desc);

		_targetClass.addPart(new ClassMethod(name, returnType, accessLevel, args));

		return toDecorate;
	}
}