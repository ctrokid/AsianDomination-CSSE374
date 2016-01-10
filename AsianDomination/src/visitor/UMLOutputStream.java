package visitor;

import impl.RelationshipManager;

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
		this.out = out;
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

	@Override
	public void preVisit(ITargetClass c) {
		StringBuilder sb = new StringBuilder();
		String className = c.getDeclaration().getName();

		sb.append(className + "[\n\t");
		sb.append("label = \"{" + className + "|");

		this.write(sb.toString());
	}

	@Override
	public void postVisit(ITargetClass c) {
		StringBuilder sb = new StringBuilder();
		sb.append("}\"\n]\n\n");

		this.write(sb.toString());
	}

	@Override
	public void visit(IClassField c) {
		StringBuilder sb = new StringBuilder();
		sb.append(c.getAccessLevel() + " " + c.getName() + " : ");
		sb.append(c.getType() + "\\l");

		this.write(sb.toString());
	}

	@Override
	public void visit(IClassMethod c) {
		StringBuilder sb = new StringBuilder();
		sb.append(c.getAccessLevel() + " " + c.getName());
		sb.append("(" + c.getSignature() + ") : ");
		sb.append(c.getReturnType() + "\\l");

		this.write(sb.toString());
	}

	@Override
	public void postVisit(IClassField c) {
		this.write("|");
	}

	@Override
	public void visit(IRelationshipManager relationshipManager) {
		for (RelationshipType edgeType : RelationshipType.values()) {
			Collection<String> relationships = relationshipManager.getRelationshipEdges(edgeType);
			
			if (relationships.size() > 0)
				this.write(DotClassUtils.CreateRelationshipEdge(edgeType));
			
			for (String edge : relationships) {
				if(edgeType.equals(RelationshipType.USES)){
					if(!hasAssociation(edge, relationshipManager)){
						this.write(edge + "\n");
					} else {
						continue;
					}
				} else {
					this.write(edge + "\n");
				}
			}
			this.write("\n");
		}
	}
	
	private boolean hasAssociation(String checker, IRelationshipManager relationshipManager){
		if(relationshipManager.getRelationshipEdges(RelationshipType.ASSOCIATION).contains(checker)){
			return true;
		}
		return false;
	}

}
