package output;

import java.util.Collection;

import api.IClassField;
import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassField;
import impl.RelationshipManager.RelationshipEdge;
import utils.AsmClassUtils;
import utils.DotClassUtils;
import utils.DotClassUtils.RelationshipType;
import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.ITraverser;
import visitor.VisitType;

public class UMLDiagramOutputStream extends AbstractDiagramOutputStream {

	public UMLDiagramOutputStream(String asmOutputPath) {
		super(asmOutputPath);
		this.setupPreVisitTargetClass();
		this.setupPostVisitTargetClass();
		this.setupVisitClassField();
		this.setupPosVisitClassField();
		this.setupVisitIClassMethod();
		this.setupVisitRelationshipManager();
	}
	
	protected void setupPreVisitTargetClass() {
		super.addVisit(VisitType.PreVisit, ITargetClass.class, (ITraverser t) -> {
			ITargetClass c = (ITargetClass) t;
			StringBuilder sb = new StringBuilder();
			// TODO : let's talk about this some more
			String className = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');

			sb.append(className + "[\n\t");
			sb.append("label = \"{" + className + "|");
			write(sb.toString());
		});
	}
	
	protected void setupPostVisitTargetClass() {
		super.addVisit(VisitType.PostVisit, ITargetClass.class, (ITraverser t) -> {
			write("}\"\n]\n\n");
		});
	}
	
	protected void setupVisitClassField() {
		super.addVisit(VisitType.Visit, IClassField.class, (ITraverser t) -> {
			IClassField c = (IClassField) t;
			StringBuilder sb = new StringBuilder();
			sb.append(c.getAccessLevel() + " " + c.getFieldName() + " : ");
			sb.append(AsmClassUtils.GetStringStrippedByCharacter(c.getType(), '/'));
			
			if (c.getSignature() != null && !c.getSignature().equals(""))
				sb.append(c.getSignature());
			
			sb.append("\\l");
			write(sb.toString());
		});
	}
	
	protected void setupVisitIClassMethod() {
		super.addVisit(VisitType.Visit, IClassMethod.class, (ITraverser t) -> {
			IClassMethod c = (IClassMethod) t;
			StringBuilder sb = new StringBuilder();
			if (c.getMethodName().contains("<"))
				return;
			
			
			sb.append(c.getAccessLevel() + " " + c.getMethodName());
			sb.append("(" + c.getSignature() + ") : ");
			sb.append(AsmClassUtils.GetStringStrippedByCharacter(c.getReturnType(), '/') + "\\l");
			
			write(sb.toString());
		});
	}

	protected void setupPosVisitClassField() {
		super.addVisit(VisitType.PostVisit, IClassField.class, (ITraverser t) -> {
			write("|");
		});
	}

	protected void setupVisitRelationshipManager() {
		super.addVisit(VisitType.Visit, IRelationshipManager.class, (ITraverser t) -> {
			IRelationshipManager relationshipManager = (IRelationshipManager) t;
			
			for (RelationshipType edgeType : RelationshipType.values()) {
				Collection<RelationshipEdge> relationships = relationshipManager.getRelationshipEdges(edgeType);

				write(DotClassUtils.CreateRelationshipEdge(edgeType));

				for (RelationshipEdge edge : relationships) {
					if (_projectModel.getTargetClassByName(edge.getSuperClass()) == null)
						continue;
					
					// TODO : also talk about this :)
					String edgeToWrite = AsmClassUtils.GetStringStrippedByCharacter(edge.getSubClass(), '/') + " -> " + AsmClassUtils.GetStringStrippedByCharacter(edge.getSuperClass(), '/');
					
					if (edgeType.equals(RelationshipType.USES)) {
						if (!_projectModel.getRelationshipManager().containsRelationshipEdge(edge.getSubClass(), edge.getSuperClass(), RelationshipType.ASSOCIATION)) {
							write(edgeToWrite + "\n");
						}
					} else {
						write(edgeToWrite + "\n");
					}
					
				}
				
				write("\n");
			}
		});
	}

	@Override
	public void writeOutput() {
		prepareFile();
		
		for (ITargetClass clazz : _projectModel.getTargetClasses()) {
			preVisit(clazz);
	
			for (IClassField field : clazz.getFields()) {
				field.accept(this);
			}
	
			postVisit(new ClassField(null, null, null, null));
	
			for (IClassMethod method : clazz.getMethods()) {
				method.accept(this);
			}
	
			postVisit(clazz);
		}
		
		_projectModel.getRelationshipManager().accept(this);
		
		endFile();
	}

	protected void prepareFile() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph G {\n");
		sb.append(DotClassUtils.CreateFontNode("Sans", "8"));

		write(sb.toString());
	}
	
	protected void endFile() {
		write("\n}");
	}

	public void generateDiagram(String diagramOutputPath) {
		LaunchDiagramGenerator.RunGVEdit(_asmOutputPath, diagramOutputPath, DiagramFileExtension.PNG);
	}

}
