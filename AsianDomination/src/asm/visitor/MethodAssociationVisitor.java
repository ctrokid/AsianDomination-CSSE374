package asm.visitor;

import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.ITargetClass;
import impl.MethodStatement;
import utils.DotClassUtils.RelationshipType;

public class MethodAssociationVisitor extends MethodVisitor {
	private String _arguments;
	private IClassMethod _classMethod;
	private ITargetClass _targetClass;

	public MethodAssociationVisitor(int api, MethodVisitor decorated, ITargetClass targetClass, String arguments,
			IClassMethod classMethod) {
		super(api, decorated);
		_targetClass = targetClass;
		_arguments = arguments;
		_classMethod = classMethod;
	}

	@Override
	public void visitFieldInsn(int arg0, String className, String fieldName, String fieldType) {
		super.visitFieldInsn(arg0, className, fieldName, fieldType);

		// FIXME: collections don't get populated with internal contents
		// FIXME: could be a bug for singletons
		// if (!className.equals(_className)) {
		// _relationshipManager.addRelationshipEdge(_className, className,
		// RelationshipType.ASSOCIATION);
		_targetClass.addRelationship(RelationshipType.ASSOCIATION, className);
		// }
	}

	@Override
	public void visitMethodInsn(int arg0, String className, String methodName, String returnType, boolean arg4) {
		super.visitMethodInsn(arg0, className, methodName, returnType, arg4);

		RelationshipType relationshipType = RelationshipType.ASSOCIATION;

		// FIXME : this could be a bug.
		// put in for factory methods to return uses relationship
		if (!className.equals(_classMethod.getReturnType())) {
			relationshipType = RelationshipType.USES;
		}
		_targetClass.addRelationship(relationshipType, className);

		MethodStatement stmt = new MethodStatement(_targetClass.getClassName(), className, methodName, returnType);
		_classMethod.addMethodStatement(stmt);
	}

	@Override
	public void visitVarInsn(int code, int var) {
		super.visitVarInsn(code, var);

		if (var == 1) {
			String[] args = _arguments.split(",");
			for (String arg : args) {
				_targetClass.addRelationship(RelationshipType.USES, arg.trim());
			}
		}
	}
}
