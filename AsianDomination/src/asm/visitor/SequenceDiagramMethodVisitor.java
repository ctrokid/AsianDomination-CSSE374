package asm.visitor;

import org.objectweb.asm.MethodVisitor;

import api.IClassMethod;
import api.IRelationshipManager;
import impl.MethodStatement;

public class SequenceDiagramMethodVisitor extends MethodVisitor {
	private IRelationshipManager _relationshipManager;
	private String _className;
	private String _arguments;
	private IClassMethod _classMethod;

	public SequenceDiagramMethodVisitor(int api,
			IRelationshipManager relationshipManager, String className,
			String arguments, IClassMethod classMethod) {
		super(api);
		_relationshipManager = relationshipManager;
		_className = className;
		_arguments = arguments;
		_classMethod = classMethod;
	}

	public SequenceDiagramMethodVisitor(int api, MethodVisitor decorated,
			IRelationshipManager relationshipManager, String className,
			String arguments, IClassMethod classMethod) {
		super(api,decorated);
		_relationshipManager = relationshipManager;
		_className = className;
		_arguments = arguments;
		_classMethod = classMethod;
	}

	@Override
	public void visitMethodInsn(int arg0, String className, String methodName,
			String returnType, boolean arg4) {
		super.visitMethodInsn(arg0, className, methodName, returnType, arg4);
		
		//Store things here
		// FIXME : integrate method parameters
		
		MethodStatement stmt = new MethodStatement(_className, className, methodName, returnType);
		_classMethod.addStatement(stmt);
		
	}

}
