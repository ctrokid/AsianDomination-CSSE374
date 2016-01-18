package asm.visitor;

import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.IRelationshipManager;
import impl.MethodStatement;
import utils.DotClassUtils.RelationshipType;

public class MethodAssociationVisitor extends MethodVisitor {
	private IRelationshipManager _relationshipManager;
	private String _className;
	private String _arguments;
	private IClassMethod _classMethod;

	public MethodAssociationVisitor(int api, MethodVisitor decorated,
			IRelationshipManager relationshipManager, String className,
			String arguments, IClassMethod classMethod) {
		super(api, decorated);
		_relationshipManager = relationshipManager;
		_className = className;
		_arguments = arguments;
		_classMethod = classMethod;
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

		if (!className.equals(_className)) {
			_relationshipManager.addRelationshipEdge(_className, className,
					RelationshipType.ASSOCIATION);
		}
		
		// TODO: sequence level get set and not passed in?
		MethodStatement stmt = new MethodStatement(_className, className, methodName, returnType, 1);
		_classMethod.addMethodStatement(stmt);
	}

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
