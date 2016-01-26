package api;

import java.util.Collection;

public interface IClassMethod extends ITargetClassPart {
	public String getSignature();
	
	public String getMethodName();

	public int getAccessLevel();
	
	public String getReturnType();
	
	public void addMethodStatement(IMethodStatement stmt);
	
	public Collection<IMethodStatement> getMethodStatements();

}
