package output;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import api.IClassField;
import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.Relationship;
import pattern.decoration.GraphVizStyleTargetClass;
import utils.AsmClassUtils;
import utils.DotClassUtils;
import utils.DotClassUtils.RelationshipType;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.ITraverser;
import visitor.IVisitor;
import visitor.VisitType;

public class UMLDiagramOutputStream extends AbstractDiagramOutputStream {
	private Set<String> _relationships;
	private List<String> _classFilterList;
	
	public UMLDiagramOutputStream(Properties props, IVisitor visitor) {
		super(props, visitor);
		initialize();
	}

	public UMLDiagramOutputStream(Properties props) {
		super(props);
		initialize();
	}
	
	private void initialize() {
		this.setupVisitTargetClass();
		this.setupPostVisitTargetClass();
		this.setupVisitClassField();
		this.setupPostVisitClassField();
		this.setupVisitIClassMethod();
		_relationships = new HashSet<String>();
		_classFilterList = new ArrayList<String>();
	}
	
	public void setClassFilter(List<String> classes) {
		_classFilterList = classes;
	}

	protected void setupVisitTargetClass() {

		_visitor.addVisit(VisitType.Visit, GraphVizStyleTargetClass.class, (ITraverser t) -> {
			GraphVizStyleTargetClass c =  (GraphVizStyleTargetClass) t;
			StringBuilder sb = new StringBuilder();
			String className = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');
			sb.append(className + "[\n\t");
			sb.append(c.getStyle()+"label = \"{" + className + c.getClassTypeWithCarrots() + "|");
			write(sb.toString());
		});
	}

	protected void setupPostVisitTargetClass() {
		_visitor.addVisit(VisitType.PostVisit, ITargetClass.class, (ITraverser t) -> {
			write("}\"\n]\n\n");
			ITargetClass c = (ITargetClass) t;
			
			IRelationshipManager manager = _projectModel.getRelationshipManager();
			
			for (Relationship r : manager.getClassRelationships(c.getClassName())) {
				if (_projectModel.getTargetClassByName(r.getDependentClass()) == null)
					continue;
				
				if (_classFilterList.size() != 0 && !_classFilterList.contains(r.getDependentClass()))
					continue;
				
				String thisClass = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');
				String subjectClass = AsmClassUtils.GetStringStrippedByCharacter(r.getDependentClass(), '/');
				
				String relationship = thisClass + " -> " + subjectClass + "[" + DotClassUtils.CreateRelationshipEdge(r.getRelationshipType());
				
				if (!r.getDecoratedType().equals(""))
					relationship += ", label = \"" + r.getDecoratedType() + "\"";
				
				relationship += "];\n";
				
				if (_relationships.contains(relationship))
					return;
				
				if (r.getRelationshipType().equals(RelationshipType.USES)) {
					if (manager.getClassRelationship(c.getClassName(), RelationshipType.ASSOCIATION, r.getDependentClass()) == null) {
						_relationships.add(relationship);
					}						
				} else {
					_relationships.add(relationship);
				}
			}
		});
	}

	protected void setupVisitClassField() {
		_visitor.addVisit(VisitType.Visit, IClassField.class, (ITraverser t) -> {
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
		_visitor.addVisit(VisitType.Visit, IClassMethod.class, (ITraverser t) -> {
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
		_visitor.addVisit(VisitType.PostVisit, IClassField.class, (ITraverser t) -> {
			write("|");
		});
	}

	@Override
	protected void writeOutput() {
		_relationships = new HashSet<String>();
		prepareFile();

		for (ITargetClass clazz : _projectModel.getTargetClasses()) {
			if (_classFilterList.size() == 0)
				((GraphVizStyleTargetClass)clazz).accept(_visitor);
			
			else if (_classFilterList.contains(clazz.getClassName()))
				((GraphVizStyleTargetClass)clazz).accept(_visitor);
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

	@Override
	protected void generateDiagram() {
		_diagramGenerator.RunGVEdit(_asmOutputPath, DiagramFileExtension.PNG);
	}

}
