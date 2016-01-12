package visitor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import api.IClassField;
import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import utils.DotClassUtils;
import utils.DotClassUtils.RelationshipType;

public class UMLOutputStream extends VisitorAdapter {
	private OutputStream out;

	public UMLOutputStream(OutputStream out) {
		// TODO FIXME: this needs to setup everything now
		this.out = out;
		// this.prepareDotFile(fontName, fontSize);
		this.setupPostVisitTargetClass();
		this.setupPreVisitTargetClass();
		this.setupVisitClassField();
		this.setupPosVisitClassField();
		this.setupVisitIClassMethod();
		// this.setupVisitRelationsipManager();
		// this.endDotFile();
	}

	private void write(String s) {
		try {
			this.out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prepareDotFile(String fontName, String fontSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph G {\n");
		sb.append(DotClassUtils.CreateFontNode(fontName, fontSize));

		this.write(sb.toString());
	}

	public void endDotFile() {
		this.write("\n}");
	}

	public void setupPreVisitTargetClass() {
		IVisitMethod command = new IVisitMethod() {
			@Override
			public void execute(ITraverser t) {
				ITargetClass c = (ITargetClass) t;
				StringBuilder sb = new StringBuilder();
				String className = c.getDeclaration().getName();

				sb.append(className + "[\n\t");
				sb.append("label = \"{" + className + "|");
				write(sb.toString());
			}
		};
		super.addVisit(VisitType.PreVisit, ITargetClass.class, command);
	}

	public void setupPostVisitTargetClass() {
		super.addVisit(VisitType.PostVisit, ITargetClass.class,
				(ITraverser t) -> {
					StringBuilder sb = new StringBuilder();
					sb.append("}\"\n]\n\n");
					write(sb.toString());
				});
	}

	public void setupVisitClassField() {
		super.addVisit(VisitType.Visit, IClassField.class, (ITraverser t) -> {
			IClassField c = (IClassField) t;
			StringBuilder sb = new StringBuilder();
			sb.append(c.getAccessLevel() + " " + c.getName() + " : ");
			sb.append(c.getType() + c.getSignature() + "\\l");
			write(sb.toString());
		});
	}

	public void setupVisitIClassMethod() {
		super.addVisit(VisitType.Visit, IClassMethod.class, (ITraverser t) -> {
			IClassMethod c = (IClassMethod) t;
			StringBuilder sb = new StringBuilder();
			sb.append(c.getAccessLevel() + " " + c.getName());
			sb.append("(" + c.getSignature() + ") : ");
			sb.append(c.getReturnType() + "\\l");
			write(sb.toString());
		});
	}

	public void setupPosVisitClassField() {
		super.addVisit(VisitType.PostVisit, IClassField.class,
				(ITraverser t) -> {
					write("|");
				});
	}

	public void setupVisitRelationsipManager() {
		super.addVisit(
				VisitType.Visit,
				IRelationshipManager.class,
				(ITraverser t) -> {
					IRelationshipManager relationshipManager = (IRelationshipManager) t;
					for (RelationshipType edgeType : RelationshipType.values()) {
						Collection<String> relationships = relationshipManager
								.getRelationshipEdges(edgeType);

						if (relationships.size() > 0)
							write(DotClassUtils
									.CreateRelationshipEdge(edgeType));

						for (String edge : relationships) {
							if (edgeType.equals(RelationshipType.USES)) {
								if (!hasAssociation(edge, relationshipManager)) {
									write(edge + "\n");
								}
							} else {
								write(edge + "\n");
							}
						}
						write("\n");
					}
				});
	}

	private boolean hasAssociation(String checker,
			IRelationshipManager relationshipManager) {
		if (relationshipManager.getRelationshipEdges(
				RelationshipType.ASSOCIATION).contains(checker)) {
			return true;
		}
		return false;
	}

}