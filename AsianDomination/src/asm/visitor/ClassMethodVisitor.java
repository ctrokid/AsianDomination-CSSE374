package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import api.IRelationshipManager;
import api.ITargetClass;
import classParser.AsmClassUtils;
import impl.ClassMethod;

public class ClassMethodVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	private IRelationshipManager _relationshipManager;
	
	public ClassMethodVisitor(int api, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api, decorated);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		MethodVisitor decorated = new MethodAssociationVisitor(Opcodes.ASM5, toDecorate, _relationshipManager, name);
		
		String accessLevel = AsmClassUtils.GetAccessLevel(access);
		String type = AsmClassUtils.GetReturnType(desc);
		String arguments = AsmClassUtils.GetArguments(desc);

		if (!name.equals("<init>"))
			_targetClass.addPart(new ClassMethod(name, arguments, accessLevel, type));
		return decorated;
	}
}