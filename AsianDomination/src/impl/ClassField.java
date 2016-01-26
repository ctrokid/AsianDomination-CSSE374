package impl;

import api.IClassField;
import visitor.IVisitor;

public class ClassField implements IClassField {
	private String _fieldName;
	private int _accessLevel;
	private String _signature;
	private String _type;
	
	public ClassField(String fieldName, int accessLevel, String signature, String type) {
		_fieldName = fieldName;
		_accessLevel = accessLevel;
		_signature = signature;
		_type = type;
	}

	@Override
	public String getFieldName() {
		return _fieldName;
	}

	@Override
	public int getAccessLevel() {
		return _accessLevel;
	}

	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public String getType() {
			return _type;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

}
