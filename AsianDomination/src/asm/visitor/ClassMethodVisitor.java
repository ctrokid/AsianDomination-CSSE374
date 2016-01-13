package asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

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
		
		MethodVisitor _decorator = createDecoratedVisitor(toDecorate, arguments);
		if (!name.contains("<")) {
			_targetClass.addPart(new ClassMethod(name, arguments, accessLevel, type));
			_relationshipManager.addRelationshipEdge(_targetClass.getDeclaration().getName(), type, RelationshipType.USES);
		}

		return _decorator;
	}
	
	private MethodVisitor createDecoratedVisitor(MethodVisitor toDecorate, String arguments) {
		MethodVisitor visitor = null;
		
		switch (_diagramType) {
			case UML:
				visitor = new UMLMethodAssociationVisitor(Opcodes.ASM5, toDecorate, _relationshipManager, _targetClass.getDeclaration().getName(), arguments);
				break;
			case SEQUENCE:
				visitor = new SequenceDiagramMethodVisitor(Opcodes.ASM5, toDecorate, _relationshipManager, _targetClass.getDeclaration().getName(), arguments);
				break;
		}
		
		return visitor;
	}
	
}