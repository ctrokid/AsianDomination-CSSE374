package visitor;


public interface IVisitor {
	public void preVisit(ITraverser c);
	public void visit(ITraverser c);
	public void postVisit(ITraverser c);
	
	
	public void addVisit(VisitType visitType, Class<?> clazz, IVisitMethod m);
	public void removeVisit(VisitType visitType, Class<?> clazz);
	
	
//	public void preVisit(IClassField f);
//	public void visit(IClassField f);
//	public void postVisit(IClassField f);
//
//	public void preVisit(IClassDeclaration d);
//	public void visit(IClassDeclaration d);
//	public void postVisit(IClassDeclaration d);
//
//	public void preVisit(IClassMethod m);
//	public void visit(IClassMethod m);
//	public void postVisit(IClassMethod m);
//
//	public void visit(IRelationshipManager relationshipManager);
}
