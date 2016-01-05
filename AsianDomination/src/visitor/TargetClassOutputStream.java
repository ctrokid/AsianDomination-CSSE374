package visitor;

import java.io.IOException;
import java.io.OutputStream;

import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;

public class TargetClassOutputStream extends VisitorAdapter {
	private OutputStream out;

	public TargetClassOutputStream(OutputStream out){
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
	public void visitCollection(ITargetClass[] classes) {
		// inheritance first
		// edge with empty head
		this.write(DotClassUtils.CreateRelationshipEdge(DotClassUtils.RelationshipType.INHERITANCE));
		for (ITargetClass clazz : classes) {
			String base = clazz.getDeclaration().getName();
			String superType = clazz.getDeclaration().getSuperType();
			
			if (!superType.toLowerCase().equals("object"))
				this.write(base + " -> " + superType + "\n");
		}
		
		this.write("\n");
		
		// implements
		// edge, dotted with empty head
		this.write(DotClassUtils.CreateRelationshipEdge(DotClassUtils.RelationshipType.IMPLEMENTATION));
		for (ITargetClass clazz : classes) {
			String base = clazz.getDeclaration().getName();
			String[] interfaces = clazz.getDeclaration().getInterfaces();

			for (String iface : interfaces) {
				this.write(base + " -> " + iface + "\n");
			}
		}
	}

}
