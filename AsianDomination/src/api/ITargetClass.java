package api;

import java.util.Collection;

import visitor.ITraverser;

public interface ITargetClass extends ITraverser {
	public Collection<IClassField> getFieldParts();
	public Collection<IClassMethod> getMethodParts();
	public IClassDeclaration getDeclaration();
	public void addPart(ITargetClassPart part);
}
