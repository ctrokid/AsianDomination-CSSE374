package output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import impl.ClassField;
import utils.AsmClassUtils;
import utils.DotClassUtils;
import utils.DotClassUtils.RelationshipType;
import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.ITraverser;
import visitor.IVisitor;
import visitor.VisitType;

public class UMLDiagramOutputStream extends AbstractDiagramOutputStream {
	protected List<String> _relationships;
	
	public UMLDiagramOutputStream(String asmOutputPath, IVisitor visitor) {
		super(asmOutputPath, visitor);
		this.setupPreVisitTargetClass();
		this.setupPostVisitTargetClass();
		this.setupVisitClassField();
		this.setupPosVisitClassField();
		this.setupVisitIClassMethod();
		_relationships = new ArrayList<String>();
	}

	protected void setupPreVisitTargetClass() {
		super.addVisit(VisitType.PreVisit, ITargetClass.class, (ITraverser t) -> {
			ITargetClass c = (ITargetClass) t;
			StringBuilder sb = new StringBuilder();
			String className = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');

			sb.append(className + "[\n\t");
			sb.append("label = \"{" + className + "|");
			write(sb.toString());
			
			HashMap<RelationshipType, HashSet<String>> relationshipEdges = c.getRelationEdges();
			for (RelationshipType type : RelationshipType.values()) {
				
				for (String edge : relationshipEdges.get(type)) {
					
					if (_projectModel.getTargetClassByName(edge) == null)
						continue;
					
					String edgeToWrite = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/') + " -> " + AsmClassUtils.GetStringStrippedByCharacter(edge, '/') + DotClassUtils.CreateRelationshipEdge(type);
					
					if (type.equals(RelationshipType.USES)) {
						if (!c.containsRelationship(RelationshipType.ASSOCIATION, edge)) {
							_relationships.add(edgeToWrite);
						}						
					} else {
						_relationships.add(edgeToWrite);
					}
				}
			}
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
			
			String acessLevel = AsmClassUtils.GetAccessLevel(c.getAccessLevel());
			sb.append(acessLevel + " " + c.getFieldName() + " : ");
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

			String accessLevel = AsmClassUtils.GetAccessLevel(c.getAccessLevel());

			sb.append(accessLevel + " " + c.getMethodName());
			sb.append("(" + AsmClassUtils.GetArguments(c.getSignature(), true) + ") : ");
			sb.append(AsmClassUtils.GetStringStrippedByCharacter(c.getReturnType(), '/') + "\\l");

			write(sb.toString());
		});
	}

	protected void setupPosVisitClassField() {
		super.addVisit(VisitType.PostVisit, IClassField.class, (ITraverser t) -> {
			write("|");
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

			postVisit(new ClassField(null, 0, null, null));

			for (IClassMethod method : clazz.getMethods()) {
				method.accept(this);
			}

			postVisit(clazz);
		}

		for (String relationship : _relationships) {
			write(relationship);
		}

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
