package visitor;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;

public abstract class VisitorAdapter implements IVisitor {

	@Override
	public void preVisit(ITargetClass c) {

	}

	@Override
	public void visit(ITargetClass c) {

	}

	@Override
	public void postVisit(ITargetClass c) {

	}

	@Override
	public void preVisit(IClassField f) {

	}

	@Override
	public void visit(IClassField f) {

	}

	@Override
	public void postVisit(IClassField f) {

	}

	@Override
	public void preVisit(IClassDeclaration d) {

	}

	@Override
	public void visit(IClassDeclaration d) {

	}

	@Override
	public void postVisit(IClassDeclaration d) {

	}

	@Override
	public void preVisit(IClassMethod m) {

	}

	@Override
	public void visit(IClassMethod m) {

	}

	@Override
	public void postVisit(IClassMethod m) {

	}

}
