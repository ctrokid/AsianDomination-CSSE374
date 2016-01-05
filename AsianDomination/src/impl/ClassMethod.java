package impl;

import api.IClassMethod;
import visitor.IVisitor;

public class ClassMethod implements IClassMethod {
	private String _signature;
	private String _name;
	private String _accessLevel;
	private String _returnType;
	
	public ClassMethod(String name, String signature, String accessLevel, String returnType) {
		_name = name;
		_signature = signature;
		_accessLevel = accessLevel;
		_returnType = returnType;
	}
	
	@Override
	public String getSignature() {
		return _signature;
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
	public String getReturnType() {
		return _returnType;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

}
