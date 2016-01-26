package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.IProjectModel;
import api.ITargetClass;

import impl.ClassMethod;
import jdk.internal.org.objectweb.asm.Opcodes;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassMethodVisitor extends ClassVisitor {
	private ITargetClass _targetClass;

	public ClassMethodVisitor(int api, IProjectModel _model, String className) {
		super(api);
		this._targetClass = _model.getTargetClassByName(className);
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, IProjectModel _model, String className) {
		super(api, decorated);
		this._targetClass = _model.getTargetClassByName(className);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);

		String type = AsmClassUtils.GetReturnType(desc);
		String arguments = AsmClassUtils.GetArguments(desc);

		IClassMethod classMethod = new ClassMethod(name, arguments, access, type);
		MethodVisitor _decorator = new MethodAssociationVisitor(Opcodes.ASM5, toDecorate, _targetClass, arguments, classMethod);
		
		//FIXME:TODO Watch for UML bug totally ignoring static now
		if (!name.equals("clinit")) {
			_targetClass.addClassMethod(classMethod);
			if (!name.contains("<")) {
				_targetClass.addRelationship(RelationshipType.USES, type);
			}
		}
		return _decorator;
	}

}