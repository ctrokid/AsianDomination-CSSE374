package asm.visitor;

import org.objectweb.asm.MethodVisitor;

import api.IRelationshipManager;
import visitor.DotClassUtils.RelationshipType;

public class MethodAssociationVisitor extends MethodVisitor {
	private IRelationshipManager _relationshipManager;
	private String _className;
	
	public MethodAssociationVisitor(int api, IRelationshipManager relationshipManager, String className) {
		super(api);
		_relationshipManager = relationshipManager;
		_className = className;
	}
	
	public MethodAssociationVisitor(int api, MethodVisitor decorated, IRelationshipManager relationshipManager, String className) {
		super(api, decorated);
		_relationshipManager = relationshipManager;
		_className = className;
	}

	@Override
	public void visitFieldInsn(int arg0, String className, String fieldName, String fieldType) {
		super.visitFieldInsn(arg0, className, fieldName, fieldType);
		// this class accesses this field
		if (!className.equals(_className)) {
			// TODO: make sure not a default type
			// TODO: what if field type is association we haven't seen before??
			_relationshipManager.addRelationshipEdge(_className, className, RelationshipType.ASSOCIATION);
		}
		System.out.println(className);
		System.out.println(fieldName);
		System.out.println(fieldType);
	}

	@Override
	public void visitMethodInsn(int arg0, String className, String methodName, String returnType, boolean arg4) {
		super.visitMethodInsn(arg0, className, methodName, returnType, arg4);
		System.out.println(className);
		System.out.println(methodName);
		System.out.println(returnType);
	}

	@Override
	public void visitTypeInsn(int code, String classType) {
		super.visitTypeInsn(code, classType);
		System.out.println(code);
		System.out.println(classType);
	}

	@Override
	public void visitVarInsn(int code, int var) {
		super.visitVarInsn(code, var);
		System.out.println(code);
		System.out.println(var);
	}
	
}
