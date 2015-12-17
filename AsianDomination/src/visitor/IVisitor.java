package visitor;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;

public interface IVisitor {
	public void preVisit(ITargetClass c);
	public void visit(ITargetClass c);
	public void postVisit(ITargetClass c);

	
	public void preVisit(IClassField f);
	public void visit(IClassField f);
	public void postVisit(IClassField f);

	public void preVisit(IClassDeclaration d);
	public void visit(IClassDeclaration d);
	public void postVisit(IClassDeclaration d);

	public void preVisit(IClassMethod m);
	public void visit(IClassMethod m);
	public void postVisit(IClassMethod m);

}
