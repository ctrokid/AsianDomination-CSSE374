package api;

public interface IMethodStatement extends ITargetClassPart {
	public String getCallerClass();
	public String getClassToCall();
	public String getParameters();
	public String getMethodName();
	public int getSequenceLevel();
}
