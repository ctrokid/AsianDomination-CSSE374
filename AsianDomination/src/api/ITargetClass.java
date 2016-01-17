package api;


import java.util.Collection;

import visitor.ITraverser;

public interface ITargetClass extends ITraverser {
	public String getClassName();
	public Collection<IClassMethod> getMethods();
	public Collection<IClassField> getFields();
	public void addClassMethod(IClassMethod classMethod);
	public void addClassField(IClassField classField);
}
