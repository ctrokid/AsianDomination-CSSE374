package api;

public interface IClassField extends ITargetClassPart {
	public String getFieldName();
	public int getAccessLevel();
	public String getSignature();
	public String getType();
}
