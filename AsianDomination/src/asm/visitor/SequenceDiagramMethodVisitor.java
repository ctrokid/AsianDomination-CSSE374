package asm.visitor;

import org.objectweb.asm.MethodVisitor;

import api.IRelationshipManager;

public class SequenceDiagramMethodVisitor extends MethodVisitor {
	private IRelationshipManager _relationshipManager;
	private String _className;
	private String _arguments;

	public SequenceDiagramMethodVisitor(int api,
			IRelationshipManager relationshipManager, String className,
			String arguments) {
		super(api);
		_relationshipManager = relationshipManager;
		_className = className;
		_arguments = arguments;

	}

	public SequenceDiagramMethodVisitor(int api, MethodVisitor decorated,
			IRelationshipManager relationshipManager, String className,
			String arguments) {
		super(api,decorated);
		_relationshipManager = relationshipManager;
		_className = className;
		_arguments = arguments;
	}

	@Override
	public void visitMethodInsn(int arg0, String className, String methodName,
			String returnType, boolean arg4) {
		super.visitMethodInsn(arg0, className, methodName, returnType, arg4);
		System.out.println(_className + " " + className + " " + methodName);
		//Store things here
	
	}

}
