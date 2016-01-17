package api;

public interface IMethodStatement {
	public String getCallerClass();
	public String getClassToCall();
	public String getParameter();
	public int getSequenceLevel();
}
