package api;

public interface IClassDeclaration extends ITargetClassPart {
	public String getName();

	public String getType();

	public String getSuperType();

	public String[] getInterfaces();
	
	public boolean isFirstClass();
	public void setFirstClass();
}
