package api;

import java.util.Collection;

import impl.MethodStatement;

public interface IClassMethod extends ITargetClassPart {
	public String getSignature();

	public String getName();

	public String getAccessLevel();
	
	public String getReturnType();
	
	public void addStatement(MethodStatement stmt);
	
	public Collection<MethodStatement> getStatements();

}
