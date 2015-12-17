package impl;

import api.IClassField;
import visitor.IVisitor;

public class ClassField implements IClassField {
	private String _name;
	private String _accessLevel;
	private String _signature;
	private String _type;
	
	
	
	public ClassField(String name, String accessLevel, String signature, String type) {
		_name = name;
		_accessLevel = accessLevel;
		_signature = signature;
		_type = type;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getAccessLevel() {
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
