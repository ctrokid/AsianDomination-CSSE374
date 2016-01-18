package api;

public interface IMethodStatement extends ITargetClassPart {
	public String getCallerClass();
	public String getClassToCall();
	public String getParameter();
	public String getMethodName();
	public int getSequenceLevel();
}
