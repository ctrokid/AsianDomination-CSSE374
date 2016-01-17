package api;

public interface IMethodStatement extends ITargetClassPart {
	public String getCallerClass();
	public String getClassToCall();
	public String getParameter();
	public int getSequenceLevel();
}
