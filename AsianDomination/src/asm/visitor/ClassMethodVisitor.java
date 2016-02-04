<<<<<<< HEAD
package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.ITargetClass;

import impl.ClassMethod;
import jdk.internal.org.objectweb.asm.Opcodes;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassMethodVisitor extends ClassVisitor {
	private ITargetClass _targetClass;

	public ClassMethodVisitor(int api, ITargetClass targetClass) {
		super(api);
		this._targetClass = targetClass;
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, ITargetClass targetClass) {
		super(api, decorated);
		this._targetClass = targetClass;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);

		String type = AsmClassUtils.GetReturnType(desc);

		IClassMethod classMethod = new ClassMethod(name, desc, access, type);
		
		String args = AsmClassUtils.GetArguments(desc, false);
		MethodVisitor _decorator = new MethodAssociationVisitor(Opcodes.ASM5, toDecorate, _targetClass, args, classMethod);
		
		//FIXME:TODO Watch for UML bug totally ignoring static now
		if (!name.equals("clinit")) {
			_targetClass.addClassMethod(classMethod);
			if (!name.contains("<")) {
				_targetClass.addRelationship(RelationshipType.USES, type);
			}
		}
		return _decorator;
	}

=======
package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;

import impl.ClassMethod;
import jdk.internal.org.objectweb.asm.Opcodes;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassMethodVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	private IRelationshipManager _relationshipManager;
	public ClassMethodVisitor(int api, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api);
		this._targetClass = targetClass;
		this._relationshipManager = relationshipManager;
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, ITargetClass targetClass, IRelationshipManager relationshipManager) {
		super(api, decorated);
		this._targetClass = targetClass;
		this._relationshipManager = relationshipManager;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);

		String type = AsmClassUtils.GetReturnType(desc);

		IClassMethod classMethod = new ClassMethod(name, desc, access, type);
		
		String args = AsmClassUtils.GetArguments(desc, false);
		MethodVisitor _decorator = new MethodAssociationVisitor(Opcodes.ASM5, toDecorate, _targetClass, args, classMethod, this._relationshipManager);
		
		//FIXME:TODO Watch for UML bug totally ignoring static now
		if (!name.equals("clinit")) {
			_targetClass.addClassMethod(classMethod);
			if (!name.contains("<")) {
				_relationshipManager.addRelationship(_targetClass.getClassName(),RelationshipType.USES, type);
			}
		}
		return _decorator;
	}

>>>>>>> a1a8608d53853ea8a31a3765e00d308070053e9e
}