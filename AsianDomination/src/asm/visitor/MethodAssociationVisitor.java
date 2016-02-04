package asm.visitor;

import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.MethodStatement;
import impl.RelationshipManager;
import utils.DotClassUtils.RelationshipType;

public class MethodAssociationVisitor extends MethodVisitor {
	private String _arguments;
	private IClassMethod _classMethod;
	private ITargetClass _targetClass;
	private IRelationshipManager _relatinoshipManager;

	public MethodAssociationVisitor(int api, MethodVisitor decorated, ITargetClass targetClass, String arguments,
			IClassMethod classMethod, RelationshipManager relationshipManager) {
		super(api, decorated);
		_targetClass = targetClass;
		_arguments = arguments;
		_classMethod = classMethod;
		_relatinoshipManager = relationshipManager;
	}

	@Override
	public void visitFieldInsn(int arg0, String className, String fieldName, String fieldType) {
		super.visitFieldInsn(arg0, className, fieldName, fieldType);

		// FIXME: collections don't get populated with internal contents
		// FIXME: could be a bug for singletons
		if (!className.equals(_targetClass.getClassName())) {
			_relatinoshipManager.addRelationship(_targetClass.getClassName(), RelationshipType.ASSOCIATION, className);

		}
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

		if (!className.equals(_targetClass.getDeclaration().getSuperClassType()))
			_relatinoshipManager.addRelationship(_targetClass.getClassName(), relationshipType, className);
		MethodStatement stmt = new MethodStatement(_targetClass.getClassName(), className, methodName, returnType);
		_classMethod.addMethodStatement(stmt);
	}

	@Override
	public void visitVarInsn(int code, int var) {
		super.visitVarInsn(code, var);

		if (var == 1) {
			String[] args = _arguments.split(",");
			for (String arg : args) {
				_relatinoshipManager.addRelationship(_targetClass.getClassName(), RelationshipType.USES, arg.trim());
			}
		}
	}
}
