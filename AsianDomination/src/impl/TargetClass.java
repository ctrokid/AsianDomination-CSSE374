package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
	// TODO: Think we need association and uses in here
	
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
			// FIXME: Throw error later
			System.err.println("Fix me, you errored.");
		}
	}

	@Override
	public void accept(IVisitor v) {
		v.preVisit(this);
		
		IClassField lastField = null;
		for (Iterator<IClassField> fieldIter = fieldParts.iterator(); fieldIter.hasNext();) {
			lastField = fieldIter.next();
			lastField.accept(v);
		}
		
		// Need to print separation
		// so we call postVisit on ONE field
		// doesn't matter which field it is, this should be safe
		v.postVisit(lastField);
		
		for (IClassMethod method : methodParts) {
			method.accept(v);
		}
		
		v.postVisit(this);
	}

}