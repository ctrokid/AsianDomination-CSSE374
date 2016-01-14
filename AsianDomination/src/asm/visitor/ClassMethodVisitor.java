package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassMethod;
import utils.AsmClassUtils;
import utils.DotClassUtils.RelationshipType;

public class ClassMethodVisitor extends ClassVisitor {
	private ITargetClass _targetClass;
	private IRelationshipManager _relationshipManager;
	private DiagramType _diagramType;
	
	public ClassMethodVisitor(int api, ITargetClass targetClass, IRelationshipManager relationshipManager, DiagramType diagramType) {
		super(api);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
		_diagramType = diagramType;
	}

	public ClassMethodVisitor(int api, ClassVisitor decorated, ITargetClass targetClass, IRelationshipManager relationshipManager, DiagramType diagramType) {
		super(api, decorated);
		_targetClass = targetClass;
		_relationshipManager = relationshipManager;
		_diagramType = diagramType;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		
		String accessLevel = AsmClassUtils.GetAccessLevel(access);
		String type = AsmClassUtils.GetReturnType(desc);
		String arguments = AsmClassUtils.GetArguments(desc);
		
		IClassMethod classMethod = new ClassMethod(name, arguments, accessLevel, type);
//		MethodVisitor _decorator = createDecoratedVisitor(toDecorate, arguments, classMethod);
		MethodVisitor _decorator = new UMLMethodAssociationVisitor(Opcodes.ASM5, toDecorate, _relationshipManager, _targetClass.getDeclaration().getName(), arguments, classMethod);
		
		if (!name.contains("<")) {
			_targetClass.addPart(classMethod);
			_relationshipManager.addRelationshipEdge(_targetClass.getDeclaration().getName(), type, RelationshipType.USES);
		}

		return _decorator;
	}
	
	@SuppressWarnings("unused")
	private MethodVisitor createDecoratedVisitor(MethodVisitor toDecorate, String arguments, IClassMethod classMethod) {
		MethodVisitor visitor = null;
		
		switch (_diagramType) {
			case UML:
				visitor = new UMLMethodAssociationVisitor(Opcodes.ASM5, toDecorate, _relationshipManager, _targetClass.getDeclaration().getName(), arguments, classMethod);
				break;
			case SEQUENCE:
				visitor = new SequenceDiagramMethodVisitor(Opcodes.ASM5, toDecorate, _relationshipManager, _targetClass.getDeclaration().getName(), arguments, classMethod);
				break;
		}
		
		return visitor;
	}
	
}