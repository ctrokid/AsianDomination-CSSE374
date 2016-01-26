package api;

import java.util.List;

public interface IClassDeclaration extends ITargetClassPart {
	
	public String getSuperType();
	public String getSignature();
	public List<String> getInterfaces();

}
