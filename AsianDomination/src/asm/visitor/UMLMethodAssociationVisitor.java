package asm.visitor;

import org.objectweb.asm.MethodVisitor;

import api.IRelationshipManager;
import utils.DotClassUtils.RelationshipType;

public class UMLMethodAssociationVisitor extends MethodVisitor {
	private IRelationshipManager _relationshipManager;
	private String _className;
	private String _arguments;

	public UMLMethodAssociationVisitor(int api,
			IRelationshipManager relationshipManager, String className,
			String arguments) {
		super(api);
		_relationshipManager = relationshipManager;
		_className = className;
		_arguments = arguments;
	}

	public UMLMethodAssociationVisitor(int api, MethodVisitor decorated,
			IRelationshipManager relationshipManager, String className,
			String arguments) {
		super(api, decorated);
		_relationshipManager = relationshipManager;
		_className = className;
		_arguments = arguments;
	}

	@Override
	public void visitFieldInsn(int arg0, String className, String fieldName,
			String fieldType) {
		super.visitFieldInsn(arg0, className, fieldName, fieldType);

		// FIXME: collections don't get populated with internal contents
		if (!className.equals(_className)) {
			_relationshipManager.addRelationshipEdge(_className, className,
					RelationshipType.ASSOCIATION);
		}
	}

	@Override
	public void visitMethodInsn(int arg0, String className, String methodName,
			String returnType, boolean arg4) {
		super.visitMethodInsn(arg0, className, methodName, returnType, arg4);

		// Recursively go into variable className's class
		// add new next node to current node
		System.out.println(_className + " " + className + " " + methodName);
		if (!className.equals(_className)) {
			_relationshipManager.addRelationshipEdge(_className, className,
					RelationshipType.ASSOCIATION);
		}
	}

	// FIXME: Not needed for Milestone 2, might need later
	// @Override
	// public void visitTypeInsn(int code, String classType) {
	// super.visitTypeInsn(code, classType);
	// System.err.println("visitTypeInsn");
	// System.out.println(code);
	// System.out.println(classType);
	// }

	@Override
	public void visitVarInsn(int code, int var) {
		super.visitVarInsn(code, var);

		if (var == 1) {
			String[] args = _arguments.split(",");
			for (String arg : args) {
				_relationshipManager.addRelationshipEdge(_className,
						arg.trim(), RelationshipType.USES);
			}
		}
	}
}
