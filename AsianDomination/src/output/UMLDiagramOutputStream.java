package output;

import java.util.ArrayList;
import java.util.List;

import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import impl.ClassField;
import impl.Relationship;
import utils.AsmClassUtils;
import utils.ClassStyle;
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
		this.setupVisitTargetClass();
		this.setupPostVisitTargetClass();
		this.setupVisitClassField();
		this.setupPostVisitClassField();
		this.setupVisitIClassMethod();
		_relationships = new ArrayList<String>();
	}

	protected void setupVisitTargetClass() {
		super.addVisit(VisitType.Visit, ITargetClass.class, (ITraverser t) -> {
			ITargetClass c = (ITargetClass) t;
			StringBuilder sb = new StringBuilder();
			String className = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');
			ClassStyle style = new ClassStyle();
			sb.append(className + "[\n\t");
			sb.append(style.getStyleByType(c.getPatternStringName()) +",label = \"{" + className + c.getPatternString() + "|");
			write(sb.toString());
		});
	}

	protected void setupPostVisitTargetClass() {
		super.addVisit(VisitType.PostVisit, ITargetClass.class, (ITraverser t) -> {
			write("}\"\n]\n\n");
			ITargetClass c = (ITargetClass) t;
			
			for (Relationship r : c.getRelationEdges()) {
				if (_projectModel.getTargetClassByName(r.getSuperClass()) == null)
					continue;
				
				String thisClass = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');
				String subjectClass = AsmClassUtils.GetStringStrippedByCharacter(r.getSuperClass(), '/');
				
				String relationship = thisClass + " -> " + subjectClass + "[" + DotClassUtils.CreateRelationshipEdge(r.getRelationshipType());
				
				if (!r.getDecoratedType().equals(""))
					relationship += ", label = \"" + r.getDecoratedType() + "\"";
				
				relationship += "];\n";
				
				if (_relationships.contains(relationship))
					return;
				
				if (r.getRelationshipType().equals(RelationshipType.USES)) {
					if (!c.containsRelationship(RelationshipType.ASSOCIATION, r.getSuperClass())) {
						_relationships.add(relationship);
					}						
				} else {
					_relationships.add(relationship);
				}
			}
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

	protected void setupPostVisitClassField() {
		super.addVisit(VisitType.PostVisit, IClassField.class, (ITraverser t) -> {
			write("|");
		});
	}

	@Override
	public void writeOutput() {
		prepareFile();

		for (ITargetClass clazz : _projectModel.getTargetClasses()) {
			clazz.accept(this);

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
