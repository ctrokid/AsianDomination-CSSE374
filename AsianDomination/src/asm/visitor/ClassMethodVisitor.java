package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.IProjectModel;
import api.IRelationshipManager;
import api.ITargetClass;

import impl.ClassMethod;
import jdk.internal.org.objectweb.asm.Opcodes;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassMethodVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	private IRelationshipManager _relationshipManager;

	public ClassMethodVisitor(int api, IProjectModel _model, String className) {
		super(api);
		this._targetClass = _model.getTargetClassByName(className);
		this._relationshipManager = _model.getRelationshipManager();
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, IProjectModel _model, String className) {
		super(api, decorated);
		this._targetClass = _model.getTargetClassByName(className);
		this._relationshipManager = _model.getRelationshipManager();
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);

		String accessLevel = AsmClassUtils.GetAccessLevel(access);
		String type = AsmClassUtils.GetReturnType(desc);
		String arguments = AsmClassUtils.GetArguments(desc);
//		System.err.println("In class: " + _targetClass.getClassName() + " args for method: " + name + " = " + arguments);

		IClassMethod classMethod = new ClassMethod(name, arguments, accessLevel, type);
		MethodVisitor _decorator = new MethodAssociationVisitor(Opcodes.ASM5, toDecorate, _relationshipManager,
				_targetClass.getClassName(), arguments, classMethod);
		if (!name.equals("clinit")) {
			_targetClass.addClassMethod(classMethod);
		}
		if (!name.contains("<")) {
			_relationshipManager.addRelationshipEdge(_targetClass.getClassName(), type, RelationshipType.USES);
		}

		return _decorator;
	}

}