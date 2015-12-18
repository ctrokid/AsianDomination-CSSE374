package impl;

import java.util.ArrayList;
import java.util.Collection;

import visitor.IVisitor;
import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import api.ITargetClassPart;

public class TargetClass implements ITargetClass {
	private Collection<IClassField> fieldParts;
	private Collection<IClassMethod> methodParts;
	private IClassDeclaration declarationPart;
	
	public TargetClass() {
		fieldParts = new ArrayList<IClassField>();
		methodParts = new ArrayList<IClassMethod>();
		declarationPart = null;
	}
	
	@Override
	public Collection<IClassField> getFieldParts() {
		return fieldParts;
	}

	@Override
	public Collection<IClassMethod> getMethodParts() {
		return methodParts;
	}

	@Override
	public IClassDeclaration getDeclaration() {
		return declarationPart;
	}

	@Override
	public void addPart(ITargetClassPart part) {
		if (part instanceof IClassDeclaration) {
			declarationPart = (IClassDeclaration) part;
		} else if (part instanceof IClassField) {
			fieldParts.add((IClassField) part);
		} else if (part instanceof IClassMethod) {
			methodParts.add((IClassMethod) part);
		} else {
			// DO SOMETHING??
			// Throw error??
			return;
		}
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

}