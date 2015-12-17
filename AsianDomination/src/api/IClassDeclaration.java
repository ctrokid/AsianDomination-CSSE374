package api;

import java.util.Collection;

public interface IClassDeclaration extends ITargetClassPart {
	public String getName();

	public String getType();

	public String getSuperType();

	public Collection<String> getInterfaces();
}
